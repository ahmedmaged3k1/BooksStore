package com.example.booksstore.Books;

import java.io.Serializable;

public class Books implements Serializable {
    private int bookId;
    private int bookImgUrl ;
    private String bookName ;
    private String bookAuthor;
    private int bookRate ;
    private int favourite ;
    private String type ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description ;

    public Books(int bookImgUrl, String bookName, String bookAuthor, int bookRate,int favourite,String type,String description) {
        this.bookImgUrl = bookImgUrl;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookRate = bookRate;
        this.favourite=favourite;
        this.type=type;
        this.description=description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public int getBookImgUrl() {
        return bookImgUrl;
    }

    public void setBookImgUrl(int bookImgUrl) {
        this.bookImgUrl = bookImgUrl;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookRate() {
        return bookRate;
    }

    public void setBookRate(int bookRate) {
        this.bookRate = bookRate;
    }
}
