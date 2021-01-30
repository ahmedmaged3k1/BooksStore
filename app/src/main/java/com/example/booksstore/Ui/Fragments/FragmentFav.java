package com.example.booksstore.Ui.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksstore.Adapter.BooksSearchRecyclerView;
import com.example.booksstore.Books.Books;
import com.example.booksstore.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FragmentFav extends Fragment {
    private View view;
    private ArrayList<Books> favBooks
            = new ArrayList<>();
    private RecyclerView booksFavRecycleviewer ;
    public FragmentFav(ArrayList<Books> favBooks
    ) {
        this.favBooks
                =favBooks
        ;
    }

    public static FragmentFav getInstance( ArrayList<Books> favBooks
    ){
        FragmentFav fragmentFav= new FragmentFav(favBooks
        );
        return fragmentFav;
    }
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fav_fragment,container,false);
        setPharmacyRecyclerViewer();
        return view;

    }

    public void setPharmacyRecyclerViewer(){
        booksFavRecycleviewer=view.findViewById(R.id.favBooksRecycleview);
        BooksSearchRecyclerView booksSearchRecyclerView = new BooksSearchRecyclerView(getContext(),favBooks);
        booksFavRecycleviewer.setAdapter(booksSearchRecyclerView);
        booksFavRecycleviewer.setLayoutManager(new LinearLayoutManager(getContext()));
        booksFavRecycleviewer.hasFixedSize();


    }
}
