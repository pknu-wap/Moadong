package moadong.club.repository;

import java.util.List;
import java.util.Optional;

import moadong.club.entity.Club;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends MongoRepository<Club, String> {

    List<Club> findAllByName(ObjectId name);

    Optional<Club> findClubById(ObjectId id);
}
