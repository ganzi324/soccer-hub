package com.ganzi.soccerhub.user.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import com.ganzi.soccerhub.common.annotation.Password;
import com.ganzi.soccerhub.user.domain.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Locale;

@Getter
public class AddUserCommand extends SelfValidating<AddUserCommand> {

    @NotNull
    private final String userKey;

    @NotNull
    private final String name;

    @Email
    private final String email;

    @Password(message = "대문자, 숫자, 특수문자가 하나 이상 포함된 8~50자 비밀번호")
    private final String password;

    private final String picture;

    @NotNull
    private final UserType userType;

    private AddUserCommand(String userKey, String name, String email, String password, String picture, UserType userType) {
        this.userKey = userKey;
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.userType = userType;

        this.validateSelf();
    }

    public static AddUserCommand createNormalUser(String name, String email, String password) {
        return new AddUserCommand(UserType.NORMAL.getCode().toLowerCase(Locale.ROOT) + "_" + email, name, email, password, null, UserType.NORMAL);
    }

    public static AddUserCommand createSnsUser(String id, String name, String email, String picture, UserType userType) {
        assert userType != UserType.NORMAL : "SNS 사용자는 NORMAL 타입이 될 수 없다.";
        return new AddUserCommand(userType.getCode().toLowerCase(Locale.ROOT) + "_" + id, name, email, null, picture, userType);
    }

}
