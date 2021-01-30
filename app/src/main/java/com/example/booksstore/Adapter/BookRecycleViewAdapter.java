package com.example.booksstore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.booksstore.Books.Books;


import com.example.booksstore.R;
import com.example.booksstore.Ui.BooksDetails;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class BookRecycleViewAdapter extends RecyclerView.Adapter<BookRecycleViewAdapter.BooksHolder> {
    private Context context;
    private ArrayList<Books> popularBooks = new ArrayList<>();

    public BookRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item,parent,false);
        return new BooksHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksHolder holder, int position) {
     holder.OnBindView(holder, position);
    }

    @Override
    public int getItemCount() {
        return popularBooks.size();
    }

    public ArrayList<Books> getPopularBooks() {
        notifyDataSetChanged();
        return popularBooks;
    }

    public void setPopularBooks(ArrayList<Books> popularBooks) {
        this.popularBooks = popularBooks;
    }

    public BookRecycleViewAdapter(Context context, ArrayList<Books> popularBooks) {
        this.context = context;
        this.popularBooks = popularBooks;
    }

    public class BooksHolder extends RecyclerView.ViewHolder {
        private ImageView bookImg ;
        private TextView bookName;
        private TextView bookRate;
        private ImageView bookImgRate ;
        private CardView bookCard;
        public BooksHolder(@NonNull View itemView) {
            super(itemView);
           bookImg= itemView.findViewById(R.id.bookImgRecycleView);
           bookName=itemView.findViewById(R.id.bookNameRecycleView);
            bookRate=itemView.findViewById(R.id.bookRateRecycleView);
            bookImgRate=itemView.findViewById(R.id.bookImgRate);
            bookCard=itemView.findViewById(R.id.bookCard);
        }
        public void OnBindView(BooksHolder holder, int position){
              holder.bookName.setText(popularBooks.get(position).getBookName());
        Log.d(TAG, "onBindViewHolder: Datacame" +popularBooks.get(position).getBookName());
        holder.bookImg.setImageResource(popularBooks.get(position).getBookImgUrl());
        holder.bookRate.setText(String.valueOf(popularBooks.get(position).getBookRate()));
        Log.d(TAG, "onBindViewHolder: Datacame");
            holder.bookCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));


        holder.bookImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent booksDetails   =new Intent(context, BooksDetails.class);
                Books bookIntent = new Books(popularBooks.get(position).getBookImgUrl(),popularBooks.get(position).getBookName(),
                        popularBooks.get(position).getBookAuthor(),popularBooks.get(position).getBookRate(),
                        popularBooks.get(position).getFavourite(),popularBooks.get(position).getType(),popularBooks.get(position).getDescription());
                booksDetails.putExtra("Book",bookIntent);
                booksDetails.putExtra("index",position);
                Log.d(TAG, "OnBindView: DataSent");
                context.startActivity(booksDetails);
                Animatoo.animateInAndOut(context);
            }
        });


        }
    }
}
