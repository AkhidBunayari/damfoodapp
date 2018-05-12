package online.damfood.damfood.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import online.damfood.damfood.intro.SlideFragment;

public class IntroPagerAdapter extends FragmentStatePagerAdapter {

    private final List<SlideFragment> fragmentList;

    public IntroPagerAdapter(FragmentManager fm, List<SlideFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
