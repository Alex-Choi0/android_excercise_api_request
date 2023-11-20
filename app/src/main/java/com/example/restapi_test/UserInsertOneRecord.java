package com.example.restapi_test;

public class UserInsertOneRecord {
    // 서버로 보낼 요청값은 name, job이고 둘다 String타입 이다.
    String name, job;

    // UserInsertOneRecord Getter
    // name값을 불러온다.
    public String getName() {
        return name;
    }

    // job값을 불러온다.
    public String getJob() {
        return job;
    }

    // UserInsertOneRecord Setter
    // name값을 입력한다.
    public void setName(String name) {
        this.name = name;
    }

    // job값을입력한다.
    public void setJob(String job) {
        this.job = job;
    }
}
