package moadong.club.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import moadong.club.entity.Club;
import moadong.club.enums.ClubState;
import moadong.club.payload.dto.ClubSearchResult;
import moadong.club.payload.dto.ClubTagProjection;
import moadong.club.payload.dto.ClubThumbnailProjection;
import moadong.club.payload.response.ClubSearchResponse;
import moadong.club.repository.ClubInformationRepository;
import moadong.club.repository.ClubRepository;
import moadong.club.repository.ClubTagRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClubSearchService {

    private final ClubRepository clubRepository;
    private final ClubInformationRepository clubInformationRepository;
    private final ClubTagRepository clubTagRepository;

    public ClubSearchResponse searchByClubStateFilter(String filter) {
        List<Club> clubs;
        if (filter.equals("all")) {
            clubs = clubRepository.findAll();
        } else {
            clubs = clubRepository.findClubByState(ClubState.fromString(filter));
        }

        List<ClubSearchResult> clubSearchResults = new ArrayList<>();
        for (Club club : clubs) {
            String thumbnail = clubInformationRepository.findThumbnailByClubId(club.getId())
                .map(ClubThumbnailProjection::getThumbnail)
                .orElse("null");
            List<String> clubTags = clubTagRepository.findAllByClubId(club.getId())
                .stream()
                .map(ClubTagProjection::getTag)
                .toList();

            ClubSearchResult clubSearchResult = ClubSearchResult.builder()
                .clubId(club.getId())
                .clubName(club.getName())
                .clubImageUrl(thumbnail)
                .clubTags(clubTags)
                .clubState(String.valueOf(club.getState().getDesc()))
                .build();

            clubSearchResults.add(clubSearchResult);
        }
        return ClubSearchResponse.builder()
            .results(clubSearchResults)
            .build();
    }
}
