package nyc.c4q.cityzenapp.recview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.cityzenapp.R;
import nyc.c4q.cityzenapp.data.GetProjects;
import nyc.c4q.cityzenapp.model.Project;
import nyc.c4q.cityzenapp.ui.CollectContract;
import nyc.c4q.cityzenapp.ui.CollectPresenter;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private List<Project> projectsList = new ArrayList<>();
    private CollectContract.View activity;

    public ProjectAdapter(List<Project> projectsList, CollectContract.View activity) {
        this.projectsList = projectsList;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View childview = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new ProjectViewHolder(childview);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projectsList.get(position);
        holder.onBind(project);

    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder {

        private ImageView projectImg;
        private TextView projectName;

        public ProjectViewHolder(View itemView) {
            super(itemView);
            projectImg = itemView.findViewById(R.id.project_img);
            projectName = itemView.findViewById(R.id.project_name);

        }

        public void onBind(final Project project) {
            projectName.setText(project.getName());
            Picasso.get().load(project.getImg()).fit().into(projectImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.showDialog(project.getCollection_name());

                }
            });
        }
    }
}
