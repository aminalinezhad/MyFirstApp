package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.example.amin.myfirstapp.LifecycleActivity;
import com.example.amin.myfirstapp.MainActivity;
import com.example.amin.myfirstapp.R;

public class MainFragment extends android.support.v4.app.Fragment {
    private View rootView;

    private EditText edtName, edtPassword;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_fragment, container, false);
        edtName = rootView.findViewById(R.id.edt_UserName);
        edtPassword = rootView.findViewById(R.id.edt_Password);

        rootView.findViewById(R.id.btn_LifeCycle).setOnClickListener(v -> lifeCycleActivityClickHandler(rootView));

        return rootView;
    }

    private void showToastMessage() {
        String name = edtName.getText().toString();
        String password = edtPassword.getText().toString();
        String message = String.format(getString(R.string.msg_UserPass), name, password);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }



    public void lifeCycleActivityClickHandler(View view) {
        Intent intent = new Intent(getContext(), LifecycleActivity.class);
        startActivityForResult(intent, MainActivity.REQUEST_LIFECYCLE);
//        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater =  getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return(true);
//    }

//    public void btn_popupMenuClick(View view) {
//        PopupMenu popupMenu = new PopupMenu(getContext(), view);
//        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.menu_popup_Item0:
//                        Toast.makeText(getContext(), "Item00 Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.menu_popup_Item1:
//                        Toast.makeText(getContext(), "Item01 Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.menu_popup_Item2:
//                        Toast.makeText(getContext(), "Item02 Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return false;
//            }
//        });
//        popupMenu.show();
//    }


}
