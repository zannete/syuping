package com.zannete.syuping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zannete.syuping.R;

public class AddStoreActivity extends AppCompatActivity {

	FirebaseDatabase database = FirebaseDatabase.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_store);

		Button btnAddStore = findViewById(R.id.btnAddStore);
		btnAddStore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				EditText etName = findViewById(R.id.etName);
				EditText etAddress = findViewById(R.id.etAddress);
				EditText etCity = findViewById(R.id.etCity);

				String name = etName.getText().toString();
				String address = etAddress.getText().toString();
				String city = etCity.getText().toString();

				DatabaseReference storeRef = database.getReference("/store");
				DatabaseReference newStoreRef = storeRef.push();
				newStoreRef.child("name").setValue(name);
				newStoreRef.child("address").setValue(address);
				newStoreRef.child("city").setValue(city);
			}
		});
	}
}
