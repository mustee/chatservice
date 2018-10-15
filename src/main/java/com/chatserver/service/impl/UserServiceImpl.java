package com.chatserver.service.impl;

import com.chatserver.models.User;
import com.chatserver.models.draft.UserDraft;
import com.chatserver.repository.UserRepository;
import com.chatserver.result.EntityResult;
import com.chatserver.result.Result;
import com.chatserver.service.UserService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenStore tokenStore;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenStore tokenStore, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.tokenStore = tokenStore;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result signUp(String username, String password) {
        Preconditions.checkArgument(StringUtils.isNotBlank(password), "Password cannot be null" );
        Preconditions.checkArgument(StringUtils.isNotBlank(username), "Username cannot be null" );

        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return Result.failed("User already exists");
        }

        userRepository.insert(new UserDraft().draft().username(username).password(passwordEncoder.encode(password))
                .recieveMessage(false).dateAdded(new Date()).build());
        return Result.succeeded();
    }

    @Override
    public Result signOut(String refreshToken) {
        Preconditions.checkArgument(StringUtils.isNotBlank(refreshToken), "Token cannot be null" );

        tokenStore.removeAccessTokenUsingRefreshToken(() -> refreshToken);
        tokenStore.removeRefreshToken(() -> refreshToken);

        return Result.succeeded();
    }

    @Override
    public Result findByUsername(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        if(!user.isPresent()){
            return Result.failed("User does not exists");
        }

        return new EntityResult<>(user.get());
    }

    @Override
    public Result subscribe(long id) {
        Optional<User> user = userRepository.get(id);
        if(!user.isPresent()){
            return Result.failed("User does not exists");
        }

        if(user.get().shouldReceiveMessage()){
            return Result.failed("User already subscribed");
        }

        userRepository.update(new User(user.get().getId(), user.get().getUsername(), user.get().getPassword(), true, user.get().getDateAdded()));
        return Result.succeeded();
    }



    /*@Override
    public Result signIn(String username, String password) {
        Preconditions.checkArgument(StringUtils.isNotBlank(password), "Password cannot be null" );
        Preconditions.checkArgument(StringUtils.isNotBlank(username), "Username cannot be null" );

        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            return Result.failed("Username or password is not correct.");
        }

        if(!passwordHasher.verifyHash(password, user.get().getPassword())){
            return Result.failed("Username or password is not correct.");
        }

        return new EntityResult<>(user.get());
    }*/
}
