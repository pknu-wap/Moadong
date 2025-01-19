package moadong.club.repository;

import java.util.List;
import moadong.club.entity.ClubInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClubInformationRepository extends MongoRepository<ClubInformation, String> {

    List<ClubInformation> findAllByClubId(Long clubId);
}
