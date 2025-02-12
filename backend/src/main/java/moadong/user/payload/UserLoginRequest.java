package moadong.user.payload;

public record UserLoginRequest(
    String email,
    String password
) {
}
