package android.example.dishtoday;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class CategorySubmit extends AppCompatActivity {

    ListView listview;
    ListView listViewTime;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterTime;
    String selectedFromList;


    private ArrayList<String> al = new ArrayList<>(); // This was throwing an error bcz i havn't initialize it.
    ArrayAdapter<String> arrayAdapter;

    String[] States =
            {"Andhra Pradesh",
                    "Arunachal Pradesh", "Assam"
                    , "Bihar"
                    , "Chhattisgarh"
                    , "Goa"
                    , "Gujarat"
                    , "Haryana"
                    , "Himachal Pradesh"
                    ,
                    "Jharkhand", "Karnataka",

                    "Kerala"
                    , "Madhya Pradesh"
                    , "Maharashtra"
                    , "Manipur"
                    , "Meghalaya"
                    , "Mizoram"
                    , "Nagaland"
                    , "Odisha"
                    , "Punjab"
                    , "Rajasthan"
                    , "Sikkim"
                    , "Tamil Nadu"
                    , "Telangana"
                    , "Tripura"
                    , "Uttarakhand"
                    , "UttarPradesh" // In database it was UttarPradesh & here it was Uttar Pradesh
                    , "West Bengal"};

    String [] Time= {"Breakfast"
            ,"Lunch",
            "Dinner"};




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
       Toolbar toolbar =findViewById(R.id.submit_here);



        Log.v("CategoryActivity", "Fine1");
        listview = findViewById(R.id.ListView_data);
        listViewTime=findViewById(R.id.ListView_Time);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, States);
        adapterTime = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, Time);

        listview.setAdapter(adapter);
        listViewTime.setAdapter(adapterTime);
        setSupportActionBar(toolbar);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        al.clear();
        int id = item.getItemId();
        if (id == R.id.item_done) {
            for (int i = 0; i < listview.getCount(); i++) {
                if (listview.isItemChecked(i)) {
                    String selectedFromList = (String) (listview.getItemAtPosition(i));
                    al.add(selectedFromList);
                    Log.v("Category","Fine1");


//
//
                }

//

            }

            for(int k=0;k<listViewTime.getCount();k++)
            {
                if(listViewTime.isItemChecked((k))){
                    selectedFromList= (String) listViewTime.getItemAtPosition(k);
                    al.add(selectedFromList);

                }
            }
            startNewActivity();
        }
        return  super.onOptionsItemSelected(item);
    }


    private void startNewActivity(){
        Intent intent = new Intent(this,SwipeActivity.class);
        intent.putExtra("string-array", al); // to send string to Swipe Activity
        startActivity(intent);

        finish();
    }
}












