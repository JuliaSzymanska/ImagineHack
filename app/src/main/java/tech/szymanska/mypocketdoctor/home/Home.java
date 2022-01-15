package tech.szymanska.mypocketdoctor.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import tech.szymanska.mypocketdoctor.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeCollectionFragment fragment = new HomeCollectionFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutToBeReplacedWithFragmentInMenu, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}