import React from 'react';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { renderHook, waitFor } from '@testing-library/react';
import { feedApi, uploadToStorage } from '@/apis/image';
import { useUploadFeed } from './useClubImages';

jest.mock('@/apis/image', () => ({
  feedApi: { getUploadUrls: jest.fn(), updateFeeds: jest.fn() },
  logoApi: {
    getUploadUrl: jest.fn(),
    completeUpload: jest.fn(),
    delete: jest.fn(),
  },
  uploadToStorage: jest.fn(),
}));

const mockedGetUploadUrls = feedApi.getUploadUrls as jest.Mock;
const mockedUploadToStorage = uploadToStorage as jest.Mock;

const makeFile = (name: string) => new File([''], name, { type: 'image/jpeg' });

const success = (name: string) => ({
  presignedUrl: `https://r2.example/put/${name}`,
  finalUrl: `https://cdn.example/${name}`,
  success: true,
  failureReason: null,
});

const wrapper = ({ children }: { children: React.ReactNode }) => {
  const queryClient = new QueryClient({
    defaultOptions: { mutations: { retry: false } },
  });
  return (
    <QueryClientProvider client={queryClient}>{children}</QueryClientProvider>
  );
};

const uploadFiles = async (files: File[]) => {
  const { result } = renderHook(() => useUploadFeed(), { wrapper });
  const promise = result.current.mutateAsync({ clubId: 'club-1', files });
  await waitFor(() => expect(result.current.isPending).toBe(false));
  return promise;
};

describe('useUploadFeed', () => {
  beforeEach(() => {
    jest.clearAllMocks();
    mockedUploadToStorage.mockResolvedValue(undefined);
  });

  it('업로드 결과를 파일 참조 기준으로 매핑한다', async () => {
    mockedGetUploadUrls.mockResolvedValue([success('a.jpg'), success('b.jpg')]);
    mockedUploadToStorage.mockImplementation((_url: string, file: File) =>
      file.name === 'a.jpg'
        ? Promise.reject(new Error('실패'))
        : Promise.resolve(),
    );

    const files = [makeFile('a.jpg'), makeFile('b.jpg')];
    const data = await uploadFiles(files);

    expect(data.failedFiles).toEqual(['a.jpg']);
    expect(data.urlByFile.get(files[1])).toBe('https://cdn.example/b.jpg');
  });

  it('저장(updateFeeds)은 호출하지 않는다', async () => {
    mockedGetUploadUrls.mockResolvedValue([success('a.jpg')]);
    await uploadFiles([makeFile('a.jpg')]);
    expect(feedApi.updateFeeds).not.toHaveBeenCalled();
  });
});
