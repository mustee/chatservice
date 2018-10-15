package com.chatserver.result;

import java.util.List;

public class ListResult<T> extends Result {
    private List<T> items;
    public ListResult(List<T> items) {
        super(true);

        this.items = items;
    }


    public List<T> getItems() {
        return items;
    }

}
