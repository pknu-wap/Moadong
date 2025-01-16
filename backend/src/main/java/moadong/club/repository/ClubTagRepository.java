package moadong.club.repository;

import moadong.club.entity.ClubTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubTagRepository extends JpaRepository<ClubTags, Long> {
    List<ClubTags> findAllByClubId(Long clubId);
}
