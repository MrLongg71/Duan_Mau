package vn.mrlongg71.ps09103_assignment.view.fragment;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.library.ActionBarLib;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerUserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_manager_user, container, false);
        ActionBarLib.setSupportActionBar(getActivity(),getString(R.string.manager));
         return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.custom_menu_add,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_type:
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_dialog_add_user);
                dialog.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
