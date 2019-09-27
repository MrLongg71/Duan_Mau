package vn.mrlongg71.ps09103_assignment.view.book;

import java.util.List;

import vn.mrlongg71.ps09103_assignment.model.objectclass.Book;
import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;

public interface IViewBook {
    void displayListBook(List<Book> bookList,List<TypeBook> typeBookList);
    void displayListTypeBookSpiner(List<TypeBook> typeBookList);

    void displayAddBookSucces();
    void displayAddBookFailed();

    void displayDeleteItemBookSuccess();
    void displayDeleteItemBookFailed();
}

