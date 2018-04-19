package me.kamili.rachid.chatapp.view.users;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import me.kamili.rachid.chatapp.R;
import me.kamili.rachid.chatapp.view.authentication.AuthenticationActivity;
import me.kamili.rachid.chatapp.view.authentication.AuthenticationPresenter;

public class UsersActivity extends AppCompatActivity implements UsersContract.View{

    private UsersPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        presenter = new UsersPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    public void onLogOut(View view) {
        presenter.logOut(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLogout() {
        Intent intent = new Intent().setClass(getApplicationContext(), AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }
}
