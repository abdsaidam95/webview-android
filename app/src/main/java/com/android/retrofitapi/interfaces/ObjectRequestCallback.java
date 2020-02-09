package com.android.retrofitapi.interfaces;

import java.util.List;

public interface ObjectRequestCallback<T> {

    void onSuccess(T object);

    void onFailed();
}


