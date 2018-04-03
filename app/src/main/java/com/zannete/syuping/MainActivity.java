package com.zannete.syuping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

	FirebaseDatabase database = FirebaseDatabase.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnCheckPrice = findViewById(R.id.btnCheckPrice);
	}
}
