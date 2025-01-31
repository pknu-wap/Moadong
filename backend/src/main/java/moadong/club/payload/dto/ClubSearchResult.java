package moadong.club.payload.dto;

import java.util.List;
import lombok.Builder;
@Builder
public record ClubSearchResult(
        String id,
        String name,
        String logo,
        List<String> tags,
        String state,
        String classification,
        String division,
        String description
) {
    public static ClubSearchResult createClubSearchResult(
            String id,
            String name,
            String logo,
            List<String> tags,
            String state,
            String classification,
            String division,
            String description
    ){
        return ClubSearchResult.builder()
                .id(id)
                .name(name)
                .logo(logo)
                .tags(tags)
                .state(state)
                .classification(classification)
                .division(division)
                .description(description)
                .build();
    }
}
