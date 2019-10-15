package vn.mrlongg71.ps09103_assignment.view.bill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

import es.dmoral.toasty.Toasty;
import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.adapter.RecyclerCustomerAdapter;
import vn.mrlongg71.ps09103_assignment.model.objectclass.Customer;
import vn.mrlongg71.ps09103_assignment.presenter.bill.PresenterChooseCustomer;

public class ChooseCustomerActivity extends AppCompatActivity implements IViewChooseCustomer {

    private Toolbar toolbar_ChooseCustomer;
    private PresenterChooseCustomer presenterChooseCustomer;
    private RecyclerView recyclerCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_customer);
        initView();
        setToolbar();
        presenterChooseCustomer.getListCustomer();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar_ChooseCustomer);
        toolbar_ChooseCustomer.setTitle("Choose Customer");
//        toolbar_ChooseCustomer.setNavigationIcon(R.drawable.ic_clear_white_24dp);
//        toolbar_ChooseCustomer.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

    }

    private void initView() {
        toolbar_ChooseCustomer = findViewById(R.id.toolbar_ChooseCustomer);
        toolbar_ChooseCustomer.setTitle("dffdf");
        presenterChooseCustomer = new PresenterChooseCustomer(this);
        recyclerCustomer = findViewById(R.id.recyclerCustomer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add_type) {
            createCustomer();
        }
        return super.onOptionsItemSelected(item);
    }

    private void createCustomer() {
        final Dialog dialog = new Dialog(ChooseCustomerActivity.this);
        dialog.setContentView(R.layout.custom_dialog_add_customer);
        final EditText edtEmailCustomer, edtPhoneCustomer, edtNameCustomer, edtPlaceCustomer;
        Button btnAddCustomerNewDialog;
        edtEmailCustomer = dialog.findViewById(R.id.edtEmailCustomer);
        edtPhoneCustomer = dialog.findViewById(R.id.edtPhoneCustomer);
        edtNameCustomer = dialog.findViewById(R.id.edtNameCustomer);
        edtPlaceCustomer = dialog.findViewById(R.id.edtPlaceCustomer);
        btnAddCustomerNewDialog = dialog.findViewById(R.id.btnAddCustomerNewDialog);
        btnAddCustomerNewDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtEmailCustomer.getText().length() > 0 && edtPhoneCustomer.getText().length() > 0 && edtNameCustomer.getText().length() > 0 && edtPlaceCustomer.getText().length() > 0) {
                    Customer customer = new Customer("", edtNameCustomer.getText().toString().trim(),
                            edtPhoneCustomer.getText().toString().trim(),
                            edtEmailCustomer.getText().toString().trim(),
                            edtPlaceCustomer.getText().toString().trim()
                    );
                    presenterChooseCustomer.getAddCustomer(customer);
                    dialog.dismiss();
                } else {
                    Toasty.error(getApplicationContext(), getString(R.string.error), Toasty.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public void displayListCustomer(List<Customer> customerList) {
        RecyclerCustomerAdapter recyclerCustomerAdapter = new RecyclerCustomerAdapter(ChooseCustomerActivity.this, R.layout.custom_list_user, customerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerCustomer.setLayoutManager(layoutManager);
        recyclerCustomer.setAdapter(recyclerCustomerAdapter);
        recyclerCustomerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onAddCustomerSuccess() {
        Toasty.success(getApplicationContext(), getString(R.string.success), Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void onAddCustomerFailed() {
        Toasty.error(getApplicationContext(), getString(R.string.error), Toasty.LENGTH_SHORT).show();

    }
}
