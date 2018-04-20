package me.kamili.rachid.chatapp.view.conversation;

import android.provider.ContactsContract;

import java.util.List;

import me.kamili.rachid.chatapp.model.Message;
import me.kamili.rachid.chatapp.model.User;
import me.kamili.rachid.chatapp.utils.DbManager;

public class ConversationPresenter implements ConversationContract.Presenter ,DbManager.IOnMessageData {

    private String otherId;
    private String conversationId;
    private ConversationContract.View view;
    private DbManager dbManager;

    public ConversationPresenter(String convId,String otherId) {
        dbManager = DbManager.getInstance(this);
        this.conversationId = convId;
        this.otherId = otherId;
    }

    @Override
    public void attachView(ConversationContract.View view) {
        this.view = view;
        dbManager.getUserById(otherId);
        dbManager.getMessages(conversationId);
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void sendMessage(String text, String conversationId) {
        dbManager.sendMessage(conversationId, text);
    }

    @Override
    public void onReceiveOtherUser(User otherUser) {
        this.view.receiveOtherUser(otherUser);
    }

    @Override
    public void onReceiveMessages(List<Message> messages) {
        this.view.receiveMessages(messages);
    }
}
