package android.example.dishtoday.Adapter;

import android.content.Context;
import android.example.dishtoday.Models.Story;
import android.example.dishtoday.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.viewHolder>
{
    ArrayList<Story>list;
    Context context;

    public StoryAdapter(ArrayList<Story> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_rv_design,parent,false);

        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Story model = list.get(position);
        holder.addStoryImg.setImageResource(model.getAddStoryImg());
        holder.profile_image.setImageResource(model.getProfile_image());
        holder.storyType.setImageResource(model.getStoryType());
        holder.name.setText(model.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView addStoryImg ,profile_image,storyType;
        TextView name;
        public viewHolder (@NonNull View itemView ){
            super(itemView);
            addStoryImg =itemView.findViewById(R.id.addStoryImg);
            profile_image=itemView.findViewById(R.id.profile_image);
            storyType=itemView.findViewById(R.id.storyType);
            name = itemView.findViewById(R.id.name);


        }

    }
}
