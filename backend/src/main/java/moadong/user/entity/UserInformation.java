package moadong.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("user_information")
public class UserInformation {
    @Id
    private String id;
    @NotNull
    @Indexed(unique = true)
    private String userId;
    @NotNull
    private String name;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;
}
