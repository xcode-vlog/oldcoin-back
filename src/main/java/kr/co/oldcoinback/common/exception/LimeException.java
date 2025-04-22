package kr.co.oldcoinback.common.exception;

import kr.co.oldcoinback.common.enums.ResponseCode;

public class LimeException extends RuntimeException {

    private ResponseCode code;

    public LimeException(ResponseCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public LimeException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }

    public LimeException(ResponseCode code, Exception e) {
        super(e);
        this.code = code;
    }

    public LimeException(Exception e) {
        super(e);
        this.code = ResponseCode.INTERNAL_SERVER_ERROR;
    }
    public LimeException(String message) {
        super(message);
        this.code = ResponseCode.INTERNAL_SERVER_ERROR;
    }
    public LimeException(String message, Exception e) {
        super(message, e);
        this.code = ResponseCode.INTERNAL_SERVER_ERROR;
    }

    public LimeException(ResponseCode code, String message, Exception e) {
        super(message, e);
        this.code = code;
    }
}
