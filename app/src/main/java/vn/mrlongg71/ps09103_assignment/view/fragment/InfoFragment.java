package vn.mrlongg71.ps09103_assignment.view.fragment;


import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.library.ActionBarLib;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {

    private TextView txtManagerUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        setActionBar();
        initView(view);
        return view;
    }

    private void setActionBar() {
        ActionBarLib.setSupportActionBar(getActivity(),getString(R.string.menu_info));
    }

    private void initView(View view) {
        txtManagerUser = view.findViewById(R.id.txtManagerUser);
        initEvent();
    }

    private void initEvent() {
        txtManagerUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtManagerUser:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram,new ManagerUserFragment()).commit();
                break;
        }
    }
}
