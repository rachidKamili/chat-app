package me.kamili.rachid.chatapp.model;

import java.util.List;
import java.util.Map;

public class Conversation {
    private String id;
    private List<String> members;
    private Map<String,Message> messages;

    public Conversation() {}

    public Conversation(String id,List<String> members, Map<String, Message> messages) {
        this.id = id;
        this.members = members;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Map<String, Message> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, Message> messages) {
        this.messages = messages;
    }
}
