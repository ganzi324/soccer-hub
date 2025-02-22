package com.ganzi.soccerhub.user.application.command;

import com.ganzi.soccerhub.common.SelfValidating;
import com.ganzi.soccerhub.common.annotation.Password;
import com.ganzi.soccerhub.user.domain.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddUserCommand extends SelfValidating<AddUserCommand> {

    @NotNull
    private final String name;

    @Email
    @NotNull
    private final String email;

    @Password(message = "대문자, 숫자, 특수문자가 하나 이상 포함된 8~50자 비밀번호")
    private final String password;

    private final String picture;

    @NotNull
    private final UserType userType;

    public AddUserCommand(String name, String email, String password, String picture, UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.userType = userType;

        this.validateSelf();
    }

    public static AddUserCommand createNormalUser(String name, String email, String password) {
        return new AddUserCommand(name, email, password, null, UserType.NORMAL);
    }

    public static AddUserCommand createSnsUser(String name, String email, String picture, UserType userType) {
        assert userType == UserType.NORMAL : "SNS 사용자는 NORMAL 타입이 될 수 없다.";
        return new AddUserCommand(name, email, null, picture, userType);
    }

}
