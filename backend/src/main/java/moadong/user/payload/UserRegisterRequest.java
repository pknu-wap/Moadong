package moadong.user.payload;

import moadong.user.entity.User;
import moadong.user.entity.UserInformation;

public record UserRegisterRequest(
        String email,
        String password,
        String name,
        String phoneNumber
) {
    public User toUserEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
    public UserInformation toUserInformationEntity(String userId){
        return UserInformation.builder()
                .userId(userId)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
