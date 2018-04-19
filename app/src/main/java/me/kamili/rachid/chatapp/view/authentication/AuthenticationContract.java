package me.kamili.rachid.chatapp.view.authentication;

import android.app.Activity;

import me.kamili.rachid.chatapp.view.base.BasePresenter;
import me.kamili.rachid.chatapp.view.base.BaseView;

public interface AuthenticationContract {
    //interface to communicate from presenter to view
    interface View extends BaseView {
        //specific methods for each view
    }

    interface Presenter extends BasePresenter<View> {
        //specific method for each view's presenter
        void startActivityForResult(Activity activity);
        boolean checkSession();

    }

}
