package com.example.booksstore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksstore.Books.Books;
import com.example.booksstore.Presenter.BooksPresenter;
import com.example.booksstore.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class BooksSearchRecyclerView extends RecyclerView.Adapter<BooksSearchRecyclerView.BookSearchHolder>implements Filterable {
    private Context context;
    private ArrayList<Books> allBooks = new ArrayList<>();
    private ArrayList<Books> allBooksFiltered = new ArrayList<>();
    private BooksPresenter booksPresenter ;


    public BooksSearchRecyclerView(Context context, ArrayList<Books> allBooks) {
        this.context = context;
        this.allBooks = allBooks;
        allBooksFiltered=allBooks;
        booksPresenter = new BooksPresenter(context);
        notifyDataSetChanged();
    }

    public ArrayList<Books> getAllBooks() {
        notifyDataSetChanged();
        return allBooks;
    }

    public void setAllBooks(ArrayList<Books> allBooks) {
        this.allBooks = allBooks;
        allBooksFiltered=allBooks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_search_item,parent,false);
        return new BookSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchHolder holder, int position) {
        holder.onBind(holder, position);
    }


    @Override
    public int getItemCount() {
        return allBooksFiltered.size();
    }
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if (key.isEmpty()) {
                    allBooksFiltered = allBooks;
                } else {
                    ArrayList<Books> allBooksIsFilterd = new ArrayList<Books>();
                    for (Books row : allBooks) {
                        if (row.getBookName().toLowerCase().contains(key.toLowerCase())) {
                            allBooksIsFilterd.add(row);
                            Log.d(TAG, "performFiltering: "+key.toLowerCase());


                        }
                    }
               //     Log.d(TAG, "performFiltering books size: "+allBooksIsFilterd.size());
              //      Log.d(TAG, "performFiltering books name: "+allBooksIsFilterd.get(0).getBookName());
                    allBooksFiltered = allBooksIsFilterd;
              //      Log.d(TAG, "performFiltering books name: "+allBooksFiltered.get(0).getBookName());
                }
                FilterResults filterResult=new FilterResults();
                filterResult.values = allBooksFiltered;
                return filterResult;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                allBooksFiltered = (ArrayList<Books>) results.values;
                Log.d(TAG, "performFiltering books name: "+allBooksFiltered.get(0).getBookName());
                notifyDataSetChanged();

            }
        };
    }

    public class BookSearchHolder extends RecyclerView.ViewHolder {

        private ImageView bookImg ;
        private TextView bookName;
        private TextView bookRate;
        private ImageView bookImgRate ;
        private CardView bookCard;
        private FloatingActionButton bookmarkBtn;
        public BookSearchHolder(@NonNull View itemView) {
            super(itemView);
            bookImg= itemView.findViewById(R.id.searchImage);
            bookName=itemView.findViewById(R.id.searchBookName);
            bookRate=itemView.findViewById(R.id.searchRate);
            bookImgRate=itemView.findViewById(R.id.bookImgRate);
            bookCard=itemView.findViewById(R.id.searchCard);
        }
        private void changeBookmark(int positon) {
            bookmarkBtn = itemView.findViewById(R.id.searchBookmark);
            if(allBooksFiltered.get(positon).getFavourite()==0){
                bookmarkBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bookmarkBtn.setImageResource(R.drawable.bookmarked244);
                        allBooksFiltered.get(positon).setFavourite(1);
                        Log.d(TAG, "onClick: "+allBooksFiltered.get(positon).getFavourite());
                        booksPresenter.updateBooks(allBooksFiltered.get(positon));
                        notifyDataSetChanged();
                    }
                });
            }
            else     if(allBooksFiltered.get(positon).getFavourite()==1){
                bookmarkBtn.setImageResource(R.drawable.bookmarked244);
                bookmarkBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bookmarkBtn.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
                        allBooksFiltered.get(positon).setFavourite(0);
                        Log.d(TAG, "onClick: "+allBooksFiltered.get(positon).getFavourite());
                        booksPresenter.updateBooks(allBooksFiltered.get(positon));
                        notifyDataSetChanged();

                    }
                });
            }

        }
        public void onBind(BookSearchHolder holder, int position){
            holder.bookName.setText(allBooksFiltered.get(position).getBookName());
            Log.d(TAG, "onBindViewHolder: Datacame" +allBooksFiltered.get(position).getBookName());
            holder.bookImg.setImageResource(allBooksFiltered.get(position).getBookImgUrl());
            holder.bookRate.setText(String.valueOf(allBooksFiltered.get(position).getBookRate()));
            Log.d(TAG, "onBindViewHolder: Datacame");
            holder.bookCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
            changeBookmark(position);


        }
    }
}
