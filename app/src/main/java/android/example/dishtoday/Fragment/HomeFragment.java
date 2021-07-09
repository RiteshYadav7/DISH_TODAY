package android.example.dishtoday.Fragment;

import android.example.dishtoday.Adapter.DashBoardAdapter;
import android.example.dishtoday.Adapter.StoryAdapter;
import android.example.dishtoday.Models.DashBoardModel;
import android.example.dishtoday.Models.Story;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.dishtoday.R;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView storyrv, DashBoardrv;
    ArrayList<Story>list;
    ArrayList<DashBoardModel> dashBoardList;



    public HomeFragment() {


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view= inflater.inflate(R.layout.fragment_home, container, false);
        storyrv= view.findViewById(R.id.storyRV);
        list=new ArrayList<>();
        list.add(new Story(R.drawable.dennis,R.drawable.ic_video_camera,R.drawable.deaf,"Ketan"));
        list.add(new Story(R.drawable.nature,R.drawable.ic_video_camera,R.drawable.deaf,"Rahul"));
        list.add(new Story(R.drawable.profile1,R.drawable.ic_video_camera,R.drawable.deaf,"Prakash"));
        list.add(new Story(R.drawable.search,R.drawable.ic_video_camera,R.drawable.deaf,"Abhijeet"));
        list.add(new Story(R.drawable.search,R.drawable.ic_video_camera,R.drawable.deaf,"Abhijeet"));


        StoryAdapter dashboardAdapter = new StoryAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        storyrv.setLayoutManager(linearLayoutManager);

        storyrv.setNestedScrollingEnabled(false);
        storyrv.setAdapter(dashboardAdapter);



        DashBoardrv= view.findViewById(R.id.dashboardRV);
        dashBoardList = new ArrayList<>();
        dashBoardList.add(new DashBoardModel(R.drawable.profile,R.drawable.new_hope,R.drawable.saved,"Ketan","Polymath","400","Cool","15"));
        dashBoardList.add(new DashBoardModel(R.drawable.profile,R.drawable.new_hope,R.drawable.saved,"Ketan","Polymath","400","Cool","15"));

        dashBoardList.add(new DashBoardModel(R.drawable.profile,R.drawable.new_hope,R.drawable.saved,"Ketan","Polymath","400","Cool","15"));

        dashBoardList.add(new DashBoardModel(R.drawable.profile,R.drawable.new_hope,R.drawable.saved,"Ketan","Polymath","400","Cool","15"));

        dashBoardList.add(new DashBoardModel(R.drawable.profile,R.drawable.new_hope,R.drawable.saved,"Ketan","Polymath","400","Cool","15"));

        DashBoardAdapter dashBoardAdapter2= new DashBoardAdapter(dashBoardList,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        DashBoardrv.setLayoutManager(layoutManager);
        DashBoardrv.setNestedScrollingEnabled(false);
        DashBoardrv.setAdapter(dashBoardAdapter2);

//        addStory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//            }
//       });
        return view;

    }
}