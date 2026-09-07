import '@testing-library/jest-dom';
import { MemoryRouter } from 'react-router-dom';
import { act, render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { ThemeProvider } from 'styled-components';
import { theme } from '@/styles/theme';
import ClubDetailTopBar, {
  PERMISSION_TOAST_MESSAGE,
  SUBSCRIBED_TOAST_MESSAGE,
} from './ClubDetailTopBar';

jest.mock('mixpanel-browser', () => ({ track: jest.fn() }));

const CLUB_ID = 'club-1';
const postMessage = jest.fn();

const renderTopBar = (initialIsSubscribed = false) =>
  render(
    <ThemeProvider theme={theme}>
      <MemoryRouter>
        <ClubDetailTopBar
          clubId={CLUB_ID}
          clubName='테스트'
          initialIsSubscribed={initialIsSubscribed}
        />
      </MemoryRouter>
    </ThemeProvider>,
  );

const sentTypes = () =>
  postMessage.mock.calls.map(([raw]) => JSON.parse(raw).type);

/** 앱이 SUBSCRIBE_RESULT를 회신하는 상황을 흉내 낸다 */
const replyFromApp = (subscribed: boolean, needsPermission = false) =>
  act(() => {
    window.dispatchEvent(
      new MessageEvent('message', {
        data: JSON.stringify({
          type: 'SUBSCRIBE_RESULT',
          payload: { clubId: CLUB_ID, subscribed, needsPermission },
        }),
      }),
    );
  });

beforeEach(() => {
  jest.clearAllMocks();
  const modalRoot = document.createElement('div');
  modalRoot.id = 'modal-root';
  document.body.appendChild(modalRoot);
  Object.defineProperty(navigator, 'userAgent', {
    value: 'MoadongApp/1.7.1 (iOS)',
    configurable: true,
  });
  window.ReactNativeWebView = { postMessage };
  // 모달의 useBodyScrollLock이 부르는데 jsdom엔 없다
  window.scrollTo = jest.fn();
});

afterEach(() => {
  document.getElementById('modal-root')?.remove();
  delete window.ReactNativeWebView;
});

describe('구독 중이 아닐 때', () => {
  it('종을 누르면 바로 앱에 토글을 보내고 모달은 띄우지 않는다', async () => {
    renderTopBar(false);

    await userEvent.click(screen.getByRole('button', { name: '알림 설정' }));

    expect(sentTypes()).toEqual(['SUBSCRIBE_TOGGLE']);
    expect(screen.queryByRole('dialog')).not.toBeInTheDocument();
  });

  it('앱이 구독 완료를 회신하면 완료 토스트를 띄운다', () => {
    renderTopBar(false);

    replyFromApp(true);

    expect(screen.getByRole('status')).toHaveTextContent(
      SUBSCRIBED_TOAST_MESSAGE,
    );
  });

  it('앱이 권한 부족을 회신하면 권한 안내 토스트를 띄운다', () => {
    renderTopBar(false);

    replyFromApp(false, true);

    expect(screen.getByRole('status')).toHaveTextContent(
      PERMISSION_TOAST_MESSAGE,
    );
  });
});

describe('구독 중일 때', () => {
  it('종을 누르면 토글을 보내지 않고 취소 확인 모달을 띄운다', async () => {
    renderTopBar(true);

    await userEvent.click(screen.getByRole('button', { name: '알림 설정' }));

    expect(postMessage).not.toHaveBeenCalled();
    expect(screen.getByRole('dialog')).toHaveTextContent(
      '정말 구독을 취소하시겠어요?',
    );
  });

  it('"아니요"를 누르면 모달만 닫히고 아무것도 보내지 않는다', async () => {
    renderTopBar(true);
    await userEvent.click(screen.getByRole('button', { name: '알림 설정' }));

    await userEvent.click(screen.getByRole('button', { name: '아니요' }));

    expect(screen.queryByRole('dialog')).not.toBeInTheDocument();
    expect(postMessage).not.toHaveBeenCalled();
  });

  it('"네"를 누르면 모달이 닫히고 앱에 토글을 보낸다', async () => {
    renderTopBar(true);
    await userEvent.click(screen.getByRole('button', { name: '알림 설정' }));

    await userEvent.click(screen.getByRole('button', { name: '네' }));

    expect(screen.queryByRole('dialog')).not.toBeInTheDocument();
    expect(sentTypes()).toEqual(['SUBSCRIBE_TOGGLE']);
  });

  it('구독 해제 회신에는 토스트를 띄우지 않는다', () => {
    renderTopBar(true);

    replyFromApp(false);

    expect(screen.queryByRole('status')).not.toBeInTheDocument();
  });
});
