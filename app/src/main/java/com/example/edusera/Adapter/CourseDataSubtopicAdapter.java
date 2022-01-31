package com.example.edusera.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edusera.Database.CourseDataDatabase;
import com.example.edusera.Model.CourseFeedModel;
import com.example.edusera.Model.TopicModel;
import com.example.edusera.R;
import com.example.edusera.databinding.CourseDataTitleItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class CourseDataSubtopicAdapter extends RecyclerView.Adapter<CourseDataSubtopicAdapter.ViewHolder> {
    private CourseDataTitleItemBinding binding;
    private Context context;
    private List<TopicModel> mList;

    public OnSubtopicClickListener listener;

    public interface OnSubtopicClickListener {
        void onSubtopicClick(TopicModel model, int position);
    }

    public void setOnItemClickListener(OnSubtopicClickListener listener) {
        this.listener = listener;
    }

    public CourseDataSubtopicAdapter(Context context, List<TopicModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CourseDataSubtopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CourseDataTitleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CourseDataSubtopicAdapter.ViewHolder holder, int position) {
        final TopicModel topicModel = mList.get(position);

        holder.subTopic.setText(topicModel.getTitle());
        holder.duration.setText(context.getString(R.string.subtopicTime, topicModel.getDuration()));


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subTopic, duration;
        AppCompatImageView playButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subTopic = binding.tvSubtopic;
            duration = binding.tvDuration;
            playButton = binding.btnPlay;

            itemView.setOnClickListener(view -> {
             if (listener != null) {
                 int position = getAdapterPosition();
                 if (position != RecyclerView.NO_POSITION) {
                     TopicModel model = mList.get(position);
                     listener.onSubtopicClick(model, position);
                 }

             }
            });
        }
    }
}
