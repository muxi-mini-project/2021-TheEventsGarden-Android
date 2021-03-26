package com.example.myapplication.Login;

public class User {
    /**
     * flower:0
     * gold : 0
     * name : string
     * password : string
     * sex : 0
     * student_id : string
     * summary:string
     * user_picture : string
     */

    private Integer flower;
    private Integer gold;
    private String name;
    private String password;
    private Integer sex;
    private String student_id;
    private String summary;
    private String user_picture;
    public User(String student_id,String password){
        this.student_id=student_id;
        this.password=password;
    }

    public Integer getFlower() {
        return flower;
    }

    public void setFlower(Integer flower) {
        this.flower = flower;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }
}
