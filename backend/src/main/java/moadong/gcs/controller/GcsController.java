package moadong.gcs.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import moadong.gcs.service.GcsService;
import moadong.global.exception.ErrorCode;
import moadong.global.exception.RestApiException;
import moadong.global.payload.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class GcsController {

    private final GcsService gcsService;


    @PostMapping("/api/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file) throws IOException {
        String fileUrl = gcsService.uploadFile(file);
        return Response.ok(fileUrl);
    }

}
