package me.kamili.rachid.chatapp.view.conversation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.kamili.rachid.chatapp.R;
import me.kamili.rachid.chatapp.model.Message;
import me.kamili.rachid.chatapp.model.User;
import me.kamili.rachid.chatapp.utils.MessagesAdapter;

public class ConversationActivity extends AppCompatActivity implements ConversationContract.View{

    @BindView(R.id.tvOtherUserName)
    TextView tvOtherUserName;
    @BindView(R.id.etTextMessage)
    EditText etTextMessage;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Message> mMessages = new ArrayList<>();

    private ConversationPresenter presenter;
    private String conversationId;
    private String otherUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        ButterKnife.bind(this);

        conversationId = getIntent().getStringExtra("conversationId");
        otherUserId = getIntent().getStringExtra("otherUserId");

        presenter = new ConversationPresenter(conversationId,otherUserId);
        bindRv();
    }

    private void bindRv() {
        mRecyclerView = findViewById(R.id.rvMessages);
        //mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MessagesAdapter(mMessages, otherUserId);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "EROOR", Toast.LENGTH_SHORT).show();
    }

    public void onSendMessage(View view) {
        String text = etTextMessage.getText().toString().trim();
        if (!text.isEmpty()) {
            presenter.sendMessage(text, conversationId);
            etTextMessage.setText("");
        }
    }

    @Override
    public void receiveMessages(List<Message> messages) {
        mMessages.clear();
        mMessages.addAll(messages);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.smoothScrollToPosition(mMessages.size()-1);
    }

    @Override
    public void receiveOtherUser(User otherUser) {
        tvOtherUserName.setText(otherUser.getName());
    }
}
