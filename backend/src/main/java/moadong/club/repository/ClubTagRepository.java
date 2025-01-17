package moadong.club.repository;

import java.util.List;
import moadong.club.entity.ClubTags;
import moadong.club.payload.dto.ClubTagProjection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClubTagRepository extends MongoRepository<ClubTags, String> {
    List<ClubTagProjection> findAllByClubId(String clubId);
}
