package vn.mrlongg71.ps09103_assignment.view.activity;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.mrlongg71.ps09103_assignment.R;
import vn.mrlongg71.ps09103_assignment.library.ActionBarLib;
import vn.mrlongg71.ps09103_assignment.library.Dialog;
import vn.mrlongg71.ps09103_assignment.view.bill.BillFragment;
import vn.mrlongg71.ps09103_assignment.view.book.BookFragment;
import vn.mrlongg71.ps09103_assignment.view.manageruser.InfoFragment;
import vn.mrlongg71.ps09103_assignment.view.statistical.StatisticalFragment;
import vn.mrlongg71.ps09103_assignment.view.typebook.TypebookFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private TextView txtNameNav,txtEmailNav;
    private CircleImageView imgUserNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        setActionBar();
        initNavigationDrawer();
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        ActionBarLib.setSupportActionBar(HomeActivity.this, getString(R.string.title_activity_home));

    }

    private void initNavigationDrawer() {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fram, new StatisticalFragment()).commit();

//        txtNameNav.setText("fggffgg");
    }

    private void initView() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Dialog.DialogExit(HomeActivity.this, getString(R.string.warning), getString(R.string.wantExit));

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_statistical:
                getSupportFragmentManager().beginTransaction().replace(R.id.fram, new StatisticalFragment()).commit();

                break;
            case R.id.nav_type:
                getSupportFragmentManager().beginTransaction().replace(R.id.fram, new TypebookFragment()).commit();

                break;
            case R.id.nav_book:
                getSupportFragmentManager().beginTransaction().replace(R.id.fram, new BookFragment()).commit();
                break;
            case R.id.nav_bill:
                getSupportFragmentManager().beginTransaction().replace(R.id.fram, new BillFragment()).commit();
                break;
            case R.id.nav_info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fram, new InfoFragment()).commit();
                break;
            case R.id.nav_exit:
                Dialog.DialogExit(HomeActivity.this, getString(R.string.warning), getString(R.string.wantExit));
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
