package me.kamili.rachid.chatapp.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.kamili.rachid.chatapp.model.Conversation;
import me.kamili.rachid.chatapp.model.Message;
import me.kamili.rachid.chatapp.model.User;

public class DbManager {

    private static IOnRetreiveData listener;

    private static DbManager instance;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private DatabaseReference convRef;

    public DbManager() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //convRef = mDatabase.child("conversations");

        mAuth = FirebaseAuth.getInstance();
    }

    public static DbManager getInstance(Object o) {
        if (instance == null) {
            instance = new DbManager();
        }
        listener = (IOnRetreiveData) o;
        return instance;
    }

    public User getUser() {
        FirebaseUser userFB = mAuth.getCurrentUser();
        if (userFB != null) {
            User user = new User(userFB.getUid(),userFB.getEmail(), userFB.getDisplayName(), userFB.getPhotoUrl().toString(), new HashMap<String, String>());
            userRef = mDatabase.child("users").child(userFB.getUid());
            userRef.child("email").setValue(user.getEmail());
            userRef.child("name").setValue(user.getName());
            userRef.child("photoUrl").setValue(user.getPhotoUrl());
            userRef.child("uid").setValue(user.getUid());
            return user;
        }
        return null;
    }

    public void receiveOrCreateConversation(final String userId, final String otherId) {

        mDatabase.child("users").child(otherId).child("conversations")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Map<String, String> conversations = (Map<String, String>) dataSnapshot.getValue();
                        if (conversations != null && conversations.containsKey(userId)) {
                            String key = conversations.get(userId);
                            conversationFromKey(key);
                        } else {
                            String key = mDatabase.child("conversations").push().getKey();
                            Conversation conversation = new Conversation(key,Arrays.asList(userId, otherId), new HashMap<String, Message>());

                            //Save to /conversations/$convId
                            mDatabase.child("conversations").child(key).setValue(conversation);
                            listener.onRetreiveConversation(conversation);

                            //Save to /users/$userid/converstations
                            Map<String, String> convValue = new HashMap<>();
                            convValue.put(otherId, key);
                            mDatabase.child("users").child(userId).child("conversations").setValue(convValue);

                            //Save to /users/$otherid/converstations
                            Map<String, String> convOtherValue = new HashMap<>();
                            convOtherValue.put(userId, key);
                            mDatabase.child("users").child(otherId).child("conversations").setValue(convOtherValue);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void conversationFromKey(String key) {
        mDatabase.child("conversations").child(key)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Conversation conversation = dataSnapshot.getValue(Conversation.class);
                        listener.onRetreiveConversation(conversation);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void getAllUsers() {
        mDatabase.child("users")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> list = dataSnapshot.getChildren();

                        // Getting current user Id
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        // Filter User
                        List<User> userList = new ArrayList<>();
                        for (DataSnapshot dataSnapshot1 : list) {
                            if (!dataSnapshot1.getKey().equals(uid)) {
                                userList.add(dataSnapshot1.getValue(User.class));
                            }
                        }

                        listener.onRetreiveUserList(userList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public interface IOnRetreiveData {
        public void onRetreiveConversation(Conversation conversation);
        public void onRetreiveUserList(List<User> userList);
    }

}
