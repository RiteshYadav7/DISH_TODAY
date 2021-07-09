package android.example.dishtoday;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class SwipeActivity extends AppCompatActivity {



    private ArrayList<String> a2=new ArrayList<>(); // This was throwing an error bcz i havn't initialize it.
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipeframe);

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> message = bundle.getStringArrayList("string-array");
        a2.clear();
        a2.add("Start Swiping");


       for(int k=0;k<message.size()-1;k++) {
           DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("State").child(message.get(k)).child(message.get(message.size() - 1));
           Log.v("SwipeActivity","Good");

           reference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   Log.v("SwipeActivity", "Ketan");

                   for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                       a2.add(snapshot.getValue().toString());

                       Log.v("SwipeActivity", snapshot.getValue().toString());


                   }


               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
       }





        arrayAdapter = new ArrayAdapter<>(this, R.layout.swipe, R.id.helloText, a2 ); // Declaring it before anything was gone into a2.Mistake


        SwipeFlingAdapterView flingContainer= findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);


        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                a2.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(SwipeActivity.this, "Left!",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                Toast.makeText(SwipeActivity.this, "Right!",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                a2.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(SwipeActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
            }
        });

    }






}
