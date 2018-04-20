package me.kamili.rachid.chatapp.view.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import me.kamili.rachid.chatapp.model.Conversation;
import me.kamili.rachid.chatapp.model.User;
import me.kamili.rachid.chatapp.utils.DbManager;


public class UsersPresenter implements UsersContract.Presenter, DbManager.IOnRetreiveData {

    private UsersContract.View view;
    private DbManager dbManager;

    public UsersPresenter() {}

    @Override
    public void attachView(UsersContract.View view) {
        this.view = view;
        dbManager = DbManager.getInstance(this);
        User user = dbManager.getUser();
        if (user != null) {
            this.view.onReceiveUser(user);
            dbManager.getAllUsers();
        }
    }

    @Override
    public void detachView() {
        //this.view = null;
    }

    @Override
    public void logOut(Context context) {
        AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        view.onLogout();
                    }
                });
    }

    @Override
    public void onRetreiveConversation(Conversation conversation, String otherId) {
        view.onShowConversation(conversation,otherId);
    }

    @Override
    public void onRetreiveUserList(List<User> userList) {
        if (view != null)
            view.onReceiveUserList(userList);
    }

    @Override
    public void goToConversationWith(User user) {
        dbManager.receiveOrCreateConversation(
                FirebaseAuth.getInstance().getCurrentUser().getUid(),
                user.getUid()
        );
    }
}
