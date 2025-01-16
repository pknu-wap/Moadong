package moadong.club.repository;

import java.util.List;
import moadong.club.entity.ClubFeedImages;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubFeedImageRepository extends MongoRepository<ClubFeedImages, String> {
    List<ClubFeedImages> findAllByClubId(Long clubId);
}
