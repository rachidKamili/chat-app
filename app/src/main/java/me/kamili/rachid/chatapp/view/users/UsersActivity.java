package me.kamili.rachid.chatapp.view.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.kamili.rachid.chatapp.R;
import me.kamili.rachid.chatapp.model.Conversation;
import me.kamili.rachid.chatapp.model.User;
import me.kamili.rachid.chatapp.utils.UsersAdapter;
import me.kamili.rachid.chatapp.view.authentication.AuthenticationActivity;

public class UsersActivity extends AppCompatActivity implements UsersContract.View , UsersAdapter.OnUserClickListener{

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<User> mUserList = new ArrayList<>();

    private UsersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        ButterKnife.bind(this);
        presenter = new UsersPresenter();

        bindRv();
    }

    private void bindRv() {
        mRecyclerView = findViewById(R.id.rvUsers);
        //mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new UsersAdapter(mUserList,this);
        mRecyclerView.setAdapter(mAdapter);
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

    @Override
    public void onReceiveUser(User user) {
        tvEmail.setText(user.getEmail());
        tvName.setText(user.getName());
    }

    @Override
    public void onReceiveUserList(List<User> userList) {
        mUserList.clear();
        mUserList.addAll(userList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShowConversation(Conversation conversation) {

        Toast.makeText(this, "Conversation!!!", Toast.LENGTH_SHORT).show();
        // TODO: 4/19/2018 Start the conversation activty
//        Intent intent = new Intent(getApplicationContext(), AuthenticationActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onItemClicked(User user) {
        presenter.goToConversationWith(user);
    }
}
