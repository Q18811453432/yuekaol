package weektest.baway.com.yuekaolx.base;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import weektest.baway.com.yuekaolx.frag.Tab;
import weektest.baway.com.yuekaolx.frag.Tabfragment;

public class Mypager extends FragmentPagerAdapter {

    private List<String> list;

    public Mypager(FragmentManager fm, List<String> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            Tabfragment tabfragment = new Tabfragment();
            return tabfragment;
        } else {
            Tab tab = new Tab();
            return tab;
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return list.get(position);
    }
}
