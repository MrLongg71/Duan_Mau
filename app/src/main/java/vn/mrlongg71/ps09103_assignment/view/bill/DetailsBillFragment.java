package vn.mrlongg71.ps09103_assignment.view.bill;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.adapter.RecyclerViewBillDetailsAdapter;
import vn.mrlongg71.ps09103_assignment.library.ActionBarLib;
import vn.mrlongg71.ps09103_assignment.library.Dialog;
import vn.mrlongg71.ps09103_assignment.model.objectclass.Bill;
import vn.mrlongg71.ps09103_assignment.model.objectclass.BillDetail;
import vn.mrlongg71.ps09103_assignment.model.objectclass.Book;
import vn.mrlongg71.ps09103_assignment.model.objectclass.Customer;
import vn.mrlongg71.ps09103_assignment.model.objectclass.User;
import vn.mrlongg71.ps09103_assignment.presenter.bill.PresenterBillDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsBillFragment extends Fragment implements IViewBillDetails {
    private PresenterBillDetails presenterBillDetails;
    private TextView txtDateDetailsBill, txtCreateDetailsBill, txtCustomerDetailsBill, txtTotalDetailsBill, txtCodeDetailsBill;
    private RecyclerView recyclerDetailsBill;
    private Bill bill;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =
                inflater.inflate(R.layout.fragment_details_bill, container, false);
        initView(view);
        setActionToolbar();
        initGetBill();


        return view;
    }

    private void setActionToolbar() {
        ActionBarLib.setSupportActionBar(getActivity(),"Details Bill " );
    }

    private void initGetBill() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Dialog.DialogLoading(progressDialog, true);
            bill = bundle.getParcelable("billdetails");
            presenterBillDetails.getBillDetails(bill);
        }
    }

    private void initView(View view) {
        presenterBillDetails = new PresenterBillDetails(this);
        txtDateDetailsBill = view.findViewById(R.id.txtDateDetailsBill);
        txtCodeDetailsBill = view.findViewById(R.id.txtCodeDetailsBill);
        txtCreateDetailsBill = view.findViewById(R.id.txtCreateDetailsBill);
        txtCustomerDetailsBill = view.findViewById(R.id.txtCustomerDetailsBill);
        txtTotalDetailsBill = view.findViewById(R.id.txtTotalDetailsBill);
        recyclerDetailsBill = view.findViewById(R.id.recyclerDetailsBill);
        progressDialog = new ProgressDialog(getActivity());
    }


    @Override
    public void displayBillDetails(List<BillDetail> billDetailList, Customer customer, User user, List<Book> bookList) {
        ActionBarLib.setSupportActionBar(getActivity(),"Details Bill " + bill.getCode());
        txtCodeDetailsBill.setText(bill.getCode());
        txtDateDetailsBill.setText("Date: " + bill.getDateCreate());
        txtCreateDetailsBill.setText("Create Bill by: " + user.getName());
        txtCustomerDetailsBill.setText(customer.getName());
        txtTotalDetailsBill.setText(bill.getTotalPrice() + "đ");
        RecyclerViewBillDetailsAdapter recyclerViewBillDetailsAdapter = new RecyclerViewBillDetailsAdapter(getActivity(), R.layout.custom_book, bookList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerDetailsBill.setLayoutManager(layoutManager);
        recyclerDetailsBill.setAdapter(recyclerViewBillDetailsAdapter);
        recyclerViewBillDetailsAdapter.notifyDataSetChanged();
        Dialog.DialogLoading(progressDialog, false);

    }
}
