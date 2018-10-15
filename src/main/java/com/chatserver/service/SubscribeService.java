package com.chatserver.service;

import com.chatserver.result.Result;

public interface SubscribeService {

    Result subscribe(long userId, long chatId);
}
