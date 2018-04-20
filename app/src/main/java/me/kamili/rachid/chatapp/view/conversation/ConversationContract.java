package me.kamili.rachid.chatapp.view.conversation;

import java.util.List;

import me.kamili.rachid.chatapp.model.Message;
import me.kamili.rachid.chatapp.model.User;
import me.kamili.rachid.chatapp.view.base.BasePresenter;
import me.kamili.rachid.chatapp.view.base.BaseView;

public interface ConversationContract {

    interface View extends BaseView {
        //specific methods for each view
        public void receiveMessages(List<Message> messages);

        public void receiveOtherUser(User otherUser);
    }

    interface Presenter extends BasePresenter<View> {
        //specific method for each view's presenter
    }
}
