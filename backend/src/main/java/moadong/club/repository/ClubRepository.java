package moadong.club.repository;

import java.util.List;
import java.util.Optional;

import moadong.club.entity.Club;
import moadong.club.enums.ClubState;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends MongoRepository<Club, String> {
    Optional<Club> findClubById(ObjectId id);
    @Query("{'classification': {$regex: ?0, $options: 'i'}, 'division': {$regex: ?1, $options: 'i'}}")
    Optional<List<Club>> findClubByClassificationAndDivisionIgnoreCase(String classification, String division);
    @Query("{'state': ?0, 'classification': {$regex: ?1, $options: 'i'}, 'division': {$regex: ?2, $options: 'i'}}")
    Optional<List<Club>> findClubByStateAndClassificationAndDivisionIgnoreCase(ClubState clubState, String classification, String division);
}
