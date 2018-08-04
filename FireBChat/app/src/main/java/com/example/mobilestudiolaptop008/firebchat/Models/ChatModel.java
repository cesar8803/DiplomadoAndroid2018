package com.example.mobilestudiolaptop008.firebchat.Models;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class ChatModel {

    @Exclude
    private String chatId;
    private ArrayList<LocationModel> locations;



    public ChatModel(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

}
