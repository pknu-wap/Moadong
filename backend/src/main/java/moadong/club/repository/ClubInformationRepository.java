package moadong.club.repository;

import java.util.List;
import java.util.Optional;
import moadong.club.entity.ClubInformation;
import moadong.club.payload.dto.ClubThumbnailProjection;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClubInformationRepository extends MongoRepository<ClubInformation, String> {

    Optional<ClubInformation> findByClubId(String clubId);
    Optional<ClubThumbnailProjection> findThumbnailByClubId(String clubId);
    Optional<ClubInformation> findByThumbnail(@Unique String thumbnail);
}
