package vn.mrlongg71.ps09103_assignment.model.book;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vn.mrlongg71.ps09103_assignment.model.objectclass.Book;
import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;
import vn.mrlongg71.ps09103_assignment.presenter.book.PresenterBook;

public class ModelBook {
    DatabaseReference noteRoot = FirebaseDatabase.getInstance().getReference();

    public void dowloadListBook(final PresenterBook presenterBook){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataBook = dataSnapshot.child("Book");
                for(DataSnapshot valueBook : dataBook.getChildren()){
                    Book book = valueBook.getValue(Book.class);


                    DataSnapshot dataType =  dataSnapshot.child("TypeBook").child(book.getTypecode());
                    TypeBook typeBook = dataType.getValue(TypeBook.class);
                    presenterBook.resultgetBook(book,typeBook);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


        noteRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    public void dowloadListTypeBook(final PresenterBook presenterBook){
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TypeBook typeBook = dataSnapshot.getValue(TypeBook.class);
                presenterBook.resultgetTypeBook(typeBook);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
       noteRoot.child("TypeBook").addChildEventListener(childEventListener);
    }
    public void initAddBook(Book book, final PresenterBook presenterBook){
        String key = noteRoot.child("Book").push().getKey();
        book.setBookcode(key);
        noteRoot.child("Book").child(key).setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    presenterBook.resultAddBook(true);
                }else {
                    presenterBook.resultAddBook(false);
                }
            }
        });
    }
    public void initDeleteBook(String key, final PresenterBook presenterBook){
        noteRoot.child("Book").child(key).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    presenterBook.resultDeleteTypeBook(true);
                }else {
                    presenterBook.resultDeleteTypeBook(false);
                }
            }
        });
    }
}
