package moadong.gcs.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import moadong.gcs.dto.ImageDeleteRequest;
import moadong.gcs.service.ClubImageService;
import moadong.global.payload.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubImageController {

    private final ClubImageService clubImageService;


    @PostMapping("/{clubId}/logo")
    public ResponseEntity<?> uploadLogo(@PathVariable String clubId,
                                        @RequestParam("logo") MultipartFile file) {
        String fileUrl = clubImageService.uploadLogo(clubId, file);
        return Response.ok(fileUrl);
    }

    @DeleteMapping("/images")
    public ResponseEntity<?> deleteFile(@RequestBody ImageDeleteRequest imageDeleteRequest) {
        clubImageService.deleteFile(imageDeleteRequest.filePath());
        return Response.ok("success delete image");
    }

    @PostMapping("/{clubId}/feeds")
    public ResponseEntity<?> uploadLogo(@PathVariable String clubId,
                                        @RequestParam("feeds") List<MultipartFile> files) {
        clubImageService.uploadFeeds(clubId, files);
        return Response.ok("success upload feeds");
    }

}
