package vn.mrlongg71.ps09103_assignment.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.mrlongg71.ps09103_assignment.R;


public class AddBookFragment extends Fragment {


    public AddBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        return view;
    }

}
