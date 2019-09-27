package vn.mrlongg71.ps09103_assignment.presenter.book;

import java.lang.reflect.Type;

import vn.mrlongg71.ps09103_assignment.model.objectclass.Book;
import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;

public interface IPresenterBook {
    //***get List Book***/
    void getBook();
    void resultgetBook(Book book, TypeBook typeBook);

    void getTypeBook();
    void resultgetTypeBook(TypeBook typeBook);

    //****Add Item Type****//
    void getAddBook(Book book);

    void resultAddBook(boolean success);
    //****Delete Item Adapter****//
    void getItemDelete(String key);

    void resultDeleteTypeBook(boolean success);
}
