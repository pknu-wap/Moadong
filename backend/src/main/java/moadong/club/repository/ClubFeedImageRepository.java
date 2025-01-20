package moadong.club.repository;

import java.util.List;
import moadong.club.entity.ClubFeedImages;
import moadong.club.payload.dto.ClubFeedImageProjection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubFeedImageRepository extends MongoRepository<ClubFeedImages, String> {
    List<ClubFeedImageProjection> findAllByClubId(String clubId);
}
