package moadong.gcs.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import moadong.global.exception.ErrorCode;
import moadong.global.exception.RestApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class GcsService {

    @Value("${google.cloud.storage.bucket.name}")
    private String bucketName;

    private final Storage storage;

    public String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        // BlobInfo 생성 (버킷 이름, 파일 이름 지정)
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        try {
            // 파일 업로드
            storage.create(blobInfo, file.getBytes());
        }catch (Exception e) {
            throw new RestApiException(ErrorCode.IMAGE_UPLOAD_FAILED);
        }
        return "https://storage.googleapis.com/" + bucketName + "/" + fileName;
    }
}
