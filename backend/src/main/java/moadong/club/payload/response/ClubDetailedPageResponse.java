package moadong.club.payload.response;

import lombok.Builder;
import moadong.club.entity.Club;
import moadong.club.entity.ClubInformation;

import java.util.List;

@Builder
public record ClubDetailedPageResponse(
        String id,
        String name,
        String logo,
        List<String> tags,
        String state,
        List<String> feeds,
        String description,
        String presidentName,
        String presidentPhoneNumber,
        String recruitmentPeriod,
        String classification,
        String division
) {
    public static ClubDetailedPageResponse createClubDetailedPageResponse(
            Club club,
            ClubInformation clubInformation,
            List<String> clubFeedImages,
            List<String> clubTags) {
        return ClubDetailedPageResponse.builder()
                .id(club.getId())
                .name(club.getName())
                .classification(club.getClassification())
                .division(club.getDivision())
                .state(club.getState().getDesc())
                .description(clubInformation.getDescription())
                .presidentName(clubInformation.getPresidentName())
                .presidentPhoneNumber(clubInformation.getPresidentTelephoneNumber())
                .feeds(clubFeedImages)
                .tags(clubTags)
                .build();
    }
}
