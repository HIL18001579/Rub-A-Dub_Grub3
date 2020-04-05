package com.example.rub_a_dub_grub3;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Adapter.SelectionAdapter;
import Animations.LoadingDialog;
import Model.SelectionListItem;

public class SelectionActivity extends AppCompatActivity {

    private static final String TAG = "SelectionActivity";

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<SelectionListItem> DiningSelection;
    SelectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        recyclerView = findViewById(R.id.recyclerSelection);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DiningSelection = new ArrayList<SelectionListItem>();
        final LoadingDialog loadingDialog = new LoadingDialog( SelectionActivity.this );
        final LoadingDialog dismissDialog = new LoadingDialog( SelectionActivity.this );

        loadingDialog.startLoadingDialog(); // Only shows when loading from Firebase
        //Handler handler = new Handler(  );
        //handler.postDelayed( new Runnable() {
            //@Override
            //public void run() {
                //loadingDialog.dismissDialog();
            //}
        //}, 3000 );

        reference = FirebaseDatabase.getInstance().getReference().child("DiningSelection");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    SelectionListItem Si = dataSnapshot1.getValue(SelectionListItem.class);
                    DiningSelection.add(Si);
                    loadingDialog.dismissDialog(); // Dismisses dialog after retrieving information from Firebase
                }

                adapter = new SelectionAdapter( SelectionActivity.this, DiningSelection);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SelectionActivity.this, "Error downloading information from database, please restart the app and try again", Toast.LENGTH_SHORT).show();
                loadingDialog.dismissDialog(); // Dismisses dialog if connection/trnasfer failure
            }
        });
    }
}
