package moadong.club.payload.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record ClubSearchResult(
        String clubId,
        String clubName,
        String clubImageUrl,
        List<String> clubTags,
        String clubState
) {
}
