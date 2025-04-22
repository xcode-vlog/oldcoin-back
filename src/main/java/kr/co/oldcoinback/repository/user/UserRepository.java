package kr.co.oldcoinback.repository.user;

import kr.co.oldcoinback.domain.request.user.CreateUserRequestDto;
import kr.co.oldcoinback.domain.request.user.UpdateUserConfirmRequestDto;
import kr.co.oldcoinback.domain.response.user.CustomUserDetail;
import kr.co.oldcoinback.domain.response.user.ResolvedUser;
import kr.co.oldcoinback.repository.user.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;



    public CustomUserDetail findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public int insertUser(CreateUserRequestDto dto) {
        return userMapper.insertUser(dto);
    }

    public int updateUserConfirm(UpdateUserConfirmRequestDto dto) {
        return userMapper.updateUserConfirm(dto);
    }

    public List<ResolvedUser> selectUserList() {
        return userMapper.selectUserList();
    }
}
