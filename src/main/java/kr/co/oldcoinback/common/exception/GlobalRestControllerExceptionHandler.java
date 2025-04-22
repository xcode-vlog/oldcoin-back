package kr.co.oldcoinback.common.exception;

import kr.co.oldcoinback.common.response.ResponseModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseModel handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return ResponseModel.ERROR(e);
    }
    @ExceptionHandler(Exception.class)
    public ResponseModel<Void> internalError(Exception error) {
        error.printStackTrace();
        return ResponseModel.ERROR(error);
    }

}
