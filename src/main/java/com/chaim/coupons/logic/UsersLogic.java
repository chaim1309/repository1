package com.chaim.coupons.logic;

import com.chaim.coupons.conts.Const;
import com.chaim.coupons.dal.IUserDal;
import com.chaim.coupons.dto.SuccessfulLoginData;
import com.chaim.coupons.dto.UserDto;
import com.chaim.coupons.dto.UserLoginData;
import com.chaim.coupons.entitys.UserEntity;
import com.chaim.coupons.enums.ErrorTypes;
import com.chaim.coupons.exceptions.ServerException;

import com.chaim.coupons.utils.JWTUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UsersLogic {
    private IUserDal usersDal;

    @Autowired
    public UsersLogic(IUserDal usersDal) {
        this.usersDal = usersDal;
    }

    public Long createUser(UserEntity user) throws ServerException {

        validateUser(user);
        existByUserName(user.getUserName());
        try {
            usersDal.save(user);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Failed to create user " + user.toString(), e);
        }

        return user.getId();
    }


    public String login(UserLoginData userLoginData) throws ServerException, JsonProcessingException {

        SuccessfulLoginData successfulLoginData = usersDal.login(userLoginData.getUserName(), userLoginData.getPassword());
        if (successfulLoginData == null) {
            throw new ServerException(ErrorTypes.LOGIN_FAILURE, "failed to login " + userLoginData.getUserName() + userLoginData.getPassword());
        }
        String token = JWTUtils.createJWT(successfulLoginData);
        return token;
    }

    public void removeUser(long id) throws ServerException {
        try {
            usersDal.deleteById(id);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Failed to remove user " + id, e);
        }

    }

    public void updateUser(UserEntity user) throws ServerException {

        validateUser(user);
        existByUserNameOtherId(user.getUserName(), user.getId());
        try {
            usersDal.save(user);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Failed to update user " + user.getId(), e);
        }

    }


    public UserDto getUser(long id) throws ServerException {
        UserDto user;
        try {
            user = usersDal.findUserById(id);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Error in getUser(), id = " + id, e);
        }

        return user;

    }

    public List<UserDto> getUsers(int pageNumber) throws ServerException {
        List<UserDto> users;
        Pageable pageable = PageRequest.of(pageNumber - 1, Const.AMOUNT_OF_ITEMS_PER_PAGE);
        try {
            users = usersDal.findAll(pageable);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Error in getUsers", e);
        }
        return users;
    }


    void validateUser(UserEntity user) throws ServerException {
        validatePassword(user.getPassword());
        validateUserName(user.getUserName());

    }

    void existByUserName(String userName) throws ServerException {
        if (usersDal.existsByUserName(userName)) {
            throw new ServerException(ErrorTypes.USER_ALREADY_EXIST,  userName);
        }
    }

    void existByUserNameOtherId(String userName, Long id) throws ServerException {
        if (usersDal.isUserNameOccupiedByOtherId(userName, id)) {
            throw new ServerException(ErrorTypes.USER_NAME_ALREADY_EXIST, userName);
        }

    }

    private void validateUserName(String userName) throws ServerException {
        if (userName.isBlank()) {
            throw new ServerException(ErrorTypes.USER_NAME_IS_NULL, " The user name is null " + userName);
        }
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);
        if (!matcher.matches()) {
            throw new ServerException(ErrorTypes.INVALID_USER_NAME, " the  user name invalid " + userName);
        }

    }

    private void validatePassword(String password) throws ServerException {
        if (password.isBlank()) {
            throw new ServerException(ErrorTypes.PASSWORD_IS_NULL, " The the password is null " + password);
        }
        int length = password.length();
        if (length > 8 && length < 15) {

        }
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasLetterOrDigit = false;


        for (int i = 0; i < length; i++) {
            char c = password.charAt(i);
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLetterOrDigit(c)) {
                hasLetterOrDigit = true;
            }
        }
        if (!hasLowerCase || !hasUpperCase || !hasLetterOrDigit) {
            throw new ServerException(ErrorTypes.INVALID_PASSWORD, " the  password invalid" + password);
        }
    }

}
