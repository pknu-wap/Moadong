package moadong.user.listener;

import moadong.global.exception.ErrorCode;
import moadong.global.listener.BaseValidationListener;
import moadong.user.entity.UserInformation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public class UserInformationValidationListener extends BaseValidationListener<UserInformation> {
    public UserInformationValidationListener(Validator validator) {
        super(validator);
    }

    @Override
    protected Class<UserInformation> getEntityType() {
        return UserInformation.class;
    }

    @Override
    protected String getEntityName() {
        return "userInformation";
    }

    @Override
    protected ErrorCode getErrorCode() {
        return ErrorCode.USER_INVALID_FORMAT;
    }
}
