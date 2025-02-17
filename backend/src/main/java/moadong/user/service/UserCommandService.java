package moadong.user.service;

import com.mongodb.MongoWriteException;
import lombok.AllArgsConstructor;
import moadong.global.exception.ErrorCode;
import moadong.global.exception.RestApiException;
import moadong.user.entity.User;
import moadong.user.payload.UserRegisterRequest;
import moadong.user.repository.UserInformationRepository;
import moadong.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserCommandService {
    private final UserRepository userRepository;
    private final UserInformationRepository userInformationRepository;

    @Transactional
    public void registerUser(UserRegisterRequest userRegisterRequest) {
        try {
            User user = userRepository.save(userRegisterRequest.toUserEntity());
            userInformationRepository.save(userRegisterRequest.toUserInformationEntity(user.getId()));
        } catch (MongoWriteException e) {
            throw new RestApiException(ErrorCode.USER_ALREADY_EXIST);
        }
    }
}
