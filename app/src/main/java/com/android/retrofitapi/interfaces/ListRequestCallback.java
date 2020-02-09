package com.android.retrofitapi.interfaces;

import java.util.List;

public interface ListRequestCallback<T> {

    void onSuccess(List<T> objects);

    void onFailed();
}

