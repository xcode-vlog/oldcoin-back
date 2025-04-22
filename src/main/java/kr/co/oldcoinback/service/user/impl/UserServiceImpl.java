package kr.co.oldcoinback.service.user.impl;

import kr.co.oldcoinback.domain.request.user.CreateUserRequestDto;
import kr.co.oldcoinback.domain.request.user.UpdateUserConfirmRequestDto;
import kr.co.oldcoinback.domain.response.user.CustomUserDetail;
import kr.co.oldcoinback.domain.response.user.ResolvedUser;
import kr.co.oldcoinback.repository.user.UserRepository;
import kr.co.oldcoinback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public ResolvedUser createUser(CreateUserRequestDto dto) {
        userRepository.insertUser(dto);

        // TODO return userinfo
        final CustomUserDetail customUserDetail = userRepository.findByUsername(dto.getUserId());

        return ResolvedUser.fromSecurity(customUserDetail);
    }

    public int updateUserConfirm(UpdateUserConfirmRequestDto dto) {
        return userRepository.updateUserConfirm(dto);
    }

    public List<ResolvedUser> selectUserList() {
        return userRepository.selectUserList();
    }

}
