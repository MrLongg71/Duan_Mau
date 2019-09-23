package vn.mrlongg71.ps09103_assignment.view.typebook;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.dmoral.toasty.Toasty;
import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.adapter.RecyclerViewTypeBookAdapter;
import vn.mrlongg71.ps09103_assignment.library.ActionBarLib;
import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;
import vn.mrlongg71.ps09103_assignment.presenter.typebook.PresenterTypeBook;


public class TypebookFragment extends Fragment implements IViewTypeBook{

    private PresenterTypeBook presenterTypeBook;
    private RecyclerView recyclerTypeBook;
    private RecyclerViewTypeBookAdapter recyclerViewTypeBookAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_typebook, container, false);
        setActionBar();
        initView(view);
        setHasOptionsMenu(true);
        presenterTypeBook = new PresenterTypeBook(this);
        presenterTypeBook.getTypeBook();
        return view;
    }

    private void initView(View view) {
        recyclerTypeBook = view.findViewById(R.id.recyclerTypeBook);
    }

    private void setActionBar() {
        ActionBarLib.setSupportActionBar(getActivity(), getString(R.string.menu_tyle));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.custom_menu_add,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_add_type:
                createDialogInputAddType();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void createDialogInputAddType() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_add);
        EditText edtTypeCode,edtTypeName;
        Button btnAddTypeBookDialog;
        edtTypeCode = dialog.findViewById(R.id.edtTypeCode);
        edtTypeName = dialog.findViewById(R.id.edtTypeName);
        btnAddTypeBookDialog = dialog.findViewById(R.id.btnAddTypeBookDialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        doAddTypeBook(edtTypeCode,edtTypeName,btnAddTypeBookDialog);
        dialog.show();
    }

    private void doAddTypeBook(final EditText edtTypeCode,final EditText edtTypeName,Button btnAddTypeBookDialog ) {
        btnAddTypeBookDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typecode = edtTypeCode.getText().toString().trim();
                String typename = edtTypeName.getText().toString().trim();
                if(typecode.length() > 0 && typename.length() >0){
                    TypeBook typeBook = new TypeBook();
                    typeBook.setTypecode(typecode);
                    typeBook.setTypename(typename);
                    presenterTypeBook.getAddTypeBook(typeBook);
                }else {
                    Toasty.error(getActivity(), getString(R.string.error),Toasty.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void displayListType(List<TypeBook> typeBookList) {
        Log.d("kientra" , typeBookList.size() +  " -  "  );
        recyclerViewTypeBookAdapter = new RecyclerViewTypeBookAdapter(getActivity(),R.layout.custom_recycler_typebook,typeBookList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerTypeBook.setLayoutManager(layoutManager);
        recyclerTypeBook.setAdapter(recyclerViewTypeBookAdapter);
        recyclerViewTypeBookAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayAddTypeSucces() {
        recyclerViewTypeBookAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "fg", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void displayAddTypeFailed() {

    }
}
