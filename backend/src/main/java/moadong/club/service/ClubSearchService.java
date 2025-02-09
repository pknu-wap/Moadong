package moadong.club.service;

import lombok.AllArgsConstructor;
import moadong.club.payload.dto.ClubSearchResult;
import moadong.club.payload.response.ClubSearchResponse;
import moadong.club.repository.ClubSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClubSearchService {
    private final ClubSearchRepository clubSearchRepository;

    public ClubSearchResponse searchClubsByKeyword(String keyword,
                                                   String recruitmentStatus,
                                                   String division,
                                                   String classification
    ) {
        List<ClubSearchResult> clubSearchResults = clubSearchRepository.searchResult(
                keyword,
                recruitmentStatus,
                division,
                classification
        );

        return ClubSearchResponse.builder()
                .clubs(clubSearchResults)
                .build();
    }
}
