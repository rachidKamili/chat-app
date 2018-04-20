package me.kamili.rachid.chatapp.view.users;

import android.content.Context;

import java.util.List;

import me.kamili.rachid.chatapp.model.Conversation;
import me.kamili.rachid.chatapp.model.User;
import me.kamili.rachid.chatapp.view.base.BasePresenter;
import me.kamili.rachid.chatapp.view.base.BaseView;

public interface UsersContract {

    interface View extends BaseView {
        //specific methods for each view
        void onLogout();
        void onReceiveUser(User user);
        void onReceiveUserList(List<User> userList);
        void onShowConversation(Conversation conversation);
    }

    interface Presenter extends BasePresenter<UsersContract.View> {
        //specific method for each view's presenter
        void logOut(Context context);
        void goToConversationWith(User user);
    }
}
