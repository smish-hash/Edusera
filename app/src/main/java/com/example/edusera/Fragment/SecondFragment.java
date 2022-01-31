package com.example.edusera.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.edusera.Adapter.ViewPagerAdapter;
import com.example.edusera.R;
import com.example.edusera.databinding.FragmentSecondBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private ViewPagerAdapter viewPagerAdapter;
    private List<String> listOfTitles;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        listOfTitles = new ArrayList<>();

        if (getActivity() != null && getActivity().getWindow() != null) {
                if (getContext() != null && getContext().getTheme() != null)
                        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background, getContext().getTheme()));
        }

        loadTitles();
        setUpViewPagerWithTabLayout();



        return binding.getRoot();
    }

    private void loadTitles() {
        listOfTitles.add("Schedule");
        listOfTitles.add("Course");
        listOfTitles.add("Instructor");
    }

    private void setUpViewPagerWithTabLayout() {
        if (getActivity() != null) {
            viewPagerAdapter = new ViewPagerAdapter(getActivity(), listOfTitles);
            binding.viewPager.setAdapter(viewPagerAdapter);
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                ((tab, position) -> tab.setText(listOfTitles.get(position)))).attach();

        binding.btnBack.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
                .navigateUp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.show();
    }
}