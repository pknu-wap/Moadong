package moadong.club.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import moadong.club.enums.ClubState;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("club")
@Getter
@Setter
public class Club {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String classification;

    @NotNull
    private String division;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ClubState state;
}
