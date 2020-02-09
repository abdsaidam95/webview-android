package com.android.retrofitapi.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.retrofitapi.interfaces.ListRequestCallback;
import com.android.retrofitapi.interfaces.ObjectRequestCallback;
import com.android.retrofitapi.interfaces.ProcessRequestCallback;
import com.android.retrofitapi.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiController {

    private Context context;
    private Requests requests;
    private static ApiController instance;

    private ApiController(Context context) {
        this.context = context;
        requests = RetrofitSettings.getRetrofitInstance().create(Requests.class);
    }

    public static ApiController getInstance(Context context) {
        if (instance == null) {
            instance = new ApiController(context);
        }
        return instance;
    }


    public void getPosts(final ListRequestCallback<Post> listRequestCallback) {
        if (isConnectedToInternet()) {
            Call<List<Post>> call = requests.getPosts();
            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (response.isSuccessful()) {
                        listRequestCallback.onSuccess(response.body());
                    } else {
                        listRequestCallback.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    listRequestCallback.onFailed();
                }
            });
        }
    }

    public void createPost(Post post, final ObjectRequestCallback<Post> objectRequestCallback) {
        if (isConnectedToInternet()) {
            Call<Post> call = requests.createPost(post.getTittle(), post.getBody(), post.getUserId());
            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        objectRequestCallback.onSuccess(response.body());
                    } else {
                        objectRequestCallback.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    objectRequestCallback.onFailed();
                }
            });
        }
    }

    public void getPost(int id, final ObjectRequestCallback<Post> objectRequestCallback) {
        if (isConnectedToInternet()) {
            Call<Post> call = requests.getPost(id);
            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        objectRequestCallback.onSuccess(response.body());
                    } else {
                        objectRequestCallback.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    objectRequestCallback.onFailed();
                }
            });
        }
    }

    public void updatePost(Post post, final ObjectRequestCallback<Post> objectRequestCallback) {
        if (isConnectedToInternet()) {
            Call<Post> call = requests.updatePost(post.getId(), post.getTittle(), post.getBody(), post.getUserId());
            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        objectRequestCallback.onSuccess(response.body());
                    } else {
                        objectRequestCallback.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    objectRequestCallback.onFailed();
                }
            });
        }
    }

    public void deletePost(int id, final ProcessRequestCallback processRequestCallback) {
        if (isConnectedToInternet()) {
            Call<Post> call = requests.deletePost(id);
            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        processRequestCallback.onSuccess();
                    } else {
                        processRequestCallback.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    processRequestCallback.onFailed();
                }
            });
        }
    }

    public boolean isConnectedToInternet() {
        ConnectivityManager connManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI ||
                    activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to wifi
                return true;
            }
        }
        return false;
    }
}
