package com.example.restapi_test;

public class UserInsertOneRecord {
    // 서버로 보낼 요청값은 name, job이고 둘다 String타입 이다.
    String name, job;

    public UserInsertOneRecord(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
