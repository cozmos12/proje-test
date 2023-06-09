package com.example.demo.dto;

public class PostDtoLikeNumber {
    private   int id;
    private String text;
    private String title;
    private String userName;
    private Long userId;

    private int likeOfNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getLikeOfNumber() {
        return likeOfNumber;
    }

    public void setLikeOfNumber(int likeOfNumber) {
        this.likeOfNumber = likeOfNumber;
    }
}
