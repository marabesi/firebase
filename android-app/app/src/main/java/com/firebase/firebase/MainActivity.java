package com.firebase.firebase;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import services.People;
import services.Url;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private ListView list;

    private static Firebase firebaseRef;
    private ProgressDialog dialog;
    private ArrayList<People> listAdapter = new ArrayList<People>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        list = (ListView) findViewById(R.id.list);

        final ArrayAdapter adapter = new ArrayAdapter<People>(this, android.R.layout.simple_list_item_1, listAdapter);
        list.setAdapter(adapter);

        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(Url.FIREBASE);

        Firebase people = firebaseRef.child("people").getRef();
        people.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap<String, String> firebaseObject = (HashMap<String, String>) dataSnapshot.getValue();

                People current = new People();

                current.setName(firebaseObject.get("name").toString());
                current.setEmail(firebaseObject.get("name").toString());

                listAdapter.add(current);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void onSave(View view)
    {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Firebase connect");
        dialog.setMessage("Saving your data");
        dialog.show();

        People p1 = new People(name.getText().toString(), email.getText().toString());

        Firebase people = firebaseRef.child("people");
        people.push().setValue(p1, new Firebase.CompletionListener() {

            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                dialog.dismiss();

                Toast.makeText(MainActivity.this, "Your contact has been saved", Toast.LENGTH_SHORT).show();

                name.setText("");
                email.setText("");
            }
        });
    }
}

