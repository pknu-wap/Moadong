package moadong.club.repository;

import java.util.List;
import moadong.club.entity.Club;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends MongoRepository<Club, String> {

    List<Club> findAllByName(String name);
}
