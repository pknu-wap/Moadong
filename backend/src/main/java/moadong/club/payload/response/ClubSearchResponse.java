package moadong.club.payload.response;

import lombok.Builder;
import moadong.club.payload.dto.ClubSearchResult;
import moadong.club.payload.dto.ClubThumbnailProjection;

import java.util.List;

@Builder
public record ClubSearchResponse(
    List<ClubSearchResult> results
) {
}
