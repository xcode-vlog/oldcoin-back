package kr.co.oldcoinback.repository.user.mybatis;

import kr.co.oldcoinback.domain.request.user.CreateUserRequestDto;
import kr.co.oldcoinback.domain.request.user.UpdateUserConfirmRequestDto;
import kr.co.oldcoinback.domain.response.user.CustomUserDetail;
import kr.co.oldcoinback.domain.response.user.ResolvedUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    CustomUserDetail findByUsername(String username);

    int insertUser(CreateUserRequestDto dto);
    int updateUserConfirm(UpdateUserConfirmRequestDto dto);
    List<ResolvedUser> selectUserList();
}
