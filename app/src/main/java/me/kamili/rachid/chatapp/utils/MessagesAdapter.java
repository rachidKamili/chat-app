package me.kamili.rachid.chatapp.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.kamili.rachid.chatapp.R;
import me.kamili.rachid.chatapp.model.Message;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    public final int CURRENT_USER = 100;
    public final int OTHER_USER = 200;
    private List<Message> mDataset;
    private String otherUserId;

    public MessagesAdapter(List<Message> myDataset, String otherUserId) {
        mDataset = myDataset;
        this.otherUserId = otherUserId;
    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position).getSenderId().equals(otherUserId) ? OTHER_USER : CURRENT_USER;
    }

    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        switch (viewType) {
            case CURRENT_USER:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message_item_view, parent, false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message_other_item_view, parent, false);
                break;
        }

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {
        holder.tvText.setText(mDataset.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvText;

        public ViewHolder(View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
        }

    }

}
