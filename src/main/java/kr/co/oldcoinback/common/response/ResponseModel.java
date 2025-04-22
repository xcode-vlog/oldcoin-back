package kr.co.oldcoinback.common.response;

import kr.co.oldcoinback.common.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseModel<T> {

    private String code;
    private String message;
    private T data;

    public static <T> ResponseModel<T> OK(T data) {
        return ResponseModel.<T>builder()
                .code(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .data(data)
                .build();
    }

    public static ResponseModel<Void> ERROR() {
        return ResponseModel.<Void>builder()
                .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                .message(ResponseCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }

    public static ResponseModel<Void> ERROR(ResponseCode responseCode) {
        return ResponseModel.<Void>builder()
                .code(responseCode.getCode())
                .message(responseCode.getMessage())
                .build();
    }

    public static ResponseModel<Void> ERROR(ResponseCode responseCode, String message) {
        return ResponseModel.<Void>builder()
                .code(responseCode.getCode())
                .message(message)
                .build();
    }

    public static ResponseModel<Void> ERROR(ResponseCode responseCode, Exception e) {
        return ResponseModel.<Void>builder()
                .code(responseCode.getCode())
                .message(e.getMessage())
                .build();
    }

    public static ResponseModel<Void> ERROR(Exception e) {
        return ResponseModel.<Void>builder()
                .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                .message(e.getMessage())
                .build();
    }



}
