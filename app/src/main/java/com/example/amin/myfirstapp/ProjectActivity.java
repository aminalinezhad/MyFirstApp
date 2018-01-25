package com.example.amin.myfirstapp;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import DataAdapter.ProjectDataAdapter;
import DataBase.ProjectMessagesDataSource;
import DataModel.MyProjectMessage;
import DataModel.MyProjectMessageList;
import DataModel.msgKind;

public class ProjectActivity extends AppCompatActivity {

    GoogleSignInAccount mAccount;
    TextView txtAccount;
    ImageView imgAccount;
    ImageButton btnSend;
    ImageButton btnImage;
    EditText edtText;
    ProjectMessagesDataSource mDataSource;
    ProjectDataAdapter mAdapter;
    RecyclerView mRecyclerView;
    String mCurrentPhotoPath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        txtAccount = (TextView) findViewById(R.id.txt_toolbar_project);
        imgAccount = (ImageView) findViewById(R.id.img_toolbar_project);
        btnSend = (ImageButton) findViewById(R.id.btn_Send);
        btnImage = (ImageButton) findViewById(R.id.btn_Image);
        edtText = (EditText) findViewById(R.id.edt_Text);

        setMyToolbar();
        initDataSource();

        mAccount = GoogleSignIn.getLastSignedInAccount(this);
        updateUI();

        MyProjectMessageList list;
        if (mAccount==null) {
            list = mDataSource.getAllMessages(null);
        } else{
            list = mDataSource.getAllMessages(mAccount.getId());
        }
        mAdapter = new ProjectDataAdapter(list);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_project);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.scrollToPosition(list.getSize()-1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }


    private void initDataSource() {
        mDataSource = new ProjectMessagesDataSource(this);
        mDataSource.open();
    }

    private void setMyToolbar() {
        Toolbar tbUp = (Toolbar) findViewById(R.id.toolbar_project);
        tbUp.setTitle("");
        setSupportActionBar(tbUp);
    }

    private void updateUI(){
        if (mAccount == null) {
            txtAccount.setText("You aren't sign in");
        } else{
            txtAccount.setText(mAccount.getDisplayName() + "\n" + mAccount.getEmail());
            Picasso.with(this).load(mAccount.getPhotoUrl()).into(imgAccount);
        }
    }

    public void btnSendClick(View view) {
        String str = edtText.getText().toString();
        if (str.equals("")) return;

        Date currentDate = Calendar.getInstance().getTime();

        MyProjectMessage msg = new MyProjectMessage();
        msg.setText(str);
        msg.setKind(msgKind.SimpleText);
        msg.setCreateDate(currentDate);
        if (mAccount!=null) {
            msg.setUserGoogleID(mAccount.getId());
            mDataSource.insertMessage(msg);
        }

        int pos = mAdapter.addMessage(msg);
        mRecyclerView.scrollToPosition(pos);
        edtText.setText("");
//Amin: below codes close keyboard
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void btnImageClick(View view) {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file=new File(getFile(),"Image_"+System.currentTimeMillis()/1000+".png");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent,MainActivity.REQUEST_IMAGE_CAPTURE);
    }

    private File getFile() {
        File file = null;

        if (Build.VERSION.SDK_INT >= 23 && (this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                this.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return getCacheFile();
        } else {
            //file=new File(Environment.getExternalStorageDirectory(),"Android");

            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Android_Course");
            file.mkdir();
        }


        return file;
    }

    private File getCacheFile() {
        File file = null;

        file = new File(this.getCacheDir(), "Android");
        file.mkdir();

        return file;
    }
}
