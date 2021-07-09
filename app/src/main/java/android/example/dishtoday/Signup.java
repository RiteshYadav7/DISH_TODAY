package android.example.dishtoday;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.firestore.v1.TargetOrBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import id.zelory.compressor.Compressor;
public class Signup extends AppCompatActivity {


    EditText txtEmail, Password, Name, number;
    Button Register;
    private static final String TAG = "Signup";


    private FirebaseAuth firebaseauth;
    private ImageView profilepic;
    private Uri uri;
    FloatingActionButton fab;
    FloatingActionButton upload;
    String userId;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    FirebaseFirestore fstore;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    Map<String, Object> Duser;
    Bitmap compressor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
//        getSupportActionBar().setTitle("Sign Up");

        fstore = FirebaseFirestore.getInstance();

        txtEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        Password = (EditText) findViewById(R.id.editTextTextPassword);
        Name = (EditText) findViewById((R.id.editTextTextPersonName));
        number = (EditText) findViewById(R.id.editTextPhone);
        Register = (Button) findViewById(R.id.button3);
        profilepic = findViewById(R.id.profile_image);
        fab = findViewById(R.id.floatingActionButton);



        firebaseauth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(Signup.this)
                        .crop()
                        .cropOval()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start(20);

            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String name = Name.getText().toString().trim();
                String num = number.getText().toString().trim();
                String password = Password.getText().toString().trim();


                User user = new User(email, password, name, num);







                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signup.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;


                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signup.this, "Password", Toast.LENGTH_SHORT).show();
                    return;


                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Signup.this, "Please Enter name", Toast.LENGTH_SHORT).show();
                    return;


                }

                if (password.length() < 6) {
                    Toast.makeText(Signup.this, "Password Length too short", Toast.LENGTH_SHORT).show();

                } else {

                    File newfile= new File(uri.getPath());
                    try {
                        compressor = new Compressor(Signup.this)
                                .setMaxHeight(125)
                                .setMaxWidth(125)
                                .setQuality(50)
                                .compressToBitmap(newfile);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
                    compressor.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    byte [] thumb= byteArrayOutputStream.toByteArray();
                    UploadTask image_path=storageReference.child("user").child(userId+".jpg").putBytes(thumb);
                    image_path.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                storeUserData(task,email,name,num,password);
                            }
                        }
                    });






                    firebaseauth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()) {

                                        userId = firebaseauth.getCurrentUser().getUid();
                                        DocumentReference documentReference = fstore.collection("users").document(userId);

                                       Duser  = new HashMap<>();
                                        Duser.put("Email", txtEmail.getText().toString().trim());
                                        Duser.put("Name", Name.getText().toString().trim());
                                        Duser.put("Number", number.getText().toString().trim());
                                        Duser.put("Password", Password.getText().toString().trim());



                                        documentReference.set(Duser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
//
                                                Toast.makeText(Signup.this, "Registarion Successful", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            }
                                        });


                                    } else {

                                        Toast.makeText(Signup.this, "Firebase too busy!", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                }


            }
        });
    }

    private void storeUserData(Task<UploadTask.TaskSnapshot> task, String email, String name, String num, String password) {
        Uri Download_uri;
        if(task!=null){
            storageReference.child("user_image");
            StorageReference ImagePath= storageReference.child(userId+".jpg");

            ImagePath.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    ImagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Duser.put("Name", Name.getText().toString().trim());
                            Duser.put("userImage",uri.toString());



                            fstore.collection("users").document(userId).update(Duser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())




                                    Toast.makeText(getApplicationContext(),"Well Done",Toast.LENGTH_SHORT).show();
                                }

                            });



                        }
                    });
                }
            });


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && data != null && resultCode == RESULT_OK && data.getData() != null) {
            uri = data.getData();





            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                profilepic.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }


    }
















