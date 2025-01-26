package moadong.club.payload.response;

import lombok.Builder;
import moadong.club.entity.Club;
import moadong.club.entity.ClubInformation;

import java.util.List;

@Builder
public record ClubDetailedPageResponse(
        String clubId,
        String clubName,
        String clubImageUrl,
        List<String> clubTags,
        String clubState,
        List<String> clubFeeds,
        String clubDescription,
        String clubPresidentName,
        String clubPresidentPhoneNumber,
        String clubPeriod
) {
    public static ClubDetailedPageResponse createClubDetailedPageResponse(Club club, ClubInformation clubInformation, List<String> clubFeedImages, List<String> clubTags) {
        return ClubDetailedPageResponse.builder()
                .clubId(club.getId())
                .clubName(club.getName())
                .clubState(club.getState().getDesc())
                .clubDescription(clubInformation.getDescription())
                .clubPresidentName(clubInformation.getClubPresidentName())
                .clubPresidentPhoneNumber(clubInformation.getPresidentTelephoneNumber())
                .clubFeeds(clubFeedImages)
                .clubTags(clubTags)
                .build();
    }
}
