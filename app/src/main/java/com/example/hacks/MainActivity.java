package com.example.hacks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    private EditText email;
    private EditText pass;
    public void register(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }
    public void myfunc(View view)
    {
        String s1=email.getText().toString();
        String s2=pass.getText().toString();
            mAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(MainActivity.this, "Log in success", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.emailInput);
        pass=findViewById(R.id.passInput);
        mAuth = FirebaseAuth.getInstance();
    }
}
