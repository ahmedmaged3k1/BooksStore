package com.example.booksstore.Ui;

import android.os.Bundle;

import com.example.booksstore.Adapter.BookRecycleViewAdapter;
import com.example.booksstore.Adapter.BookSliderAdapter;
import com.example.booksstore.Books.Books;
import com.example.booksstore.Presenter.BooksPresenter;

import com.example.booksstore.R;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BooksHome extends AppCompatActivity {

    private static final String TAG = "BooksHome";
    private ArrayList<Books> allBooks = new ArrayList<>();
    private ArrayList<Books> popularBooks = new ArrayList<>();
    private ViewPager booksSlider;
    private BooksPresenter booksPresenter = new BooksPresenter(this);
    private TabLayout bookIndicator;
    private RecyclerView booksRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        //Animatoo.animateInAndOut(this);
        addBooks();
        setSliderAdapter();
        autoSwipe();
        setPopularBooksAdapter();


    }
    public void setPopularBooksAdapter(){
        booksRecycleView=findViewById(R.id.popularBooksRecycleView);
        popularBooks=booksPresenter.getPopularBooks();
        BookRecycleViewAdapter bookRecycleViewAdapter= new BookRecycleViewAdapter(this,popularBooks);
        // Log.d(TAG, "setPopularBooksAdapter: "+popularBooks.get(2).getBookName());
        booksRecycleView.setAdapter(bookRecycleViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        booksRecycleView.setLayoutManager(linearLayoutManager);
        booksRecycleView.hasFixedSize();
    }
    public void autoSwipe(){
        // bookIndicator= (TabLayout) findViewById(R.id.bookIndicator); // Show Indicators
        //  bookIndicator.setupWithViewPager(booksSlider,true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new BooksHome.BookSliderTimer(),4000,2500);
    }
    public void addBooks(){
        booksPresenter.addBooks();
        allBooks=booksPresenter.getBooksFromDatabase();
    }
    public void setSliderAdapter(){
        booksSlider=findViewById(R.id.bookSlider);
        BookSliderAdapter bookSliderAdapter = new BookSliderAdapter(allBooks,this);
        booksSlider.setAdapter(bookSliderAdapter);
    }
    class BookSliderTimer extends TimerTask {

        @Override
        public void run() {
            BooksHome.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(booksSlider.getCurrentItem()<allBooks.size()-1){
                        booksSlider.setCurrentItem(booksSlider.getCurrentItem()+1);
                    }
                    else{
                        booksSlider.setCurrentItem(0);
                    }
                }
            });
        }
    }
    class PopularBookSliderTimer extends TimerTask{

        @Override
        public void run() {
            BooksHome.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(booksSlider.getCurrentItem()<allBooks.size()-1){
                        booksSlider.setCurrentItem(booksSlider.getCurrentItem()+1);
                    }
                    else{
                        booksSlider.setCurrentItem(0);
                    }
                }
            });
        }
    }
}