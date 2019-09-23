package vn.mrlongg71.ps09103_assignment.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.kalert.KAlertDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;
import vn.mrlongg71.ps09103_assignment.model.typebook.ModelTypeBook;
import vn.mrlongg71.ps09103_assignment.presenter.typebook.PresenterTypeBook;
import vn.mrlongg71.ps09103_assignment.view.typebook.IViewTypeBook;
import vn.mrlongg71.ps09103_assignment.view.typebook.TypebookFragment;

public class RecyclerViewTypeBookAdapter extends RecyclerView.Adapter<RecyclerViewTypeBookAdapter.ViewHolder> {
    private Context context;
    private int resource;
    private List<TypeBook> typeBookList;
    private DatabaseReference dataType = FirebaseDatabase.getInstance().getReference().child("TypeBook");

    public RecyclerViewTypeBookAdapter(Context context, int resource, List<TypeBook> typeBookList) {
        this.context = context;
        this.resource = resource;
        this.typeBookList = typeBookList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgTypeBook;
        TextView txtTypeCode,txtTypeName;
        CardView cardType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTypeBook = itemView.findViewById(R.id.imgTypeBook);
            txtTypeCode = itemView.findViewById(R.id.txtTypeCode);
            txtTypeName = itemView.findViewById(R.id.txtTypeName);
            cardType = itemView.findViewById(R.id.cartType);
        }
    }
    @NonNull
    @Override
    public RecyclerViewTypeBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewTypeBookAdapter.ViewHolder holder, final int position) {
        TypeBook typeBook = typeBookList.get(position);
        holder.txtTypeCode.setText(typeBook.getTypecode());
        holder.txtTypeName.setText(typeBook.getTypename());
        eventCartType(holder,position);

    }

    private void eventCartType(final ViewHolder holder,final int position) {
        holder.cardType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context,holder.cardType);
                popupMenu.getMenuInflater().inflate(R.menu.poupup_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() ==  R.id.editType){
                            eventDialogEditType(position);
                        }
                        if(item.getItemId() ==  R.id.deleteType){
                            eventDialogDeleteType(position);
                        }

                        return false;
                    }
                });
                popupMenu.show();


            }
        });
    }

    private void eventDialogDeleteType(final int position) {
        final TypeBook typeBook = typeBookList.get(position);
        new KAlertDialog(context,KAlertDialog.ERROR_TYPE)
                .setContentText(context.getString(R.string.wantDelete) + " loại " + typeBook.getTypecode() )
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(final KAlertDialog kAlertDialog) {
                        dataType.child(typeBook.getKey()).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){

                                    new ModelTypeBook().dowloadListTypeBook(null);
                                    typeBookList.remove(typeBookList.get(position));
                                    notifyDataSetChanged();
                                    Toasty.success(context,context.getString(R.string.success),Toasty.LENGTH_LONG).show();
                                    kAlertDialog.dismissWithAnimation();
                                }
                            }
                        });
                    }
                })
                .setCancelClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismissWithAnimation();
                    }
                })
                .show();


    }

    private void eventDialogEditType(final int position) {
        final TypeBook typeBook = typeBookList.get(position);
        final android.app.Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog_add);
        final EditText edtTypeCode,edtTypeName;
        Button btnAddTypeBookDialog;
        edtTypeCode = dialog.findViewById(R.id.edtTypeCode);
        edtTypeName = dialog.findViewById(R.id.edtTypeName);
        btnAddTypeBookDialog = dialog.findViewById(R.id.btnAddTypeBookDialog);
        btnAddTypeBookDialog.setText(context.getString(R.string.edit));
        edtTypeCode.setText(typeBook.getTypecode());
        edtTypeName.setText(typeBook.getTypename());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        btnAddTypeBookDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeBook.setTypecode(edtTypeCode.getText().toString().trim());
                typeBook.setTypename(edtTypeName.getText().toString().trim());
                dataType.child(typeBook.getKey()).setValue(typeBook).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toasty.success(context,context.getString(R.string.success),Toasty.LENGTH_LONG).show();
                            notifyDataSetChanged();
                            notifyItemChanged(position);
                            dialog.dismiss();
                        }else{
                            dialog.dismiss();
                            Toasty.error(context,context.getString(R.string.error),Toasty.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return typeBookList.size();
    }


}
