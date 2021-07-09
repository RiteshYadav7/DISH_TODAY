package android.example.dishtoday.Fragment;


import android.example.dishtoday.Adapter.FriendAdapter;
import android.example.dishtoday.Models.FriendModel;
import android.example.dishtoday.R;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FriendModel> list;
    Toolbar toolbar;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView = view.findViewById(R.id.friendsRV);
        list = new ArrayList<>();
        list.add(new FriendModel(R.drawable.profile));

        list.add(new FriendModel(R.drawable.nature_dordogne));
        list.add(new FriendModel(R.drawable.nature));
        list.add(new FriendModel(R.drawable.cover));
        list.add(new FriendModel(R.drawable.deaf));
        list.add(new FriendModel(R.drawable.original));
        list.add(new FriendModel(R.drawable.profile1));
        list.add(new FriendModel(R.drawable.nature1));

        FriendAdapter adapter = new FriendAdapter(list,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//        toolbar = view.findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        return view;
    }


    /*@Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }*/

    /*@Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.notification).setVisible(true);
        menu.findItem(R.id. profile).setVisible(true);
        menu.findItem(R.id. setting).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }*/
}