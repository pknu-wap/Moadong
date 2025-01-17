package moadong.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("club_tags")
@Getter
@Setter
public class ClubTags {

    @Id
    private String id;

    @NotNull
    private String clubId;

    @Column(length = 8)
    private String tag;
}
