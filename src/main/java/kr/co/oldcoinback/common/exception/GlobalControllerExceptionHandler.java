package kr.co.oldcoinback.common.exception;

import kr.co.oldcoinback.common.response.ResponseModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseModel<Void> internalError(Exception error) {
        return ResponseModel.ERROR(error);
    }
}
