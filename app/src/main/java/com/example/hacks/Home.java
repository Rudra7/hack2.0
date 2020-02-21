package com.example.hacks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Home extends AppCompatActivity {

    public static String selectedLocation;
    private ListView listView;
    public TextView text;
    public Button button;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private FirebaseDatabase database;
    private EditText enteredLoc;
    private DatabaseReference reference;
    public void proceed(View view){
        Intent intent=new Intent(getApplicationContext(),Dump.class);
        startActivity(intent);
    }
    public void func(View view)
    {
        arrayList.clear();
        String s=enteredLoc.getText().toString();
        String loc="Location/"+s;
        database=FirebaseDatabase.getInstance();
        reference=database.getReference(loc);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("entered","entered");
                Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();
                System.out.println("Author: " + newPost.get("loc1"));
                arrayList.add(newPost.get("loc1").toString());
                arrayList.add(newPost.get("loc2").toString());
                arrayList.add(newPost.get("loc3").toString());
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        enteredLoc=findViewById(R.id.locInput);
        listView=findViewById(R.id.list);
        button=findViewById(R.id.button);
        arrayList=new ArrayList<String>();
        arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                button.setVisibility(View.VISIBLE);
                selectedLocation=arrayList.get(i);
            }
        });
    }
}
