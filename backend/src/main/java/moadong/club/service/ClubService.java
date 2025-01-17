package moadong.club.service;

import lombok.AllArgsConstructor;
import moadong.club.dto.ClubCreateRequest;
import moadong.club.entity.Club;
import moadong.club.entity.ClubInformation;
import moadong.club.enums.ClubState;
import moadong.club.repository.ClubRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

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

        return club.getId();
    }
}
