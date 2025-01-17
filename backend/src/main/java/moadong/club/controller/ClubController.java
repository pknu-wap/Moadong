package moadong.club.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import moadong.club.dto.ClubCreateRequest;
import moadong.club.dto.ClubUpdateRequest;
import moadong.club.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/club")
@AllArgsConstructor
@Tag(name = "Club", description = "클럽 API")
public class ClubController {

    private final ClubService clubService;

    @PostMapping("/")
    @Operation(summary = "클럽 생성", description = "클럽을 생성합니다.")
    public ResponseEntity<String> createClub(@RequestBody ClubCreateRequest request) {
        clubService.createClub(request);
        return new ResponseEntity<>("success create club", HttpStatus.OK);
    }

    @PutMapping("/")
    @Operation(summary = "클럽 수정", description = "클럽을 수정합니다.")
    public ResponseEntity<String> updateClub(@RequestBody ClubUpdateRequest request) {
        clubService.updateClub(request);
        return new ResponseEntity<>("success update club", HttpStatus.OK);
    }
}
