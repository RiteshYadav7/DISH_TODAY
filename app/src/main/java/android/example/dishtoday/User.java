package android.example.dishtoday;

import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class User {

    private String email;
    private  String password;
    private  String fullname;
    private  String number;



    public User(){

    }
    public User(String email, String password){
        this.email=email;
        this.password=password;

    }

    public User(String email, String password, String fullname, String number){
        this.email=email;
        this.password=password;
        this.fullname=fullname;
        this.number=number;



    }



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getNumber() {
        return number;
    }







}
