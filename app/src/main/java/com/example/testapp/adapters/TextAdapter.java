package com.example.testapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.models.User;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ImageViewHolder> {
    private Context mContext;
    //list of single upload items
    private List<User> mUsers;
    private TextAdapter.OnItemClickListener mListener;

    public TextAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
    }

    @Override
    public TextAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.text_item, parent, false);
        return new TextAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, @SuppressLint("RecyclerView") int position) {

        User userCurrent = mUsers.get(position);
        holder.textViewName.setText(userCurrent.getFName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                /*

                User selectedItem = mUsers.get(position);
                final String selectedKey = selectedItem.getKey();


                //final String DOB=selectedItem.getDOB();
                //final String img=selectedItem.getImageUrl();

                Context context = v.getContext();


                Intent intent = new Intent(context, PostDetail.class);

                intent.putExtra("postKey", selectedKey);
                //intent.putExtra("name", name);
                //intent.putExtra("imageUrl", img);
                //intent.putExtra("name",Upload.getImageUrl());
                v.getContext().startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {

        return mUsers.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName;
        public ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            //imageView = itemView.findViewById(R.id.image_view_upload);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onClick(View v) {
            /*
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }*/
        }



        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever = menu.add(Menu.NONE, 1, 1, "Do whatever");
            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");

            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    switch (item.getItemId()) {
                        case 1:
                            mListener.onWhatEverClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(TextAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}
