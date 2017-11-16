package com.intimetec.conferencemanager.ui.booking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.intimetec.conferencemanager.R;
import com.intimetec.conferencemanager.constant.PreferenceConstant;
import com.intimetec.conferencemanager.data.ConferenceRoomsDataSource;
import com.intimetec.conferencemanager.data.remote.ConferenceRoomsRemoteDataSource;
import com.intimetec.conferencemanager.data.remote.UserRemoteDataSource;
import com.intimetec.conferencemanager.model.conference.ConferenceRooms;
import com.intimetec.conferencemanager.model.user.Data;
import com.intimetec.conferencemanager.model.user.User;
import com.intimetec.conferencemanager.preference.PreferenceManager;
import com.intimetec.conferencemanager.ui.BaseActivity;
import com.intimetec.conferencemanager.ui.login.LoginActivity;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnListFragmentInteractionListener {

    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        User user = PreferenceManager.getObject(HomeActivity.this, PreferenceConstant.USER_KEY, User.class);

        if (user != null) {
            setDrawerData(user);
        }
        addFragment(R.id.contentFrame, new HomeFragment(), HomeFragment.class.getCanonicalName());
    }

    private void setDrawerData(User user) {
        List<Data> userData = user.getData();

        View headerView = mNavigationView.getHeaderView(0);

        TextView nameTxtView = (TextView) headerView.findViewById(R.id.nav_head_name);
        TextView emailTxtView = (TextView) headerView.findViewById(R.id.nav_head_email);

        nameTxtView.setText(userData.get(0).getFname());
        emailTxtView.setText(userData.get(0).getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            PreferenceManager.clearPreference(this);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(ConferenceRooms.Data item) {

    }
}
