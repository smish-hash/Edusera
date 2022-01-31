package com.example.edusera.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.edusera.Adapter.CourseFeedAdapter;
import com.example.edusera.Interface.ApiInterface;
import com.example.edusera.Model.CourseFeedModel;
import com.example.edusera.R;
import com.example.edusera.Database.Repository;
import com.example.edusera.ViewModel.CourseFeedViewModel;
import com.example.edusera.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private NavController navController;

    private CourseFeedViewModel courseFeedViewModel;
    private List<CourseFeedModel> courseFeedList;
    private CourseFeedAdapter adapter;
    private RecyclerView recyclerView;
    private Repository repository;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        if (getActivity() != null)
            repository = new Repository(getActivity().getApplication());

        courseFeedList = new ArrayList<>();
        recyclerView = binding.courseFeedRecyclerView;
        recyclerView.setHasFixedSize(true);

        courseFeedViewModel = new ViewModelProvider(this).get(CourseFeedViewModel.class);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        adapter = new CourseFeedAdapter(getContext(), courseFeedList);
        adapter.setOnItemClickListener((model, position) -> {
            navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
//            Log.d("api", "onClick: " + model.getTitle());
        });

        performRequest();

        courseFeedViewModel.getGetAllCourseFeeds().observe(getViewLifecycleOwner(), new Observer<List<CourseFeedModel>>() {
            @Override
            public void onChanged(List<CourseFeedModel> courses) {
                recyclerView.setAdapter(adapter);
                adapter.getAllCourses(courses);
                Log.d("api", "onChanged: " + courses);
            }
        });
    }

    private void performRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<CourseFeedModel>> call = apiInterface.getFeeds(getString(R.string.course_feed_code));

        call.enqueue(new Callback<List<CourseFeedModel>>() {
            @Override
            public void onResponse(Call<List<CourseFeedModel>> call, Response<List<CourseFeedModel>> response) {
                if (response.isSuccessful()) {
//                    repository.insertCourseFeed(response.body());
                    courseFeedViewModel.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CourseFeedModel>> call, Throwable t) {
                Log.d("api", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}