package me.kamili.rachid.chatapp.view.users;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class UsersPresenter implements UsersContract.Presenter{

    private UsersContract.View view;

    @Override
    public void attachView(UsersContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
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
}
