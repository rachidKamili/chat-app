package me.kamili.rachid.chatapp.view.users;

import android.content.Context;

import me.kamili.rachid.chatapp.view.base.BasePresenter;
import me.kamili.rachid.chatapp.view.base.BaseView;

public interface UsersContract {

    interface View extends BaseView {
        //specific methods for each view
        void onLogout();
    }

    interface Presenter extends BasePresenter<UsersContract.View> {
        //specific method for each view's presenter
        void logOut(Context context);
    }
}
