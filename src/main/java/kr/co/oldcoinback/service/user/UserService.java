package kr.co.oldcoinback.service.user;

import kr.co.oldcoinback.domain.request.user.CreateUserRequestDto;
import kr.co.oldcoinback.domain.request.user.UpdateUserConfirmRequestDto;
import kr.co.oldcoinback.domain.response.user.ResolvedUser;

import java.util.List;

public interface UserService {


    ResolvedUser createUser(CreateUserRequestDto dto);

    int updateUserConfirm(UpdateUserConfirmRequestDto dto);

    List<ResolvedUser> selectUserList();
}
