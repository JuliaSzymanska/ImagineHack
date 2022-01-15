package tech.szymanska.mypocketdoctor.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeCollectionAdapter extends FragmentStateAdapter {
    private final ArrayList<Fragment> fragments = new ArrayList<>(
            Arrays.asList(
                    new DiagnoseFragment(),
                    new HistoryFragment(),
                    new UsersFragment()
            ));

    public HomeCollectionAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position > fragments.size()) {
            throw new IllegalArgumentException();
        }
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
