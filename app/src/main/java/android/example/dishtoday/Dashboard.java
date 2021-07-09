package android.example.dishtoday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import android.net.Uri;

public class Dashboard extends AppCompatActivity
{
    TextView Swipe2Sel;
    private TextView UserName;
    private ImageView UserImage;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    private EditText Search;
    TextView Bloggers;


    StorageReference storageReference;

      String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String name;







    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        UserImage = findViewById(R.id.imageView2);
        TextView Name = (TextView) findViewById(R.id.UserName);


        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        uid = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fstore.collection("users").document(uid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Name.setText(value.getString("Name"));
                String s = value.getString("userImage");
                Uri ImageURL = Uri.parse(s);
                Glide.with(Dashboard.this).load(ImageURL).into(UserImage);


            }
        });

            Swipe2Sel= (TextView)findViewById(R.id.textView1);
            Swipe2Sel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, CategorySubmit.class);
                    startActivity(intent);
                    Toast.makeText(Dashboard.this,"Cool",Toast.LENGTH_SHORT).show();
                }
            });

            Bloggers = (TextView)findViewById(R.id.textView0);
            Bloggers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(Dashboard.this,Blogger.class);
                    startActivity(intent);
                    Toast.makeText(Dashboard.this,"What's New?",Toast.LENGTH_SHORT).show();

                }
            });










        SearchView MySearch =findViewById(R.id.SearchView);
        MySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                    Intent intent = new Intent(Intent.ACTION_SEARCH);
                     intent.setPackage("com.google.android.youtube");
                     intent.putExtra(SearchManager.QUERY, query);
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  startActivity(intent);
                  return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });







             }














        }



