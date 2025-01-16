package moadong.club.repository;

import moadong.club.entity.ClubInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubInformationRepository extends JpaRepository<ClubInformation, Long> {
    List<ClubInformation> findAllByClubId(Long clubId);
}
