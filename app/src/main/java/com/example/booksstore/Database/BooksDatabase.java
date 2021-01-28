package com.example.booksstore.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.booksstore.Books.Books;
import com.example.booksstore.Presenter.BooksPresenter;

import java.util.ArrayList;

public class BooksDatabase extends SQLiteOpenHelper {
    private String databaseName ="Books";
    private String bookId ="bookId";
    private String bookImgUrl = "bookImgUrl";
    private String bookName ="bookName";
    private String bookAuthor ="bookAuthor";
    private String bookRate ="bookRate";
    private String favourite ="isFav" ;
    private String type ="type" ;
    private String description ="bookDetails";



    public BooksDatabase(@Nullable Context context) {
        super(context, "Books", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Books ( bookImgUrl integer ,id integer primary key autoincrement , bookName  text ,  bookAuthor text ,  bookRate integer ,  isFav integer,  type text,bookDetails text)  ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Books ");
        onCreate(db);
    }
    public boolean  insert(Books books){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bookImgUrl,books.getBookImgUrl());
        values.put(bookName,books.getBookName());
        values.put(bookAuthor,books.getBookAuthor());
        values.put(bookRate,books.getBookRate());
        values.put(favourite,books.getFavourite());
        values.put(type,books.getType());
        values.put(description,books.getDescription());
        long result = database.insert(databaseName,null,values);
        return result!=-1 ;
    }
    public  boolean update (Books books) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bookImgUrl, books.getBookImgUrl());
        values.put(bookName, books.getBookName());
        values.put(bookAuthor, books.getBookAuthor());
        values.put(bookRate, books.getBookRate());
        values.put(favourite, books.getFavourite());
        values.put(type,books.getType());
        values.put(description,books.getDescription());
        String[] args = {String.valueOf(books.getBookImgUrl()), String.valueOf(books.getBookName()),
                String.valueOf(books.getBookAuthor()), String.valueOf(books.getBookRate()),String.valueOf(books.getFavourite()),String.valueOf(books.getType()),
                String.valueOf(books.getDescription())};
        long result = database.update(databaseName, values, "bookImgUrl= ?  , bookName=? , bookAuthor=?, bookRate=?," +
                "isFav=? ,type=?,bookDetails=?", args);
        return result > 0;
    }
    public long getCount(){
        SQLiteDatabase database = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(database,databaseName);
    }
    public  boolean delete (Books books){
        SQLiteDatabase database =getWritableDatabase();
        String[] args = {String.valueOf(books.getBookImgUrl()),  String.valueOf(books.getBookName()),
                String.valueOf(books.getBookAuthor()), String.valueOf(books.getBookRate()),String.valueOf(books.getFavourite()),String.valueOf(books.getType())
        , String.valueOf(books.getDescription())};
        long result = database.delete(databaseName,"bookImgUrl= ?  , bookName=? , bookAuthor=?, bookRate=? ,isFav=?,type=?,bookDetails=?",args);
        return result>0;
    }
    public  ArrayList<Books> getAllBooks(){
        ArrayList<Books> booksData = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor= database.rawQuery("select * from Books",null);
        if (cursor.moveToFirst()){
            do {
                String book_name = cursor.getString(cursor.getColumnIndex(bookName));
                String book_Author = cursor.getString(cursor.getColumnIndex(bookAuthor));
                int favouritee = cursor.getInt(cursor.getColumnIndex(favourite));
                int bookImg = cursor.getInt(cursor.getColumnIndex(bookImgUrl));
                int book_Rate= cursor.getInt(cursor.getColumnIndex(bookRate));
                String bookType = cursor.getString(cursor.getColumnIndex(type));
                String bookDetails = cursor.getString(cursor.getColumnIndex(description));

                Books booksData1 = new Books(bookImg,book_name,book_Author,book_Rate,favouritee,bookType,bookDetails);
                booksData.add(booksData1);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return booksData;
    }
    public ArrayList<Books> getFavouriteBooks(int isFav){
        ArrayList<Books> booksData = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String []args = {String.valueOf(isFav)};
        Cursor cursor= database.rawQuery("select * from Books where isfav = ?",args);
        if (cursor.moveToFirst()){
            do {
                String book_name = cursor.getString(cursor.getColumnIndex(bookName));
                String book_Author = cursor.getString(cursor.getColumnIndex(bookAuthor));
                int favouritee = cursor.getInt(cursor.getColumnIndex(favourite));
                int bookImg = cursor.getInt(cursor.getColumnIndex(bookImgUrl));
                int book_Rate= cursor.getInt(cursor.getColumnIndex(bookRate));
                String bookType = cursor.getString(cursor.getColumnIndex(type));
                String bookDetails = cursor.getString(cursor.getColumnIndex(description));
                Books booksData1 = new Books(bookImg,book_name,book_Author,book_Rate,favouritee,bookType,bookDetails);
                booksData.add(booksData1);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return booksData;
    }
    public ArrayList<Books> getBooksType(String typee){
        ArrayList<Books> booksData = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String []args = {String.valueOf(typee)};
        Cursor cursor= database.rawQuery("select * from Books where type = ?",args);
        if (cursor.moveToFirst()){
            do {
                String book_name = cursor.getString(cursor.getColumnIndex(bookName));
                String book_Author = cursor.getString(cursor.getColumnIndex(bookAuthor));
                int favouritee = cursor.getInt(cursor.getColumnIndex(favourite));
                int bookImg = cursor.getInt(cursor.getColumnIndex(bookImgUrl));
                int book_Rate= cursor.getInt(cursor.getColumnIndex(bookRate));

                String bookDetails = cursor.getString(cursor.getColumnIndex(description));
                Books booksData1 = new Books(bookImg,book_name,book_Author,book_Rate,favouritee,typee,bookDetails);
                booksData.add(booksData1);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return booksData;
    }
    public ArrayList<Books> getPopularBooks(){
        ArrayList<Books> booksData = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String []args = {String.valueOf(3)};
        Cursor cursor= database.rawQuery("select * from Books where bookRate > ?",args);
        if (cursor.moveToFirst()){
            do {
                String book_name = cursor.getString(cursor.getColumnIndex(bookName));
                String book_Author = cursor.getString(cursor.getColumnIndex(bookAuthor));
                int favouritee = cursor.getInt(cursor.getColumnIndex(favourite));
                int bookImg = cursor.getInt(cursor.getColumnIndex(bookImgUrl));
                int book_Rate= cursor.getInt(cursor.getColumnIndex(bookRate));
                String bookType = cursor.getString(cursor.getColumnIndex(type));
                String bookDetails = cursor.getString(cursor.getColumnIndex(description));
                Books booksData1 = new Books(bookImg,book_name,book_Author,book_Rate,favouritee,bookType,bookDetails);
                booksData.add(booksData1);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return booksData;
    }


}
