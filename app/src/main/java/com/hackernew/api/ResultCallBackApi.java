package com.hackernew.api;

/**
 * Created by Hien on 5/29/2017.
 */

public interface ResultCallBackApi {
    void success(Object... params);
    void failed();
}
