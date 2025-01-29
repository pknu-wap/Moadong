package moadong.club.payload.dto;

import java.util.List;
import lombok.Builder;
@Builder
public record ClubSearchResult(
        String clubId,
        String clubName,
        String clubImageUrl,
        List<String> clubTags,
        String clubState
) {
}
