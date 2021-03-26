package com.example.myapplication.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class LoginActivity extends AppCompatActivity  {
    private EditText mstuendt_id, mpassword;
    private Button login;
    public static String token;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mstuendt_id = findViewById(R.id.et_user);
        mpassword = findViewById(R.id.et_password);
        login = findViewById(R.id.bt_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student_id=mstuendt_id.getText().toString();
                String password=mpassword.getText().toString();
                request(student_id,password);
            }
        });
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void request(String id,String password){
        Retrofit retrofit=new Retrofit
                .Builder().baseUrl("http://39.102.42.156:1333/api/v1/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
            LoginApi request = retrofit.create(LoginApi.class);
            Call<LoginResponse> call = request.getCall(new User(id,password));

            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful()==true){
                        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        //finish();
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        token = response.body().getToken();
                        Log.d("tag", "code"+response.body());
                    }else {
                        Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                    Toast.makeText(LoginActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                    throwable.printStackTrace();
                    Log.e("tag",throwable.getMessage());
                }
            });
        }
}
