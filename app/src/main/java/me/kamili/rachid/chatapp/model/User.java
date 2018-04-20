package me.kamili.rachid.chatapp.model;

import java.util.Map;

public class User {
    private String uid;
    private String email;
    private String name;
    private String photoUrl;
    private Map<String, String> conversations;

    public User(){}

    public User(String uid, String email, String name, String photoUrl, Map<String, String> conversations) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.photoUrl = photoUrl;
        this.conversations = conversations;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Map<String, String> getConversations() {
        return conversations;
    }

    public void setConversations(Map<String, String> conversations) {
        this.conversations = conversations;
    }
}
