package com.example.mobilestudiolaptop008.firebchat.Models;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class DashBoarModel {

    @Exclude
    private String chatId;
    private ArrayList<UserModel> friends;

    public DashBoarModel() {
    }

    public DashBoarModel(String chatId, ArrayList<UserModel> friends) {
        this.chatId = chatId;
        this.friends = friends;
    }

    public DashBoarModel(String chatId) {
        this.chatId = chatId;
        friends = new ArrayList<>();

    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public ArrayList<UserModel> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<UserModel> friends) {
        this.friends = friends;
    }
}
