package moadong.club.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import moadong.club.payload.request.ClubCreateRequest;
import moadong.club.payload.request.ClubUpdateRequest;
import moadong.club.payload.response.ClubDetailedPageResponse;
import moadong.club.payload.response.ClubSearchResponse;
import moadong.club.service.ClubCommandService;
import moadong.club.service.ClubDetailedPageService;
import moadong.club.service.ClubSearchFilterService;
import moadong.club.service.ClubSearchService;
import moadong.global.payload.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/club")
@AllArgsConstructor
@Tag(name = "Club", description = "클럽 API")
public class ClubController {

    private final ClubCommandService clubCommandService;
    private final ClubDetailedPageService clubDetailedPageService;
    private final ClubSearchFilterService clubSearchFilterService;
    private final ClubSearchService clubSearchService;

    @PostMapping("/")
    @Operation(summary = "클럽 생성", description = "클럽을 생성합니다.")
    public ResponseEntity<?> createClub(@RequestBody ClubCreateRequest request) {
        clubCommandService.createClub(request);
        return Response.ok("success create club");
    }

    @PutMapping("/")
    @Operation(summary = "클럽 수정", description = "클럽을 수정합니다.")
    public ResponseEntity<?> updateClub(@RequestBody ClubUpdateRequest request) {
        clubCommandService.updateClub(request);
        return Response.ok("success update club");
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<?> getClubDetailedPage(@PathVariable String clubId) {
        ClubDetailedPageResponse clubDetailedPageResponse = clubDetailedPageService.getClubDetailedPage(clubId);
        return Response.ok(clubDetailedPageResponse);
    }

    @GetMapping("/list/")
    public ResponseEntity<?> getClubsByFilter(
            @RequestParam(value = "availability", required = false) String availability,
            @RequestParam(value = "classification", required = false) String classification,
            @RequestParam(value = "division", required = false) String division
    ){
        ClubSearchResponse clubSearchResponse = clubSearchFilterService.getClubsByFilter(availability, classification, division);
        return Response.ok(clubSearchResponse);
    }

    @GetMapping("/search/")
    public ResponseEntity<?> searchClubsByKeyword(
            @RequestParam(value = "keyword", required = true) String keyword
    ){
        ClubSearchResponse clubSearchResponse = clubSearchService.searchClubsByKeyword(keyword);
        return Response.ok(clubSearchResponse);
    }
}
