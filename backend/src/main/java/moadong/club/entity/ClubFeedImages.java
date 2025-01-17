package moadong.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("club_feed_images")
@Getter
@Setter
public class ClubFeedImages {

    @Id
    private String id;

    @NotNull
    private String clubId;

    @Column(length = 1024)
    private String image;
}
