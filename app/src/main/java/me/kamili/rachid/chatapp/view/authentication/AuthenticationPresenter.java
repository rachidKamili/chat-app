package me.kamili.rachid.chatapp.view.authentication;

import android.app.Activity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;


public class AuthenticationPresenter implements AuthenticationContract.Presenter {

    static final int RC_SIGN_IN = 123;
    private AuthenticationContract.View view;

    @Override
    public void attachView(AuthenticationContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void startActivityForResult(Activity activity) {
        activity.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.FacebookBuilder().build(),
                                new AuthUI.IdpConfig.TwitterBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    public boolean checkSession() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    @Override
    public void startUsersActivity() {
        view.onStartUsersActivity();
    }
}
