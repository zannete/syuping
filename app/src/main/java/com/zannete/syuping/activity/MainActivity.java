package com.zannete.syuping.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zannete.syuping.R;

public class MainActivity extends AppCompatActivity implements OnCompleteListener<AuthResult> {

	FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
	FirebaseAuth mAuth = FirebaseAuth.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

    mAuth.signInAnonymously().addOnCompleteListener(this);
	}

  @Override
  public void onComplete(@NonNull Task<AuthResult> task) {
	  if(task.isSuccessful()) {
      Button btnCheckPrice = findViewById(R.id.btnCheckPrice);
      btnCheckPrice.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          EditText etBarcode = findViewById(R.id.etBarcode);
          String barcode = etBarcode.getText().toString();

          DatabaseReference productRef = mDatabase.getReference("/product/" + barcode);
          productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              if(dataSnapshot.exists()){

              }else{
                Intent i = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(i);
              }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
              Log.d("AMOURE", "cancelled");
            }
          });
        }
      });
    }
  }
}
