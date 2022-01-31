package com.example.edusera.Adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edusera.Model.CourseFeedModel;
import com.example.edusera.R;
import com.example.edusera.databinding.CourseFeedItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseFeedAdapter extends RecyclerView.Adapter<CourseFeedAdapter.ViewHolder> {

    private Context context;
    private List<CourseFeedModel> mList;
    private CourseFeedItemBinding binding;

    public OnItemClickListener listener;

    public interface OnItemClickListener {
        void onCourseClick(CourseFeedModel model, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CourseFeedAdapter(Context context, List<CourseFeedModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CourseFeedItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CourseFeedModel courseFeedModel = mList.get(position);

        holder.title.setText(courseFeedModel.getTitle());
        holder.instructorName.setText(courseFeedModel.getInstructorName());
        holder.startDate.setText(courseFeedModel.getStartDate());

        if (!courseFeedModel.getLive()) {
            holder.isLive.setTextColor(context.getResources().getColor(R.color.textLightGray, context.getTheme()));
            for (Drawable drawable: binding.tvCourseLiveStatus.getCompoundDrawables()) {
                if (drawable != null) {
                    drawable.setColorFilter(new PorterDuffColorFilter(context.getColor(R.color.textLightGray), PorterDuff.Mode.SRC_IN));
                }
            }
        } else {
            holder.isLive.setTextColor(context.getResources().getColor(R.color.red, context.getTheme()));
            for (Drawable drawable: binding.tvCourseLiveStatus.getCompoundDrawables()) {
                if (drawable != null) {
                    drawable.setColorFilter(new PorterDuffColorFilter(context.getColor(R.color.red), PorterDuff.Mode.SRC_IN));
                }
            }
        }

        Glide.with(context).load(courseFeedModel.getCourseThumbnail()).into(holder.courseImage);
        Glide.with(context).load(courseFeedModel.getInstructorImage()).into(holder.instructorImage);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void getAllCourses(List<CourseFeedModel> mList) {
        this.mList = mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, instructorName, startDate, isLive;
        ImageView instructorImage, courseImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = binding.tvCourseTitle;
            instructorName = binding.tvInstructorName;
            startDate = binding.tvStartDate;
            isLive = binding.tvCourseLiveStatus;
            instructorImage = binding.ivInstructorThumbnail;
            courseImage = binding.ivCourseThumbnail;

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        CourseFeedModel model = mList.get(position);
                        listener.onCourseClick(model, position);
                    }
                }
            });
        }
    }
}
