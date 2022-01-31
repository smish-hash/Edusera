package com.example.edusera.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.edusera.Adapter.CourseDataAdapter;
import com.example.edusera.Interface.ApiInterface;
import com.example.edusera.Model.CourseDataModel;
import com.example.edusera.R;
import com.example.edusera.Database.Repository;
import com.example.edusera.ViewModel.CourseDataViewModel;
import com.example.edusera.databinding.FragmentCourseDataBinding;

import java.util.ArrayList;
import java.util.List;

public class CourseDataFragment extends Fragment {

    private FragmentCourseDataBinding binding;
    private NavController navController;

    private CourseDataViewModel courseDataViewModel;
    private List<CourseDataModel> courseDataList;
    private CourseDataAdapter adapter;
    private RecyclerView recyclerView;
    private Repository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCourseDataBinding.inflate(inflater, container, false);

        if (getActivity() != null)
            repository = new Repository(getActivity().getApplication());

        courseDataList = new ArrayList<>();
        recyclerView = binding.courseDataRecyclerView;
        recyclerView.setHasFixedSize(true);

        courseDataViewModel = new ViewModelProvider(this).get(CourseDataViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new CourseDataAdapter(getContext(), courseDataList);
        adapter.setOnItemClickListener((model, position) -> {
//            Toast.makeText(getContext(), "Topic Click " + model.getTitle(), Toast.LENGTH_SHORT).show();
//            Log.d("title", "onTitleClick: " + model.getTopics());
        });

        performRequest();

        courseDataViewModel.getGetAllCourseData().observe(getViewLifecycleOwner(), new Observer<List<CourseDataModel>>() {
            @Override
            public void onChanged(List<CourseDataModel> courseDataModels) {
                recyclerView.setAdapter(adapter);
                adapter.getAllCourseData(courseDataModels);
            }
        });
    }

    private void performRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<CourseDataModel>> call = apiInterface.getCourseData(getString(R.string.course_data_code));

        call.enqueue(new Callback<List<CourseDataModel>>() {
            @Override
            public void onResponse(Call<List<CourseDataModel>> call, Response<List<CourseDataModel>> response) {
                if (response.isSuccessful()) {
//                    repository.insertCourseData(response.body());
                    courseDataViewModel.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CourseDataModel>> call, Throwable t) {
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