package moadong.club.repository;

import java.util.List;
import moadong.club.entity.ClubTags;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClubTagRepository extends MongoRepository<ClubTags, String> {

    List<ClubTags> findAllByClubId(Long clubId);
}
