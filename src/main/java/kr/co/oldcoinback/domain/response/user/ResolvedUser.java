package kr.co.oldcoinback.domain.response.user;

import lombok.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ResolvedUser {

    private String userSeq;
    private String userId;
    private String name;
    private String nickName;
    private String email;
    private String hpNumber;
    private String userRole;
    private String confirm;

    public static ResolvedUser fromSecurity(CustomUserDetail customUserDetail) {
        return ResolvedUser.builder()
                .userSeq(customUserDetail.getUserSeq())
                .userId(customUserDetail.getUserId())
                .name(customUserDetail.getName())
                .nickName(customUserDetail.getNickName())
                .email(customUserDetail.getEmail())
                .hpNumber(customUserDetail.getHpNumber())
                .userRole(customUserDetail.getUserRole())
                .confirm(customUserDetail.getConfirm())
                .build();
    }
    public static ResolvedUser fromMap(Map map) {
        return ResolvedUser.builder()
                .userSeq(map.get("userSeq").toString())
                .userId(map.get("userId").toString())
                .name(map.get("name").toString())
                .nickName(map.get("nickName").toString())
                .email(map.get("email").toString())
                .hpNumber(map.get("hpNumber").toString())
                .userRole(map.get("userRole").toString())
                .confirm(map.get("confirm").toString())
                .build();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();

        result.put("userSeq", userSeq);
        result.put("userId", userId);
        result.put("name", name);
        result.put("nickName", nickName);
        result.put("email", email);
        result.put("userRole", userRole);
        result.put("confirm", confirm);

        return result;
    }


}
