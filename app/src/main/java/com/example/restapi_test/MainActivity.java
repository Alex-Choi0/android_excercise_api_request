package com.example.restapi_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class MainActivity extends AppCompatActivity {

    // Get요청을 보내는 버튼
    Button sendGetRequestBtn;
    // 응답받은 데이터를 표시하는 TextView
    TextView responseData;
    // 유저의 ID를 입력할수 있는 입력창
    EditText inputUserId;

    // API요청후 응답받을 값을 정의한다.
    RequestUser requestUser;

    // SINGLE USER에서 받는 응답의 INTERFACE만들기
    interface RequestUser{
        // Get요청을 주는 주는 주소는 /api/users/{유저ID}
        @GET("/api/users/{uid}")
        // Call은 해당 API를 요청시 서버에서 받을수 있는 데이터 양식(JSON)을 정의해 준다. UserSingleData대로 서버에서 응답이 온다.
        // 변수 uid는 @GET요청의 {uid}와 연결하기 위해서 앞단에 @Path("uid")를 추가하여 요청시 해당 uid값이 바르게 입력되도록 한다.
        Call<UserSingleData> getOneUser(@Path("uid") int uid);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main.xml의 View와 Java를 연결한다.
        sendGetRequestBtn = findViewById(R.id.button);
        responseData = findViewById(R.id.textView);
        inputUserId = findViewById(R.id.editTextNumber);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in") // Base URL을 입력한다.
                .addConverterFactory(GsonConverterFactory.create()) // 응답받은 JSON타입 데이터를 Gson을 이용하여 JAVA에서 읽을수 있게 변환한다.
                .build(); // retrofit을 build한다.

        requestUser= retrofit.create(RequestUser.class); // Request요청이 가능하도록 requestUser를 생성

        // 버튼을 클릭하면 발생할 이벤트 작성
        sendGetRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uid = Integer.parseInt(inputUserId.getText().toString());
                requestUser.getOneUser(uid).enqueue(new Callback<UserSingleData>() {
                    @Override
                    public void onResponse(Call<UserSingleData> call, Response<UserSingleData> response) {
                        // 정상적으로 응답을 받을시
                        responseData.setText("first name : "+response.body().data.first_name + "\n" + "last name : " + response.body().data.last_name);
                    }

                    @Override
                    public void onFailure(Call<UserSingleData> call, Throwable t) {
                        // 에러 발생시
                        responseData.setText(t.getMessage());
                    }
                });
            }
        });
    }
}