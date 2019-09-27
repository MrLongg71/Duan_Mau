package vn.mrlongg71.ps09103_assignment.view.book;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.adapter.SpinerAdapter;
import vn.mrlongg71.ps09103_assignment.model.objectclass.Book;
import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;
import vn.mrlongg71.ps09103_assignment.presenter.book.IPresenterBookAdapter;
import vn.mrlongg71.ps09103_assignment.presenter.book.PresenterBook;


public class AddBookFragment extends Fragment implements IViewBook {


    private Button btnAddBook;
    private TextInputEditText edtBookName, edtAuthor, edtPrice, edtAmount;
    private SpinerAdapter adapterSpiner;
    private Spinner spinnerTypeBook;
    private PresenterBook presenterBook;
    private int positionSpiner = 0;
    private List<TypeBook> typeBookLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        initView(view);
        initEvent();

        return view;
    }

    private void initView(View view) {
        btnAddBook = view.findViewById(R.id.btnAddBook);
        edtBookName = view.findViewById(R.id.edtBookName);
        edtAmount = view.findViewById(R.id.edtAmount);
        edtAuthor = view.findViewById(R.id.edtAuthor);
        edtPrice = view.findViewById(R.id.edtPrice);
        spinnerTypeBook = view.findViewById(R.id.spinerTypeBook);
        presenterBook = new PresenterBook(this);
        typeBookLists = new ArrayList<>();
        presenterBook.getTypeBook();
    }

    private void initEvent() {
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionSpiner = spinnerTypeBook.getSelectedItemPosition();
                String bookname = edtBookName.getText().toString().trim();
                String author = edtAuthor.getText().toString().trim();
                String amount = edtAmount.getText().toString().trim();
                String pricestr = edtPrice.getText().toString().trim();




                if (checkValid(bookname, author, amount, pricestr)) {
                    Book book = new Book();
                    book.setBookname(edtBookName.getText().toString().trim());
                    book.setAmount(edtAmount.getText().toString().trim());
                    book.setAuthor(edtAuthor.getText().toString().trim());

                    double price = Double.parseDouble(pricestr);
                    book.setPrice(price);
                    book.setTypecode(typeBookLists.get(positionSpiner).getKey());
                    presenterBook.getAddBook(book);
                } else {
                    Toasty.error(getActivity(),getString(R.string.error),Toasty.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void displayListBook(List<Book> bookList, List<TypeBook> typeBookList) {

    }

    @Override
    public void displayListTypeBookSpiner(List<TypeBook> typeBookList) {
        typeBookLists.addAll(typeBookList);
        adapterSpiner = new SpinerAdapter(getActivity(),R.layout.custom_spiner,typeBookList);
        spinnerTypeBook.setAdapter(adapterSpiner);

    }

    @Override
    public void displayAddBookSucces() {
        getActivity().getSupportFragmentManager().popBackStack();
        Toasty.success(getActivity(),getString(R.string.success),Toasty.LENGTH_LONG).show();
    }

    @Override
    public void displayAddBookFailed() {
        Toasty.error(getActivity(),getString(R.string.error),Toasty.LENGTH_LONG).show();

    }

    @Override
    public void displayDeleteItemBookSuccess() {

    }

    @Override
    public void displayDeleteItemBookFailed() {

    }

    public boolean checkValid(String bookname, String amount, String author, String price) {

        if (bookname.length() == 0 || author.length() == 0 || amount.length() == 0 || price.length() == 0) {
            return false;
        } else {
            return true;
        }
    }


}
