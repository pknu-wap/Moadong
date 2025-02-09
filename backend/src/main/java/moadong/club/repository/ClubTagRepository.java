package moadong.club.repository;

import moadong.club.entity.ClubTags;
import moadong.club.payload.dto.ClubTagProjection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ClubTagRepository extends MongoRepository<ClubTags, String> {
    Optional<List<ClubTagProjection>> findAllByClubId(String clubId);
}
