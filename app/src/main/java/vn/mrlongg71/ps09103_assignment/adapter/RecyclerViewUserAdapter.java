package vn.mrlongg71.ps09103_assignment.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.model.objectclass.User;

public class RecyclerViewUserAdapter extends RecyclerView.Adapter<RecyclerViewUserAdapter.ViewHolderUser> {
    private Context context;
    private List<User> userList;
    private int layout;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public RecyclerViewUserAdapter(Context context, List<User> userList, int layout) {
        this.context = context;
        this.userList = userList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        ViewHolderUser viewHolderUser = new ViewHolderUser(view);
        return viewHolderUser;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderUser holder, int position) {
        final User user = userList.get(position);
        storageReference.child("user").child(user.getImage()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Glide.with(context)
                        .load(task.getResult().toString())
                        .into(holder.imgUser);
            }
        });

        holder.txtName.setText(user.getName());
        holder.txtEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolderUser extends RecyclerView.ViewHolder {
        CircleImageView imgUser;
        TextView txtName,txtEmail;
        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);
            imgUser = (CircleImageView) itemView.findViewById(R.id.imgUser);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);

        }
    }
}
