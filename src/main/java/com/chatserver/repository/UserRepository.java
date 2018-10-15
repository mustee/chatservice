package com.chatserver.repository;

import com.chatserver.models.User;
import com.chatserver.models.draft.UserDraft;

import java.util.Optional;

public interface UserRepository {

    long insert(UserDraft draft);

    int update(User user);

    Optional<User> get(long id);

    Optional<User> findByUsername(String username);

    int delete(long id);
}
