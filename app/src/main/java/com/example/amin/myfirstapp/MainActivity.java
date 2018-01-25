package com.example.amin.myfirstapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import Fragments.ActionReceiverFragment;
import Fragments.MainFragment;
import Utility.ScreenUtility;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mainDrawerLayout;
    public static final int REQUEST_LIFECYCLE = 1;
    public static final String LIFE_CYCLE_RESULT_KEY = "LIFE_CYCLE_RESULT_KEY";
    private static final int REQUEST_SIGN_IN = 2;
    static final int REQUEST_IMAGE_CAPTURE = 3;

    GoogleSignInClient mGoogleSingInClient;
    GoogleSignInAccount mAccount;

    SignInButton btnSignIn;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mainDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mainDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        btnSignIn.setOnClickListener(view -> btnSignInClick(view));

        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(view -> btnLogoutClick(view));

        setGoogleSignInClient();

        NavigationView mainNavigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        mainNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_main_fragment:
                        addFragment(new MainFragment(), "Main Fragment");
                        break;
                    case R.id.menu_action_receiver:
                        addFragment(new ActionReceiverFragment(), "Action Receiver Fragment");
                        break;
                    case R.id.menu_Project:
                        Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
                        startActivity(intent);
                        break;
                }
                mainDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        Intent intent = getIntent();
        String userName = intent.getStringExtra(SplashActivity.USER_NAME);
    }

    private void btnLogoutClick(View view) {
        mGoogleSingInClient.revokeAccess();
        Toast.makeText(this, "You are logged out", Toast.LENGTH_SHORT).show();
    }

    private void addFragment(Fragment fragment, String tagFragment) {
        FragmentManager fragMngr = getSupportFragmentManager();
        FragmentTransaction fragTran = fragMngr.beginTransaction();

        fragTran.replace(R.id.fragment_container, fragment, tagFragment);
        fragTran.addToBackStack(tagFragment);
        fragTran.commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LIFECYCLE && resultCode == RESULT_OK) {
            Toast.makeText(this, data.getStringExtra(LIFE_CYCLE_RESULT_KEY), Toast.LENGTH_LONG).show();
        }

        if (requestCode==REQUEST_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInresult(task);
        }
    }

    @Override
    public void onBackPressed() {
        if (mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void screenSizeClick(View view) {
        ScreenUtility su = new ScreenUtility(this);
        Toast.makeText(this, String.format("Width: %s; Height: %s", su.getDpWidth(), su.getDpHeight()), Toast.LENGTH_SHORT).show();
    }


    private void setGoogleSignInClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSingInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn(){
        Intent intent = mGoogleSingInClient.getSignInIntent();
        startActivityForResult(intent, REQUEST_SIGN_IN);
    }

    private void handleSignInresult(Task<GoogleSignInAccount> task) {
        try {
            mAccount = task.getResult(ApiException.class);
            if (mAccount!=null) {
                Toast.makeText(this, "Successfull login", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "not login", Toast.LENGTH_SHORT).show();
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public void btnSignInClick(View view) {
        mAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (mAccount==null)
            signIn();
        else
            Toast.makeText(this, "You are already login", Toast.LENGTH_SHORT).show();
    }
}

