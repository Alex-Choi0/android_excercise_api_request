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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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
        // @Header추가하고 "Authorization"에 대한 헤더를 만든다. 해당 헤더는 String값이 들어가기 때분에 아래처럼 코드를 수정한다.
        Call<UserSingleData> getOneUser(@Header ("Authorization") String authToken, @Path("uid") int uid);

        // Post요청을 주는 주소는 /api/users
        @POST("/api/users")
        Call<UserGetOneRecord> postOneUser(@Body UserInsertOneRecord users);

    }

    Button sendPostRequestBtn;
    EditText inputUserName;
    EditText inputUserJob;
    TextView responseDataPost;

    // API의 POST요청후 응답받을 값을 저의
    UserGetOneRecord requestInsertUser;
    UserInsertOneRecord createOneUserRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main.xml의 View와 Java를 연결한다.(GET 부분)
        sendGetRequestBtn = findViewById(R.id.button);
        responseData = findViewById(R.id.textView);
        inputUserId = findViewById(R.id.editTextNumber);

        // activity_main.xml의 View와 Java를 연결합니다(POST 부분)
        sendPostRequestBtn = findViewById(R.id.button2);
        inputUserName = findViewById(R.id.editTextName);
        inputUserJob = findViewById(R.id.editTextJob);
        responseDataPost = findViewById(R.id.textViewJob);

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
                // JWT토큰은 헤더의 첫 부분에 Bearer(한칸 공백) 먼저 추가하고 뒤에 토큰을 넣습니다.
                String token = "Bearer " + "testToekn";
                // GET요청할때 Header에 토큰(token)을 넣고 서버에 요청을 한다.
                requestUser.getOneUser(token, uid).enqueue(new Callback<UserSingleData>() {
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


        sendPostRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOneUserRecord = new UserInsertOneRecord(
                        inputUserName.getText().toString(),
                        inputUserJob.getText().toString()
                ); // 서버에 보낼 요청값을 생성합니다.
                // createOneUserRecord.setName(inputUserName.getText().toString()); // name값을 입력합니다.
                // createOneUserRecord.setJob(inputUserJob.getText().toString()); // job값을 입력합니다.

                // 해당 createOneUserRecord데이터를 서버쪽으로 요청합니다.
                requestUser.postOneUser(createOneUserRecord).enqueue(new Callback<UserGetOneRecord>() {
                    @Override
                    public void onResponse(Call<UserGetOneRecord> call, Response<UserGetOneRecord> response) {
                        // 정상적으로 응답이 올경우 아래와 같이 TextView에 표시합니다.
                        responseDataPost.setText(
                                "id : " + response.body().getId() + "\n"
                                + "name : " + response.body().getName() + "\n"
                                + "job : " + response.body().getJob() + "\n"
                                + "createdAt : " + response.body().getCreatedAt() + "\n"

                        );
                    }

                    @Override
                    public void onFailure(Call<UserGetOneRecord> call, Throwable t) {
                        responseData.setText(t.getMessage());

                    }
                });

            }
        });

    }
}