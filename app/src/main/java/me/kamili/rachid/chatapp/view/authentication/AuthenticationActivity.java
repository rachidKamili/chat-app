package me.kamili.rachid.chatapp.view.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import me.kamili.rachid.chatapp.R;
import me.kamili.rachid.chatapp.view.users.UsersActivity;

import static me.kamili.rachid.chatapp.view.authentication.AuthenticationPresenter.RC_SIGN_IN;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationContract.View {

    private AuthenticationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        presenter = new AuthenticationPresenter();
        presenter.startActivityForResult(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);

        if (presenter.checkSession()) {
            // already signed in
            Intent intent = new Intent().setClass(getApplicationContext(), UsersActivity.class);
            startActivity(intent);
            finish();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // Successfully signed in
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent().setClass(getApplicationContext(), UsersActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Sign in failed
                Toast.makeText(this, "Sign-in error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
