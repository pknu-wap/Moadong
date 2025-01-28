package moadong.club.service;

import lombok.AllArgsConstructor;
import moadong.club.entity.Club;
import moadong.club.enums.ClubState;
import moadong.club.payload.dto.ClubInformationSearchProjection;
import moadong.club.payload.dto.ClubSearchResult;
import moadong.club.payload.dto.ClubTagProjection;
import moadong.club.payload.response.ClubSearchResponse;
import moadong.club.repository.ClubInformationRepository;
import moadong.club.repository.ClubRepository;
import moadong.club.repository.ClubTagRepository;
import moadong.global.exception.ErrorCode;
import moadong.global.exception.RestApiException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClubSearchService {

    private final ClubRepository clubRepository;
    private final ClubInformationRepository clubInformationRepository;
    private final ClubTagRepository clubTagRepository;

    public ClubSearchResponse getClubs(
            String availability,
            String classification,
            String division
    ) {
        Optional<List<Club>> clubs;
        if (availability.equals("all")) {
            clubs = clubRepository.findClubByClassificationAndDivisionIgnoreCase(classification, division);
        } else {
            clubs = clubRepository.findClubByStateAndClassificationAndDivisionIgnoreCase(
                    ClubState.fromString(availability),
                    classification,
                    division
            );
        }
        clubs.orElseThrow(() -> new RestApiException(ErrorCode.CLUB_NOT_FOUND));

        List<ClubSearchResult> clubSearchResults = new ArrayList<>();
        for (Club club : clubs.get()) {
            ClubInformationSearchProjection informationByClubId = clubInformationRepository.findInformationByClubId(club.getId())
                    .orElse(ClubInformationSearchProjection.empty());

            List<String> tags = clubTagRepository.findAllByClubId(club.getId())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(ClubTagProjection::getTag)
                    .toList();

            ClubSearchResult clubSearchResult = ClubSearchResult.builder()
                    .id(club.getId())
                    .name(club.getName())
                    .logo(informationByClubId.getLogo())
                    .tags(tags)
                    .state(String.valueOf(club.getState().getDesc()))
                    .division(informationByClubId.getDescription())
                    .classification(club.getClassification())
                    .build();

            clubSearchResults.add(clubSearchResult);
        }
        return ClubSearchResponse.builder()
                .clubs(clubSearchResults)
                .build();
    }
}
