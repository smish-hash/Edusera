package com.example.edusera.Adapter;

import com.example.edusera.Fragment.CourseDataFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private FragmentActivity fragmentActivity;
    private List<String> titleList;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<String> titleList) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
        this.titleList = titleList;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CourseDataFragment();
            case 1:
                return new CourseDataFragment();
            case 2:
                return new CourseDataFragment();
        }
        return new CourseDataFragment();
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}
