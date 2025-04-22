package kr.co.oldcoinback.domain.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {

    private String userId;
    @Setter
    private String password;
    private String name;
    private String nickName;
    private String email;
    private String birth;
    private String hpNumber;
    private String userRole;
    private String confirm;
}
