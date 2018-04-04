package com.zannete.syuping.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zannete.syuping.R;
import com.zannete.syuping.adapter.StoreSearchResultAdapter;
import com.zannete.syuping.model.Store;

import java.util.ArrayList;

public class SearchStoreActivity extends AppCompatActivity implements View.OnClickListener {

  FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
  RecyclerView rvSearchResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_store);

		Button btnSearch = findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(this);

		Intent i = getIntent();
		String searchID = i.getStringExtra(AddProductActivity.SEARCH_STORE);
		EditText etName = findViewById(R.id.etName);
		etName.setText(searchID);

		if(!etName.getText().toString().isEmpty()){
		  btnSearch.performClick();
    }

    rvSearchResult = findViewById(R.id.rvSearchResult);
		rvSearchResult.setLayoutManager(new LinearLayoutManager(this));
		rvSearchResult.setHasFixedSize(true);
	}

  @Override
  public void onClick(View view) {
	  EditText etName = findViewById(R.id.etName);
    DatabaseReference storeRef = mDatabase.getReference("/store");
    storeRef.orderByChild("name").startAt(etName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot.exists()){
          ArrayList<Store> arrStore = new ArrayList<>();
          for(DataSnapshot snapshot: dataSnapshot.getChildren()){
            Store store = new Store();
            store.setName(snapshot.child("name").getValue().toString());
            store.setAddress(snapshot.child("address").getValue().toString());
            store.setCity(snapshot.child("city").getValue().toString());
            store.setId(snapshot.getKey());
            arrStore.add(store);
          }
          StoreSearchResultAdapter adapter = new StoreSearchResultAdapter(arrStore);
          rvSearchResult.setAdapter(adapter);
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }
}
