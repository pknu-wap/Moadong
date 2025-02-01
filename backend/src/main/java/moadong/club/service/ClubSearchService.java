package moadong.club.service;

import lombok.AllArgsConstructor;
import moadong.club.entity.Club;
import moadong.club.payload.dto.ClubInformationSearchProjection;
import moadong.club.payload.dto.ClubSearchResult;
import moadong.club.payload.dto.ClubTagProjection;
import moadong.club.payload.response.ClubSearchResponse;
import moadong.club.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ClubSearchService {
    private final ClubKeywordSearchRepository clubKeywordSearchRepository;
    private final ClubInformationRepository clubInformationRepository;
    private final ClubTagRepository clubTagRepository;

    public ClubSearchResponse searchClubsByKeyword(String keyword){
        List<ClubSearchResult> clubSearchResults = clubKeywordSearchRepository.searchResult(keyword);

        return ClubSearchResponse.builder()
                .clubs(clubSearchResults)
                .build();
    }
}
