package vn.mrlongg71.ps09103_assignment.view.book;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.developer.kalert.KAlertDialog;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.adapter.RecyclerViewBookAdapter;
import vn.mrlongg71.ps09103_assignment.library.ActionBarLib;
import vn.mrlongg71.ps09103_assignment.model.objectclass.Book;
import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;
import vn.mrlongg71.ps09103_assignment.presenter.book.IPresenterBookAdapter;
import vn.mrlongg71.ps09103_assignment.presenter.book.PresenterBook;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment implements IViewBook, IPresenterBookAdapter {
    private PresenterBook presenterBook;
    private RecyclerViewBookAdapter recyclerViewBookAdapter;
    private RecyclerView recyclerViewBook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_book, container, false);
        setActionBar();
        initView(view);
        setHasOptionsMenu(true);
        presenterBook = new PresenterBook(this);
        presenterBook.getBook();
        return view;
    }

    private void initView(View view) {
        recyclerViewBook = view.findViewById(R.id.recyclerBook);
    }

    private void setActionBar() {
        ActionBarLib.setSupportActionBar(getActivity(), getString(R.string.menu_book));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.custom_menu_add, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_type:
                AddBookFragment addBookFragment = new AddBookFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram, addBookFragment).addToBackStack(null).commit();

                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void displayListBook(List<Book> bookList,List<TypeBook> typeBookList) {
        recyclerViewBookAdapter = new RecyclerViewBookAdapter(getActivity(), R.layout.custom_book, bookList, typeBookList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBook.setLayoutManager(layoutManager);
        recyclerViewBook.setAdapter(recyclerViewBookAdapter);
    }

    @Override
    public void displayListTypeBookSpiner(List<TypeBook> typeBookList) {

    }


    @Override
    public void displayAddBookSucces() {

    }

    @Override
    public void displayAddBookFailed() {

    }

    @Override
    public void displayDeleteItemBookSuccess() {
        recyclerViewBookAdapter.notifyDataSetChanged();
        presenterBook.getBook();
        Toasty.success(getActivity(),getString(R.string.success),Toasty.LENGTH_LONG).show();
    }

    @Override
    public void displayDeleteItemBookFailed() {
        Toasty.error(getActivity(),getString(R.string.error),Toasty.LENGTH_LONG).show();
    }

    @Override
    public void onEventDeleteItemClickListenerBook(final int position, final List<Book> bookList) {
        final Book book = bookList.get(position);
        new KAlertDialog(getActivity(), KAlertDialog.ERROR_TYPE)
                .setContentText(getActivity().getString(R.string.wantDelete) + " sách " + book.getBookname())
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        //progressBarTypeBook.setVisibility(View.VISIBLE);
                        presenterBook.getItemDelete(book.getBookcode());
                        bookList.clear();
                        recyclerViewBookAdapter.notifyDataSetChanged();
                        kAlertDialog.dismissWithAnimation();
                    }
                })
                .setCancelText("No")
                .setCancelClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    @Override
    public void onEventEditItemClickListenerBook(int position, List<Book> bookList) {

    }

}
