package com.example.booksstore.Ui.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksstore.Adapter.BookRecycleViewAdapter;
import com.example.booksstore.Adapter.BooksSearchRecyclerView;
import com.example.booksstore.Books.Books;
import com.example.booksstore.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FragmentSearch extends Fragment {
    private View view;
    private ArrayList<Books> allBooks = new ArrayList<>();
    private RecyclerView booksSearchRecycleviewer ;
    private TextView bookSearchText;



    public FragmentSearch( ArrayList<Books> allBooks ) {
        this.allBooks=allBooks;
    }
    public static FragmentSearch getInstance( ArrayList<Books> allBooks ){

        FragmentSearch fragmentSearch =new FragmentSearch(allBooks);
        return fragmentSearch;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.search_fragment,container,false);
        setPharmacyRecyclerViewer();

        //changeBookmark();
        return view;
    }



    public void setPharmacyRecyclerViewer(){
        booksSearchRecycleviewer=view.findViewById(R.id.searchBooksRecycleView);
        BooksSearchRecyclerView booksSearchRecyclerView = new BooksSearchRecyclerView(getContext(),allBooks);
        booksSearchRecycleviewer.setAdapter(booksSearchRecyclerView);
        booksSearchRecycleviewer.setLayoutManager(new LinearLayoutManager(getContext()));
        booksSearchRecycleviewer.hasFixedSize();
        bookSearchText=view.findViewById(R.id.searchBooksName);
        bookSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                booksSearchRecyclerView.getFilter().filter(s);
                Log.d(TAG, "onTextChanged: "+s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
