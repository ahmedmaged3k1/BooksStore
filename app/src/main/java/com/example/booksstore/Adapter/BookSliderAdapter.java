package com.example.booksstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.booksstore.Books.Books;
import com.example.booksstore.R;

import java.util.ArrayList;

public class BookSliderAdapter extends PagerAdapter {
    private ArrayList<Books> allbooks ;
    private Context context;

    public BookSliderAdapter(ArrayList<Books> allbooks, Context context) {
        this.allbooks = allbooks;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout =inflater.inflate(R.layout.book_slider,null);
        ImageView bookImage = slideLayout.findViewById(R.id.bookImgUrl);
        TextView bookName =slideLayout.findViewById(R.id.bookName);
        bookImage.setImageResource(allbooks.get(position).getBookImgUrl());
        bookName.setText(allbooks.get(position).getBookName());
        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return allbooks.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
