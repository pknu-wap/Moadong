package moadong.global.listener;

import moadong.global.exception.ErrorCode;
import moadong.global.exception.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

public abstract class BaseValidationListener<T> {
    private final Validator validator;
    public BaseValidationListener(Validator validator) {
        this.validator = validator;
    }

    @EventListener
    public void onBeforeSave(BeforeSaveEvent<?> event) {
        Object source = event.getSource();
        if (getEntityType().isInstance(source)) {
            T entity = getEntityType().cast(source);
            validate(entity, getEntityName());
        }
    }

    private void validate(T target, String objectName) {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(target, objectName);
        validator.validate(target, errors);

        if (errors.hasErrors()) {
            throw new RestApiException(getErrorCode());
        }
    }

    protected abstract Class<T> getEntityType();

    protected abstract String getEntityName();
    protected abstract ErrorCode getErrorCode();
}
