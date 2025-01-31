package moadong.club.repository;

import java.util.Optional;
import moadong.club.entity.ClubInformation;
import moadong.club.payload.dto.ClubInformationSearchProjection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClubInformationRepository extends MongoRepository<ClubInformation, String> {

    Optional<ClubInformation> findByClubId(String clubId);
    Optional<ClubInformationSearchProjection> findInformationByClubId(String clubId);
}
