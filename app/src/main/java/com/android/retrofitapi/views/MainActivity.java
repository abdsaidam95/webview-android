package com.android.retrofitapi.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.retrofitapi.R;
import com.android.retrofitapi.api.ApiController;
import com.android.retrofitapi.interfaces.ListRequestCallback;
import com.android.retrofitapi.interfaces.ObjectRequestCallback;
import com.android.retrofitapi.interfaces.ProcessRequestCallback;
import com.android.retrofitapi.models.Post;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    public static final String TAG = "API_REQUESTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.txt);

        getPosts();
//        createPost();
//        getPost();
//        updatePost();
//        deletePost();
    }

    private void getPosts() {
        ApiController.getInstance(this).getPosts(new ListRequestCallback<Post>() {

            @Override
            public void onSuccess(List<Post> objects) {
                Log.d(TAG, "onSuccess: POSTS: " + objects.size());
                Toast.makeText(MainActivity.this, "xxxxxxxx", Toast.LENGTH_SHORT).show();


                textView.setText(objects.get(1).getBody());
            }

            @Override
            public void onFailed() {


            }
        });
    }

    private void createPost() {
        ApiController.getInstance(this).createPost(generatePost(), new ObjectRequestCallback<Post>() {
            @Override
            public void onSuccess(Post object) {
                Log.d(TAG, "onSuccess: New Post Title: " + object.getTittle());
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private void getPost() {
        ApiController.getInstance(this).getPost(1, new ObjectRequestCallback<Post>() {
            @Override
            public void onSuccess(Post object) {
                Log.d(TAG, "onSuccess: Post Title: " + object.getTittle());
            }

            @Override
            public void onFailed() {

            }
        });

    }

    private void updatePost() {
        Post post = generatePost();
        post.setId(1);
        ApiController.getInstance(this).updatePost(post, new ObjectRequestCallback<Post>() {
            @Override
            public void onSuccess(Post object) {
                Log.d(TAG, "onSuccess: UPDATED Post Title: " + object.getTittle());
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private void deletePost() {
        ApiController.getInstance(this).deletePost(1, new ProcessRequestCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: Post Deleted");
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private Post generatePost() {
        Post post = new Post();
        post.setTittle("New Title");
        post.setBody("New Body");
        post.setUserId(1);
        return post;
    }
}
