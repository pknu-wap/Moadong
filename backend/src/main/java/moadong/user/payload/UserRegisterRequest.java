package moadong.user.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import moadong.global.annotation.Korean;
import moadong.global.annotation.PhoneNumber;
import moadong.user.entity.User;
import moadong.user.entity.UserInformation;

public record UserRegisterRequest(
        @NotNull
        @Size(min = 5, max = 50)
        String email,
        @NotNull
        @Size(min = 8, max = 20)
        String password,
        @NotNull
        @Size(min = 1, max = 10)
        @Korean
        String name,
        @PhoneNumber
        String phoneNumber
) {
    public User toUserEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }

    public UserInformation toUserInformationEntity(String userId) {
        return UserInformation.builder()
                .userId(userId)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
