package moadong.gcs.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import moadong.club.entity.ClubFeedImages;
import moadong.club.entity.ClubInformation;
import moadong.club.repository.ClubFeedImageRepository;
import moadong.club.repository.ClubInformationRepository;
import moadong.club.repository.ClubRepository;
import moadong.global.exception.ErrorCode;
import moadong.global.exception.RestApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class GcsService {

    private final ClubInformationRepository clubInformationRepository;
    private final ClubFeedImageRepository clubFeedImageRepository;

    @Value("${google.cloud.storage.bucket.name}")
    private String bucketName;

    private final Storage storage;
    private final int MAX_FEED_COUNT = 5;


    public String uploadLogo(String clubId, MultipartFile file) {

        ClubInformation clubInfo = clubInformationRepository.findByClubId(clubId)
                .orElseThrow(() -> new RestApiException(ErrorCode.CLUB_INFORMATION_NOT_FOUND));
        if (clubInfo.getThumbnail() != null) {
            // https://storage.googleapis.com/{bucketName}/{clubId}/{fileType}/{filePath} -> {filePath}
            String thumbnailPath = clubInfo.getThumbnail().split("/",5)[4];
            deleteFile(thumbnailPath);
        }

        String filePath = uploadFile(clubId, file, "logo");
        clubInformationRepository.save(clubInfo.updateThumbnail(filePath));
        return filePath;
    }

    public void uploadFeeds(String clubId, List<MultipartFile> files) {
        if (files.isEmpty()) {
            throw new RestApiException(ErrorCode.FILE_NOT_FOUND);
        } else if (files.size() + clubFeedImageRepository.countByClubId(clubId) > MAX_FEED_COUNT) {
            throw new RestApiException(ErrorCode.TOO_MANY_FILES);
        }

        for (MultipartFile file : files) {
            if (file == null) {
                throw new RestApiException(ErrorCode.FILE_NOT_FOUND);
            }
            String filePath = uploadFile(clubId, file, "feed");
            ClubFeedImages clubFeedImages = ClubFeedImages.builder().clubId(clubId).image(filePath).build();
            clubFeedImageRepository.save(clubFeedImages);
        }
    }


    private String uploadFile(String clubId, MultipartFile file, String fileType) {

        if (file == null) {
            throw new RestApiException(ErrorCode.FILE_NOT_FOUND);
        }

        BlobInfo blobInfo = getBlobInfo(clubId, fileType, file);
        try {
            storage.create(blobInfo, file.getBytes()); // 파일 업로드
        } catch (Exception e) {
            throw new RestApiException(ErrorCode.IMAGE_UPLOAD_FAILED);
        }

        return "https://storage.googleapis.com/" + bucketName + "/" + blobInfo.getName();
    }

    private void deleteFile(String filePath) {
        // 삭제할 파일의 BlobId를 생성
        BlobId blobId = BlobId.of(bucketName,filePath);

        try {
            boolean deleted = storage.delete(blobId);
            if (!deleted) {
                throw new RestApiException(ErrorCode.IMAGE_DELETE_FAILED);
            }
        } catch (Exception e) {
            throw new RestApiException(ErrorCode.IMAGE_DELETE_FAILED);
        }
    }

    // BlobInfo 생성 (버킷 이름, 파일 이름 지정)
    private BlobInfo getBlobInfo(String clubId, String fileType, MultipartFile file) {
        String fileName = clubId + "/" + fileType + "/" + file.getOriginalFilename();
        BlobId blobId = BlobId.of(bucketName, fileName);

        return BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
    }
}
