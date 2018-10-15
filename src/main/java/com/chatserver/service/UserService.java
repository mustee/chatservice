package com.chatserver.service;

import com.chatserver.result.Result;

public interface UserService {

    Result signUp(String username, String password);

    Result signOut(String refreshToken);

    Result findByUsername(String name);

    Result subscribe(long id);
}
