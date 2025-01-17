package moadong.club.service;

import lombok.AllArgsConstructor;
import moadong.club.dto.ClubCreateRequest;
import moadong.club.dto.ClubUpdateRequest;
import moadong.club.entity.Club;
import moadong.club.entity.ClubInformation;
import moadong.club.enums.ClubState;
import moadong.club.repository.ClubInformationRepository;
import moadong.club.repository.ClubRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubInformationRepository clubInformationRepository;

    public String createClub(ClubCreateRequest request) {
        Club club = Club.builder()
            .name(request.name())
            .classification(request.classification())
            .division(request.division())
            .state(ClubState.UNAVAILABLE)
            .build();
        clubRepository.save(club);

        ClubInformation clubInformation = ClubInformation.builder()
            .clubId(club.getId())
            .build();
        clubInformationRepository.save(clubInformation);

        return club.getId();
    }

    public String updateClub(ClubUpdateRequest request) {
        Club club = clubRepository.findById(request.clubId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 클럽입니다."));
        ClubInformation clubInformation = clubInformationRepository.findByClubId(request.clubId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 클럽 정보입니다."));

        club.update(request);
        clubRepository.save(club);

        clubInformation.update(request);
        clubInformationRepository.save(clubInformation);

        return club.getId();
    }
}
