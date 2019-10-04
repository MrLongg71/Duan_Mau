package vn.mrlongg71.ps09103_assignment.model.objectclass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Book implements Parcelable {
    private String bookcode,bookname,author,amount,typecode;
    private double price;
    private List<String> arrImagesBook;


    public Book(String bookcode, String bookname, String author, String amount, String typecode, double price, List<String> arrImagesBook) {
        this.bookcode = bookcode;
        this.bookname = bookname;
        this.author = author;
        this.amount = amount;
        this.typecode = typecode;
        this.price = price;
        this.arrImagesBook = arrImagesBook;
    }

    public Book() {
    }


    protected Book(Parcel in) {
        bookcode = in.readString();
        bookname = in.readString();
        author = in.readString();
        amount = in.readString();
        typecode = in.readString();
        price = in.readDouble();
        arrImagesBook = in.createStringArrayList();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public List<String> getArrImagesBook() {
        return arrImagesBook;
    }

    public void setArrImagesBook(List<String> arrImagesBook) {
        this.arrImagesBook = arrImagesBook;
    }

    public static Creator<Book> getCREATOR() {
        return CREATOR;
    }

    public String getBookcode() {
        return bookcode;
    }

    public void setBookcode(String bookcode) {
        this.bookcode = bookcode;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookcode);
        dest.writeString(bookname);
        dest.writeString(author);
        dest.writeString(amount);
        dest.writeString(typecode);
        dest.writeDouble(price);
        dest.writeStringList(arrImagesBook);
    }
}
