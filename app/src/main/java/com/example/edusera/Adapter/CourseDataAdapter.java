package com.example.edusera.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edusera.Model.CourseDataModel;
import com.example.edusera.Model.TopicModel;
import com.example.edusera.PlayerActivity;
import com.example.edusera.R;
import com.example.edusera.Utils.DividerItemDecorator;
import com.example.edusera.databinding.CourseDataItemBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CourseDataAdapter extends RecyclerView.Adapter<CourseDataAdapter.ViewHolder> {

    private CourseDataItemBinding binding;
    private final Context context;
    private List<CourseDataModel> mList;

    private final Float rotate = 90F;

    public OnItemClickListener listener;

    public interface OnItemClickListener {
        void onTitleClick(CourseDataModel model, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CourseDataAdapter(Context context, List<CourseDataModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CourseDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CourseDataItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CourseDataAdapter.ViewHolder holder, int position) {
        final CourseDataModel courseDataModel = mList.get(position);
        holder.topic.setText(courseDataModel.getTitle());


        RecyclerView.ItemDecoration divider = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider));
        holder.subtopicRecyclerView.addItemDecoration(divider);

        List<TopicModel> subtopics = courseDataModel.getTopics();
        holder.subtopicRecyclerView.setHasFixedSize(true);
        CourseDataSubtopicAdapter adapter = new CourseDataSubtopicAdapter(context, subtopics);
        adapter.setOnItemClickListener((model, position1) -> {
//            Toast.makeText(context, "subtopic " + model.getTitle(), Toast.LENGTH_SHORT).show();

            if (model.getVideoLink() != null) {
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra("videoData", model);
                context.startActivity(intent);
            } else {
                Snackbar.make(binding.getRoot(), "You are not yet enrolled, please enroll to view the video", Snackbar.LENGTH_LONG)
                        .setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
            }
        });
        holder.subtopicRecyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void getAllCourseData(List<CourseDataModel> mList) {
        this.mList = mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView topic;
        private final AppCompatImageView btnToggleSubtopicVisibility;
        private final RecyclerView subtopicRecyclerView;
        private final CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topic = binding.tvTitle;
            btnToggleSubtopicVisibility = binding.appCompatImageView;
            subtopicRecyclerView = binding.subTopicRecyclerView;
            card = binding.itemCardView;

            card.setOnClickListener(view -> {
                /*if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        CourseDataModel model = mList.get(position);
                        listener.onTitleClick(model, position);
                    }
                }*/
                if (subtopicRecyclerView.getVisibility() == View.GONE) {
                    animateToggle(1);
                } else if (subtopicRecyclerView.getVisibility() == View.VISIBLE) {
                    animateToggle(2);
                }
            });
        }

        private void animateToggle(int ch) {
            switch (ch) {
                case 1:
//                    plus to minus
                    btnToggleSubtopicVisibility.animate()
                            .rotationBy(rotate)
                            .setDuration(100)
                            .scaleX(1.1f)
                            .scaleY(1.1f)
                            .withEndAction(() -> {

                                btnToggleSubtopicVisibility.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_remove_24));
                                subtopicRecyclerView.setVisibility(View.VISIBLE);

                                btnToggleSubtopicVisibility.animate()
                                        .rotationBy(rotate)
                                        .scaleX(1f)
                                        .scaleY(1f)
                                        .setDuration(100)
                                        .start();
                            }).start();
                    break;
                case 2:
//                    minus to plus
                    btnToggleSubtopicVisibility.animate()
                            .rotationBy(-rotate)
                            .setDuration(100)
                            .scaleX(1.1f)
                            .scaleY(1.1f)
                            .withEndAction(() -> {
                                btnToggleSubtopicVisibility.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_add_24));
                                subtopicRecyclerView.setVisibility(View.GONE);

                                btnToggleSubtopicVisibility.animate()
                                        .rotationBy(-rotate)
                                        .scaleX(1f)
                                        .scaleY(1f)
                                        .setDuration(100)
                                        .start();
                            }).start();
                    break;
            }
        }
    }
}
