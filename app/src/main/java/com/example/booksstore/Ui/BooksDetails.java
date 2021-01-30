package com.example.booksstore.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booksstore.Adapter.BookRecycleViewAdapter;
import com.example.booksstore.Books.Books;
import com.example.booksstore.Presenter.BooksPresenter;
import com.example.booksstore.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BooksDetails extends AppCompatActivity {
    private static final String TAG ="BooksDetails" ;
    private TextView bookDescription;
    private ImageView bookImage;
    private FloatingActionButton bookmark_btn;
    private FloatingActionButton bookmarkBtn;
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView similarBooksAdapter ;
    private ArrayList<Books> similarBooks = new ArrayList<>();
    Books books;
    private BooksPresenter booksPresenter = new BooksPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_details);
        getDataFromIntent();
       changeBookmark();
    //   animate();
     //   Log.e(TAG, "onCreate: books.getType()"+similarBooks.get(0).getType());

      setSimilarBooksAdapter();
    }
    public void setSimilarBooksAdapter(){
        similarBooksAdapter=findViewById(R.id.similarBooksAdapter);
        similarBooksSetter();
        similarBooks=similarBooksSetter();
        BookRecycleViewAdapter bookRecycleViewAdapter= new BookRecycleViewAdapter(this,similarBooks);
        // Log.d(TAG, "setPopularBooksAdapter: "+popularBooks.get(2).getBookName());
        similarBooksAdapter.setAdapter(bookRecycleViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        similarBooksAdapter.setLayoutManager(linearLayoutManager);
        similarBooksAdapter.hasFixedSize();

    }
    private ArrayList<Books> similarBooksSetter(){
        ArrayList<Books> similarBooks = new ArrayList<>();
        Intent book = getIntent();
        Bundle bundle = book.getExtras();
        books = (Books) bundle.getSerializable("Book");
        similarBooks=booksPresenter.getBooksType(books.getType());
        int itemPosition =getIntent().getIntExtra("index",0);
        similarBooks.remove(itemPosition);
        Log.d(TAG, "similarBooksSetter: "+getIntent().getIntExtra("index",0));
        return similarBooks;

    }

    private void animate(){
        coordinatorLayout=findViewById(R.id.bookDetalsLayout);

        coordinatorLayout.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale));
    //    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(BooksDetails.this,bookImage,"animateDetails");
     //   options.toBundle();
    }
    private void getDataFromIntent(){
        Intent book = getIntent();
        Bundle bundle = book.getExtras();
         books = (Books) bundle.getSerializable("Book");
        bookImage=findViewById(R.id.bookDetailedImage);
        bookImage.setImageResource(books.getBookImgUrl());
        bookDescription=findViewById(R.id.bookDetails);
        bookDescription.setText(books.getDescription());
    }
    private void changeBookmark(){
        bookmark_btn=findViewById(R.id.bookmarkBtn);

        bookmark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bookmarkChanged");
                bookmark_btn.setImageResource(R.drawable.bookmarkred);
                Intent book = getIntent();
                Bundle bundle = book.getExtras();
                books = (Books) bundle.getSerializable("Book");
                books.setFavourite(1);
                booksPresenter.updateBooks(books);
                Log.d(TAG, "onCreate: "+books.getBookName());

            }
        });

    }
}