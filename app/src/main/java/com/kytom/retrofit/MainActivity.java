package com.kytom.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.kytom.retrofit.Interface.Api;
import com.kytom.retrofit.modelo.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView MJsonTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MJsonTxt = findViewById(R.id.JsonTxt);

        getPost();

    }
    public void getPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:192.168.1.105//:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<List<Post>> call = api.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    MJsonTxt.setText("Codigo de error:"+response.code());
                    return;
                }
                List<Post> postList = response.body();
                for(Post post: postList){
                    String content = "";
                    content += "name:" + post.getName() + "\n";
                    content += "price:" + post.getPrice() + "\n";
                    content += "quantity:" + post.getQuantity() + "\n";
                    MJsonTxt.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                    MJsonTxt.setText(t.getMessage());
            }
        });

    }
}