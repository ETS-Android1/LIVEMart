package com.example.oop_project.Retailer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.oop_project.R;

import java.util.ArrayList;

public class RetailerOrders extends Fragment implements AdapterView.OnItemClickListener{
    ArrayList<String> Keys = new ArrayList<>();
    boolean t;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listview = view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter= new ArrayAdapter<>(getActivity(),R.layout.row,R.id.maintransview,Keys);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaction_layout, container, false);
        return view;
    }
    public RetailerOrders(ArrayList<String> Keys) {
        this.Keys=Keys;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedFromList = (String) (parent.getItemAtPosition(position));
        AppCompatActivity activity=(AppCompatActivity)view.getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new RetailerOrders_sub(selectedFromList)).addToBackStack(null).commit();
    }
}
