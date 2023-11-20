package com.example.restapi_test;

public class UserGetOneRecord {
    // 서버로 보낸 응답값 4개중 String(name, job, createdAt)과 int(id)로 나눈다.
    String name, job, createdAt;
    int id;

    // UserGetOneRecord Getter
    // name값을 불러온다.
    public String getName() {
        return name;
    }

    // job값을 불러온다
    public String getJob() {
        return job;
    }

    // createdAt값을 불러온다.
    public String getCreatedAt() {
        return createdAt;
    }

    // 해당 record값의 id를 불러온다.
    public int getId() {
        return id;
    }

    // UserGetOneRecord Setter
    // name값을 입력한다.
    public void setName(String name) {
        this.name = name;
    }

    // job값을 입력한다.
    public void setJob(String job) {
        this.job = job;
    }

    // createdAt값을 입력한다.
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // id값을 입력한다.
    public void setId(int id) {
        this.id = id;
    }
}
