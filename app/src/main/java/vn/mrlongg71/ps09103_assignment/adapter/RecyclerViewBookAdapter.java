package vn.mrlongg71.ps09103_assignment.adapter;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.model.objectclass.Book;
import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;
import vn.mrlongg71.ps09103_assignment.presenter.book.IPresenterBookAdapter;

public class RecyclerViewBookAdapter extends RecyclerView.Adapter<RecyclerViewBookAdapter.ViewHolderBook> {
    private Context context;
    private int resource;
    private List<Book> bookList;
    private List<TypeBook> typeBookList;
    private IPresenterBookAdapter iPresenterBookAdapter;

    public RecyclerViewBookAdapter(Context context, int resource, List<Book> bookList,List<TypeBook> typeBookList,IPresenterBookAdapter iPresenterBookAdapter) {
        this.context = context;
        this.resource = resource;
        this.bookList = bookList;
        this.typeBookList = typeBookList;
        this.iPresenterBookAdapter = iPresenterBookAdapter;
    }

    public class ViewHolderBook extends RecyclerView.ViewHolder {
        CardView cardBook;
        ImageView imageBook;
        TextView txtBookName,txtTypeBook,txtPrice,txtAmount;
        public ViewHolderBook(@NonNull View itemView) {
            super(itemView);
            cardBook = itemView.findViewById(R.id.cardBook);
            imageBook = itemView.findViewById(R.id.imageBook);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            txtTypeBook = itemView.findViewById(R.id.txtTypeBook);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtAmount = itemView.findViewById(R.id.txtAmount);
        }
    }
    @NonNull
    @Override
    public ViewHolderBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolderBook viewHolderBook = new ViewHolderBook(view);
        return viewHolderBook;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolderBook holder, int position) {
        Book book = bookList.get(position);
        TypeBook typeBook = typeBookList.get(position);
        holder.txtBookName.setText(book.getBookname());
        holder.txtAmount.setText("Số lượng: "+book.getAmount());
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        String price = numberFormat.format(book.getPrice());
        holder.txtPrice.setText("Giá: "+price);
        holder.txtTypeBook.setText("Loại: "+typeBook.getTypename());
        eventCartBook(position,holder);
    }

    private void eventCartBook(final int position,final ViewHolderBook holder) {
        holder.cardBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context, holder.cardBook);
                popupMenu.getMenuInflater().inflate(R.menu.poupup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.editType) {
                            iPresenterBookAdapter.onEventEditItemClickListenerBook(position,bookList);
                        }
                        if (item.getItemId() == R.id.deleteType) {
                            iPresenterBookAdapter.onEventDeleteItemClickListenerBook(position,bookList);

                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


}
