package kr.ac.duksung.websocketexer;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TestPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> items;
    private ArrayList<String> itexts = new ArrayList<>();
    //adapter
    public TestPagerAdapter(FragmentManager fm){
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new PageOneFragment());
        items.add(new PageTwoFragment());
        items.add(new PageThreeFragment());
        itexts.add("실시간재고");
        itexts.add("도난의심기록");
        itexts.add("출입기록");
    }
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
    public  CharSequence getPageTitle(int position){
        return itexts.get(position);
    }
}
