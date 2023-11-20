package com.example.restapi_test;

public class UserSingleData {

    DataClass data;
    SupportClass support;

    public DataClass getData() {
        return data;
    }

    public SupportClass getSupport() {
        return support;
    }

    public void setData(DataClass data) {
        this.data = data;
    }

    public void setSupport(SupportClass support) {
        this.support = support;
    }



    // SINGLE USER의 응답에서 data부분의 요소들을 받을수 있는 class
    class  DataClass{
        // data의 요소인 email, first_name, last_name, avater는 모두 String이기 때문에 String변수 하나고 정의
        String email, first_name, last_name, avater;
        // data의 요소중에서 유일하게 id는 숫자이다. 따라서 int로 정의한다.
        int id;

        public String getEmail() {
            return email;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getAvater() {
            return avater;
        }

        public int getId() {
            return id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public void setAvater(String avater) {
            this.avater = avater;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    // SINGLE USER의 응답에서 support부부의 요소를 받을수 있는 class
    class SupportClass{
        // support의 두개의 키값은 모두 String이기 때문에 String변수로 정의한다.
        String url, text;


        public String getUrl() {
            return url;
        }

        public String getText() {
            return text;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
