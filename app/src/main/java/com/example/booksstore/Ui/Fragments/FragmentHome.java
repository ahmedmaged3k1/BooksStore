package com.example.booksstore.Ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.booksstore.Adapter.BookRecycleViewAdapter;
import com.example.booksstore.Adapter.BookSliderAdapter;
import com.example.booksstore.Adapter.ViewPagerAdapter;
import com.example.booksstore.Books.Books;
import com.example.booksstore.Presenter.BooksPresenter;
import com.example.booksstore.R;
import com.example.booksstore.Ui.BooksHome;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentHome extends Fragment {
    private View view;
    private ArrayList<Books> allBooks = new ArrayList<>();
    private ArrayList<Books> popularBooks = new ArrayList<>();
    private ViewPager booksSlider;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView booksRecycleView;

    public FragmentHome(ArrayList<Books> allBooks,ArrayList<Books> popularBooks) {
        this.allBooks=allBooks;
        this.popularBooks=popularBooks;
    }


    public static FragmentHome getInstance(ArrayList<Books> allBooks,ArrayList<Books> popularBooks){
        FragmentHome fragmentHome = new FragmentHome(allBooks,popularBooks);
        return fragmentHome;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);


        setSliderAdapter();
        autoSwipe();
        setPopularBooksAdapter();

        return view;
    }

    public void setPopularBooksAdapter() {
        booksRecycleView = view.findViewById(R.id.popularBooksRecycleView);
        BookRecycleViewAdapter bookRecycleViewAdapter = new BookRecycleViewAdapter(getContext(), popularBooks);
        // Log.d(TAG, "setPopularBooksAdapter: "+popularBooks.get(2).getBookName());
        booksRecycleView.setAdapter(bookRecycleViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        booksRecycleView.setLayoutManager(linearLayoutManager);
        booksRecycleView.hasFixedSize();
    }

    public void autoSwipe() {
        // bookIndicator= (TabLayout) findViewById(R.id.bookIndicator); // Show Indicators
        //  bookIndicator.setupWithViewPager(booksSlider,true);
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new FragmentHome.BookSliderTimer(), 4000, 2500);
    }


    public void setSliderAdapter() {
        booksSlider = view.findViewById(R.id.bookSlider);
        BookSliderAdapter bookSliderAdapter = new BookSliderAdapter(allBooks, getContext());
        booksSlider.setAdapter(bookSliderAdapter);
    }

    class BookSliderTimer extends TimerTask {



        @Override
        public void run() {
            booksSlider.post(new Runnable() {
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
  /*      @Override
        public void run() {
            FragmentHome.this.runOnUiThread(new Runnable() {
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
    }*/

    }
}
