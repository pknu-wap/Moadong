package moadong.user.listener;

import moadong.global.exception.ErrorCode;
import moadong.global.listener.BaseValidationListener;
import moadong.user.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public class UserValidationListener extends BaseValidationListener<User> {

    public UserValidationListener(Validator validator) {
        super(validator);
    }

    @Override
    protected Class<User> getEntityType() {
        return User.class;
    }

    @Override
    protected String getEntityName() {
        return "user";
    }

    @Override
    protected ErrorCode getErrorCode() {
        return ErrorCode.USER_INVALID_FORMAT;
    }
}
