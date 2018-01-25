package Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amin.myfirstapp.R;

/**
 * Created by Amin on 14/12/2017.
 */

public class viewPagerFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ViewPager viewpager = rootview.findViewById(R.id.view_pager);
        viewpager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        return rootview;
    }
}
