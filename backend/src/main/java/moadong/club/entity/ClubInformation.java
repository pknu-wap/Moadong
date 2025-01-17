package moadong.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import moadong.club.enums.ClubState;
import moadong.global.RegexConstants;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("club_information")
@Getter
@Setter
public class ClubInformation {

    @Id
    private String id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String clubId;

    @Column(length = 1024)
    private String thumbnail;

    @Column(length = 30)
    private String introduction;

    @Column(length = 5)
    private String clubPresidentName;

    @Pattern(regexp = RegexConstants.PHONE_NUMBER, message = "전화번호 형식이 올바르지 않습니다.")
    @Column(length = 13)
    private String telephoneNumber;

    private LocalDate recruitmentStart;

    private LocalDate recruitmentEnd;
}
