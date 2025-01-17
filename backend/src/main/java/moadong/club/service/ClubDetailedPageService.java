package moadong.club.service;

import lombok.AllArgsConstructor;
import moadong.club.entity.Club;
import moadong.club.entity.ClubInformation;
import moadong.club.payload.dto.ClubFeedImageProjection;
import moadong.club.payload.dto.ClubTagProjection;
import moadong.club.payload.response.ClubDetailedPageResponse;
import moadong.club.repository.ClubFeedImageRepository;
import moadong.club.repository.ClubInformationRepository;
import moadong.club.repository.ClubRepository;
import moadong.club.repository.ClubTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClubDetailedPageService {
    private final ClubRepository clubRepository;
    private final ClubInformationRepository clubInformationRepository;
    private final ClubFeedImageRepository clubFeedImageRepository;
    private final ClubTagRepository clubTagRepository;

    public ClubDetailedPageResponse getClubDetailedPage(String clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new IllegalArgumentException("Club not found with ID: " + clubId));

        ClubInformation clubInformation = clubInformationRepository.findByClubId(clubId)

                .orElseThrow(() -> new IllegalArgumentException("Club not found with ID: " + clubId));

        List<String> clubFeedImages = clubFeedImageRepository.findAllByClubId(club.getId())
                .stream()
                .map(ClubFeedImageProjection::getImage)
                .toList();

        List<String> clubTags = clubTagRepository.findAllByClubId(club.getId())
                .stream()
                .map(ClubTagProjection::getTag)
                .toList();

        return ClubDetailedPageResponse.createClubDetailedPageResponse(
                club,
                clubInformation,
                clubFeedImages,
                clubTags
        );
    }
}
