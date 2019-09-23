package vn.mrlongg71.ps09103_assignment.presenter.typebook;

import java.util.List;

import vn.mrlongg71.ps09103_assignment.model.objectclass.TypeBook;

public interface IPresenterTypeBook {
    void getTypeBook();
    void resultgetTypeBook(TypeBook typeBook);

    void getAddTypeBook(TypeBook typeBook);
    void resultAddTypeBook(boolean success);
}
