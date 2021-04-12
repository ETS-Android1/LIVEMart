package com.example.oop_project.Customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CustomerShopList extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String catname,pname;
    RecyclerView recview1;
    Shopadapter adapter;

    public CustomerShopList() {

    }

    public CustomerShopList(String catname, String pname) {

        this.catname = catname;
        this.pname = pname;
    }

    public static CustomerShopList newInstance(String param1, String param2) {
        CustomerShopList fragment = new CustomerShopList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1).trim();
            mParam2 = getArguments().getString(ARG_PARAM2).trim();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_list, container, false);

        recview1 = (RecyclerView) view.findViewById(R.id.recview_shop);
        recview1.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i("Mine dis", "pname:" + pname);
        Log.i("Mine dis", "catname:" + catname);

         DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        Query query = rootref.child("Quantity").child(catname).child(pname).child("Retailer");
        //pname = Fruits
        FirebaseRecyclerOptions<model_shop> options =
                new FirebaseRecyclerOptions.Builder<model_shop>()
                        .setQuery(query, model_shop.class)
                        .build();
        adapter = new Shopadapter(options);
        recview1.setAdapter(adapter);
        return view;
    }

    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new CustomerSubCategories()).addToBackStack(null).commit();

    }
}