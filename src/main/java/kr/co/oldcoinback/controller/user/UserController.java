package kr.co.oldcoinback.controller.user;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.oldcoinback.common.enums.ResponseCode;
import kr.co.oldcoinback.common.exception.LimeException;
import kr.co.oldcoinback.common.response.ResponseModel;
import kr.co.oldcoinback.common.token.JwtTokenProvider;
import kr.co.oldcoinback.domain.request.user.CreateUserRequestDto;
import kr.co.oldcoinback.domain.request.user.LoginRequestDto;
import kr.co.oldcoinback.domain.request.user.UpdateUserConfirmRequestDto;
import kr.co.oldcoinback.domain.response.user.CustomUserDetail;
import kr.co.oldcoinback.domain.response.user.ResolvedUser;
import kr.co.oldcoinback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;



    @PostMapping("/signup")
    public ResponseModel<ResolvedUser> signup(@RequestBody CreateUserRequestDto requestDto, HttpServletResponse response) {

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        final ResolvedUser user = userService.createUser(requestDto);

        jwtTokenProvider.setResponseToken(user, response);

        return ResponseModel.OK(user);
    }

    @PostMapping("/login")
    public ResponseModel<ResolvedUser> authenticate(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) throws LimeException {
        Optional<UserDetails> optionalUser = Optional.ofNullable(userDetailsService.loadUserByUsername(requestDto.getUserId()));

        if(optionalUser.isEmpty()) {
            throw new LimeException(ResponseCode.USER_NOT_FOUND);
        }

        UserDetails user = optionalUser.get();


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, requestDto.getPassword(), user.getAuthorities());
        final Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        CustomUserDetail customUserDetail = (CustomUserDetail) authenticate.getPrincipal();
        final ResolvedUser resolvedUser = ResolvedUser.fromSecurity(customUserDetail);

        jwtTokenProvider.setResponseToken(resolvedUser, response);

        return ResponseModel.OK(resolvedUser);
    }

    @GetMapping("/logout")
    public ResponseModel<Void> logout(HttpServletResponse response) {
        jwtTokenProvider.removeResponseToken(response);
        return ResponseModel.OK(null);
    }

    @PutMapping("/user-confirm")
    public ResponseModel<Void> updateUserConfirm(@RequestBody UpdateUserConfirmRequestDto requestDto, @AuthenticationPrincipal ResolvedUser user) {
        userService.updateUserConfirm(requestDto);
        return ResponseModel.OK(null);
    }

    @GetMapping("/user-list")
    public ResponseModel<List<ResolvedUser>> getUserList() {
        final List<ResolvedUser> resolvedUsers = userService.selectUserList();
        return ResponseModel.OK(resolvedUsers);
    }


}
