package android.example.dishtoday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_in_activity extends AppCompatActivity {
    EditText Email, Password;
    Button Login;
    private FirebaseAuth firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_activity);
//        getSupportActionBar().setTitle("Sign In");
        Email= (EditText)findViewById(R.id.editTextTextPersonName);
        Password= (EditText)findViewById(R.id.editTextTextPassword);
        Login= (Button)findViewById(R.id.button3);
        firebase=FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Sign_in_activity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;


                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Sign_in_activity.this, "Password", Toast.LENGTH_SHORT).show();
                    return;

                }

                firebase.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(
                                            @NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                            Toast.makeText(Sign_in_activity.this, "Welcome Again", Toast.LENGTH_SHORT).show();


                                        } else {

                                            // sign-in failed
                                            Toast.makeText(Sign_in_activity.this, "Retry, Login Failed", Toast.LENGTH_SHORT).show();
                                            // hide the progress bar

                                        }
                                    }
                                });
            }



        });
        Button go2 = findViewById(R.id.google2);
        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sign_in_activity.this, "Redirecting",Toast.LENGTH_SHORT).show();
                String url = "http://www.gmail.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button fb2 = findViewById(R.id.facebook2);
        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sign_in_activity.this, "Redirecting",Toast.LENGTH_SHORT).show();
                String url = "http://www.facebook.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
    }
}