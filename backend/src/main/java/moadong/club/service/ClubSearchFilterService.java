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
public class ClubSearchFilterService {

    private final ClubRepository clubRepository;
    private final ClubInformationRepository clubInformationRepository;
    private final ClubTagRepository clubTagRepository;

    public ClubSearchResponse getClubsByFilter(
            String availability,
            String classification,
            String division
    ) {
        List<Club> clubs = searchClubsByFilter(availability, classification, division)
                .orElseThrow(() -> new RestApiException(ErrorCode.CLUB_NOT_FOUND));

        List<ClubSearchResult> clubSearchResults = new ArrayList<>();

        for (Club club : clubs) {
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
                    .division(club.getDivision())
                    .classification(club.getClassification())
                    .introduction(informationByClubId.getIntroduction())
                    .build();

            clubSearchResults.add(clubSearchResult);
        }
        return ClubSearchResponse.builder()
                .clubs(clubSearchResults)
                .build();
    }

    private Optional<List<Club>> searchClubsByFilter(String availability, String classification, String division) {
        if (availability.equals("all")) {
            return getClubsByClassificationAndDivision(classification, division);
        } else {
            return getClubsByStateAndClassificationAndDivision(availability, classification, division);
        }
    }

    private Optional<List<Club>> getClubsByClassificationAndDivision(String classification, String division) {
        if ("all".equals(classification) && "all".equals(division)) {
            return Optional.of(clubRepository.findAll()); // 모든 클럽 반환
        } else if ("all".equals(classification)) {
            return clubRepository.findClubByDivisionIgnoreCaseExact(division); // division만 필터링
        } else if ("all".equals(division)) {
            return clubRepository.findClubByClassificationIgnoreCaseExact(classification); // classification만 필터링
        } else {
            return clubRepository.findClubByClassificationAndDivisionIgnoreCaseExact(classification, division); // 둘 다 필터링
        }
    }

    private Optional<List<Club>> getClubsByStateAndClassificationAndDivision(String availability, String classification, String division) {
        ClubState clubState = ClubState.fromString(availability);

        if ("all".equals(classification) && "all".equals(division)) {
            return clubRepository.findClubByState(clubState); // 상태만 필터링
        } else if ("all".equals(classification)) {
            return clubRepository.findClubByStateAndDivisionIgnoreCaseExact(clubState, division); // 상태 + division 필터링
        } else if ("all".equals(division)) {
            return clubRepository.findClubByStateAndClassificationIgnoreCaseExact(clubState, classification); // 상태 + classification 필터링
        } else {
            return clubRepository.findClubByStateAndClassificationAndDivisionIgnoreCaseExact(clubState, classification, division); // 상태 + classification + division 필터링
        }
    }
}
