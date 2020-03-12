package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.UserResponse;
import com.example.myapplication.ui.SingleItemActivity;

import java.util.List;

public class RecyclerViewCustomAdapter extends  RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder>{
    private Context mContext;
    private List<UserResponse> mUsers;
    int userId;
    private static final String EXTRA_USER_ID = "com.example.myapplication.extras.EXTRA_USER_ID";
    public RecyclerViewCustomAdapter(Context context, List<UserResponse> users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_user_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.textUserName.setText(mUsers.get(position).name);
        holder.textUserEmail.setText(mUsers.get(position).email);
        holder.textUserAlias.setText(mUsers.get(position).company.bs);
        userId = mUsers.get(position).id;
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textUserName, textUserEmail, textUserAlias;

        CustomViewHolder(View itemView) {
            super(itemView);
            textUserName = itemView.findViewById(R.id.text_user_name);
            textUserEmail = itemView.findViewById(R.id.text_user_email);
            textUserAlias = itemView.findViewById(R.id.text_user_alias);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SingleItemActivity.class);
                    intent.putExtra(EXTRA_USER_ID, userId);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}