package moadong.club.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import moadong.club.payload.request.ClubCreateRequest;
import moadong.club.payload.request.ClubUpdateRequest;
import moadong.club.payload.response.ClubDetailedPageResponse;
import moadong.club.payload.response.ClubSearchResponse;
import moadong.club.service.ClubDetailedPageService;
import moadong.club.service.ClubSearchService;
import moadong.club.service.ClubService;
import moadong.global.payload.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/club")
@AllArgsConstructor
@Tag(name = "Club", description = "클럽 API")
public class ClubController {

    private final ClubService clubService;
    private final ClubDetailedPageService clubDetailedPageService;
    private final ClubSearchService clubSearchService;

    @PostMapping("/")
    @Operation(summary = "클럽 생성", description = "클럽을 생성합니다.")
    public ResponseEntity<?> createClub(@RequestBody ClubCreateRequest request) {
        clubService.createClub(request);
        return Response.ok("success create club");
    }

    @PutMapping("/")
    @Operation(summary = "클럽 수정", description = "클럽을 수정합니다.")
    public ResponseEntity<?> updateClub(@RequestBody ClubUpdateRequest request) {
        clubService.updateClub(request);
        return Response.ok("success update club");
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<?> getClubDetailedPage(@PathVariable String clubId) {
        ClubDetailedPageResponse clubDetailedPageResponse = clubDetailedPageService.getClubDetailedPage(clubId);
        return Response.ok(clubDetailedPageResponse);
    }

    @GetMapping("/list/")
    public ResponseEntity<?> getClassificationFilteredClubList(@RequestParam(value = "filter", required = false) String filter) {
        ClubSearchResponse clubSearchResponse = clubSearchService.searchByClubStateFilter(filter);
        return Response.ok(clubSearchResponse);
    }
}
