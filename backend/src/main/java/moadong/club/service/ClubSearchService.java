package moadong.club.service;

import lombok.AllArgsConstructor;
import moadong.club.payload.dto.ClubSearchResult;
import moadong.club.payload.response.ClubSearchResponse;
import moadong.club.repository.ClubInformationRepository;
import moadong.club.repository.ClubKeywordSearchRepository;
import moadong.club.repository.ClubTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClubSearchService {
    private final ClubKeywordSearchRepository clubKeywordSearchRepository;
    private final ClubInformationRepository clubInformationRepository;
    private final ClubTagRepository clubTagRepository;

    public ClubSearchResponse searchClubsByKeyword(String keyword) {
        List<ClubSearchResult> clubSearchResults = clubKeywordSearchRepository.searchResult(keyword);

        return ClubSearchResponse.builder()
                .clubs(clubSearchResults)
                .build();
    }
}
