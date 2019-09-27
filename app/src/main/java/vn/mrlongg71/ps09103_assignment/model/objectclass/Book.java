package vn.mrlongg71.ps09103_assignment.model.objectclass;

public class Book {
    private String bookcode,bookname,author,amount,typecode;
    private double price;

    public Book(String bookcode, String bookname, String author, double price, String amount, String typecode) {
        this.bookcode = bookcode;
        this.bookname = bookname;
        this.author = author;
        this.price = price;
        this.amount = amount;
        this.typecode = typecode;
    }

    public Book() {
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
}
