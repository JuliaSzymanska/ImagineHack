package tech.szymanska.mypocketdoctor.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;

import tech.szymanska.mypocketdoctor.R;
import tech.szymanska.mypocketdoctor.helpers.IOnBackPressed;

public class HomeCollectionFragment extends Fragment implements IOnBackPressed {

    HomeCollectionAdapter homeCollectionAdapter;
    ViewPager2 viewPager;
    ArrayList<String> tabsName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        homeCollectionAdapter = new HomeCollectionAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(homeCollectionAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        tabsName = new ArrayList<>(
                Arrays.asList(
                        getResources().getString(R.string.tabDiagnose),
                        getResources().getString(R.string.tabHistory),
                        getResources().getString(R.string.tabUsers)
                ));

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabsName.get(position))
        ).attach();
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}