package vn.mrlongg71.ps09103_assignment.presenter.typebook;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;
import vn.mrlongg71.ps09103_assignment.model.typebook.ModelTypeBook;
import vn.mrlongg71.ps09103_assignment.view.typebook.IViewTypeBook;

public class PresenterTypeBook implements IPresenterTypeBook {
    private IViewTypeBook iViewTypeBook;
    private ModelTypeBook modelTypeBook;
    final List<TypeBook> typeBookList = new ArrayList<>();
    public PresenterTypeBook(IViewTypeBook iViewTypeBook) {
        this.iViewTypeBook = iViewTypeBook;
        modelTypeBook = new ModelTypeBook();
    }

    @Override
    public void getTypeBook() {
        typeBookList.clear();
        modelTypeBook.dowloadListTypeBook(this);
    }


    @Override
    public void resultgetTypeBook(TypeBook typeBook) {
        typeBookList.add(typeBook);
        if(typeBookList.size() != 0){
            iViewTypeBook.displayListType(typeBookList);
        }
    }

    @Override
    public void getAddTypeBook(TypeBook typeBook) {
        modelTypeBook.initAddTypeBook(typeBook,this);
    }

    @Override
    public void resultAddTypeBook(boolean success) {
        if(success){
            iViewTypeBook.displayAddTypeSucces();
        }else {
            iViewTypeBook.displayAddTypeFailed();
        }
    }
}
