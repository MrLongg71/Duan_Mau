package vn.mrlongg71.ps09103_assignment.presenter.statistical;

import android.renderscript.Sampler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.KeyException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.mrlongg71.ps09103_assignment.model.objectclass.Bill;
import vn.mrlongg71.ps09103_assignment.model.objectclass.BillDetail;
import vn.mrlongg71.ps09103_assignment.model.objectclass.Book;
import vn.mrlongg71.ps09103_assignment.model.statistical.ModelStatistical;
import vn.mrlongg71.ps09103_assignment.view.statistical.IViewStatistical;

public class PresenterStatistical implements IPresenterStatistical {
    IViewStatistical iViewStatistical;
    List<Bill> billList = new ArrayList<>();
    List<BillDetail> billDetailListItem = new ArrayList<>();
    List<Book> bookList = new ArrayList<>();
    ModelStatistical modelStatistical;

    public PresenterStatistical(IViewStatistical iViewStatistical) {
        this.iViewStatistical = iViewStatistical;
        modelStatistical = new ModelStatistical();
    }

    @Override
    public void getListBill(int i, int month) {
        if (billList.size() == 0) {
            modelStatistical.initGetListBill(this);
        } else {
            statistical(i, month);
        }

    }

    private void statistical(int i, int month) {
        switch (i) {
            case 0:
                initStatisticalYesterDay();
                break;
            case 1:

                initStatisticalDay();
                break;
            case 2:
                initStatisticalMonth(month);
                break;
        }
    }

    @Override
    public void resultBillSuccess(Bill bill, List<String> keyBillDetails, Book book) {
        if (bill != null) {
            billList.add(bill);
            bookList.add(book);
            initStatisticalDay();
            initStatisticalYesterDay();
            initStatisticalMonth(0);
        }
    }

    @Override
    public void resultBillFailed() {
        iViewStatistical.onStatisticalFailed();
    }

    public void initStatisticalDay() {


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Bill> billListDay = new ArrayList<>();
        List<Book> bookListDay = new ArrayList<>();
        String[] dateCal = simpleDateFormat.format(calendar.getTime()).split("-");

        for (Bill bill : billList) {
            String[] dateBill = bill.getDateCreate().split("-");
            if (dateBill[0].equals(dateCal[0])) {
                billListDay.add(bill);
//                    for(int i = 0; i < keyBillDetails.size() ;i++){
//                        if(bill.getCodeBillDetail().equals(keyBillDetails.get(i))){
//                            initGetDetailsListBill(keyBillDetails.get(i));
//                        }
//
//                    }
//                    if(billDetailListItem.size() >0){

//                    }

//                    for (int i = 0; i < billListDay.size(); i++) {
//                        if (billListDay.get(i).getCodeBillDetail().equals(billDetailList.get(i).getCode())) {
//                            billDetailList.add(billDetailList.get(i));
//                        }
//
//                    }
            }
            bookListDay.clear();
            for (Book book : bookList) {
                String[] dateBook = book.getDate().split("-");
                if (dateBook[0].equals(dateCal[0])) {
                    bookListDay.add(book);
                }
                iViewStatistical.onStatisticalDay(billListDay, billDetailListItem, bookListDay);
            }

        }

//            for(int i  = 0; i < billListDay.size() ;i++){
//                if(billListDay.get(i).getCodeBillDetail().equals(billDetailList.get(i).getCode())){
//                    billDetailList.add(billDetailList.get(i));
//                }
//
//            }


    }

    public void initStatisticalYesterDay() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Bill> billListDay = new ArrayList<>();
        List<Book> bookListDay = new ArrayList<>();
        String[] dateCal = simpleDateFormat.format(calendar.getTime()).split("-");
        String yesterday = String.valueOf(Integer.parseInt(dateCal[0]) - 1);

        for (Bill bill : billList) {
            String[] dateBill = bill.getDateCreate().split("-");
            if (dateBill[0].equals(yesterday)) {
                billListDay.add(bill);
//                    for(int i = 0; i < keyBillDetails.size() ;i++){
//                        if(bill.getCodeBillDetail().equals(keyBillDetails.get(i))){
//                            initGetDetailsListBill(keyBillDetails.get(i));
//                        }
//
//                    }
//                    if(billDetailListItem.size() >0){

//                    }

//                    for (int i = 0; i < billListDay.size(); i++) {
//                        if (billListDay.get(i).getCodeBillDetail().equals(billDetailList.get(i).getCode())) {
//                            billDetailList.add(billDetailList.get(i));
//                        }
//
//                    }
            }
            bookListDay.clear();
            for (Book book : bookList) {
                String[] dateBook = book.getDate().split("-");
                if (dateBook[0].equals(yesterday)) {
                    bookListDay.add(book);
                }
                iViewStatistical.onStatisticalYesterday(billListDay, billDetailListItem, bookListDay);
            }

        }

//            for(int i  = 0; i < billListDay.size() ;i++){
//                if(billListDay.get(i).getCodeBillDetail().equals(billDetailList.get(i).getCode())){
//                    billDetailList.add(billDetailList.get(i));
//                }
//
//            }


    }

    public void initStatisticalMonth(final int month) {
        String[] dateCal;
        if (month != 0) {
            dateCal = new String[2];
            if (month < 10) {
                dateCal[1] = "0" + month;
            }  else {
                dateCal[1] = String.valueOf(month);

            }


        } else {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateCal = simpleDateFormat.format(calendar.getTime()).split("-");
        }
        List<Bill> billListDay = new ArrayList<>();
        List<Book> bookListDay = new ArrayList<>();
        for (Bill bill : billList) {
            String[] dateBill = bill.getDateCreate().split("-");
            if (dateBill[1].equals(dateCal[1])) {
                billListDay.add(bill);
//                    for(int i = 0; i < keyBillDetails.size() ;i++){
//                        if(bill.getCodeBillDetail().equals(keyBillDetails.get(i))){
//                            initGetDetailsListBill(keyBillDetails.get(i));
//                        }
//
//                    }
//                    if(billDetailListItem.size() >0){

//                    }

//                    for (int i = 0; i < billListDay.size(); i++) {
//                        if (billListDay.get(i).getCodeBillDetail().equals(billDetailList.get(i).getCode())) {
//                            billDetailList.add(billDetailList.get(i));
//                        }
//
//                    }
            }
            bookListDay.clear();
            for (Book book : bookList) {
                String[] dateBook = book.getDate().split("-");
                if (dateBook[1].equals(dateCal[1])) {
                    bookListDay.add(book);
                }
                iViewStatistical.onStatisticalMonth(billListDay, billDetailListItem, bookListDay);
            }

        }

//            for(int i  = 0; i < billListDay.size() ;i++){
//                if(billListDay.get(i).getCodeBillDetail().equals(billDetailList.get(i).getCode())){
//                    billDetailList.add(billDetailList.get(i));
//                }
//
//            }


    }

    private void initGetDetailsListBill(String key) {
        Log.d("ggg", key);
        DatabaseReference dataDetailsBill = FirebaseDatabase.getInstance().getReference().child("BillDetails").child(key);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot valueDetails : dataSnapshot.getChildren()) {
                    BillDetail billDetail = valueDetails.getValue(BillDetail.class);
                    billDetailListItem.add(billDetail);
                    Log.d("ggg", "------");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dataDetailsBill.addValueEventListener(valueEventListener);
    }


}
