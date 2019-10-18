package vn.mrlongg71.ps09103_assignment.model.statistical;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import vn.mrlongg71.ps09103_assignment.model.objectclass.Bill;
import vn.mrlongg71.ps09103_assignment.model.objectclass.BillDetail;
import vn.mrlongg71.ps09103_assignment.model.objectclass.Book;
import vn.mrlongg71.ps09103_assignment.presenter.statistical.PresenterStatistical;

public class ModelStatistical {
    public  void initGetListBill(final PresenterStatistical presenterStatistical){
        DatabaseReference noteRoot = FirebaseDatabase.getInstance().getReference();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataBill = dataSnapshot.child("Bill");
                if(!dataBill.exists()){
                    presenterStatistical.resultBillFailed();
                }

                for(DataSnapshot valueListBill : dataBill.getChildren()){
                    Bill bill = valueListBill.getValue(Bill.class);
                    DataSnapshot dataDetailsBill = dataSnapshot.child("BillDetails");
                    List<String> keyBillDetailsList = new ArrayList<>();
                    for(DataSnapshot valueDetailsBill : dataDetailsBill.getChildren()){
                        String key = valueDetailsBill.getKey();
                        keyBillDetailsList.add(key);
                    }
                    DataSnapshot dataBook = dataSnapshot.child("Book");
                    for(DataSnapshot valueBook : dataBook.getChildren()){
                        Book book = valueBook.getValue(Book.class);
                        presenterStatistical.resultBillSuccess(bill,keyBillDetailsList,book);

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        noteRoot.addValueEventListener(valueEventListener);
    }


}
