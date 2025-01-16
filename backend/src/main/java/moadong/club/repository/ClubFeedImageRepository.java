package moadong.club.repository;

import moadong.club.entity.ClubFeedImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubFeedImageRepository extends JpaRepository<ClubFeedImages, Long> {
    List<ClubFeedImages> findAllByClubId(Long clubId);
}
