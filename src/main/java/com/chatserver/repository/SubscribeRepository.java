package com.chatserver.repository;

import com.chatserver.models.draft.SubscribeDraft;

public interface SubscribeRepository {

    long insert(SubscribeDraft draft);

    int delete(long id);

}
