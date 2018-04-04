package com.zannete.syuping.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zannete.syuping.R;

public class AddProductActivity extends AppCompatActivity {

  public static final int SEARCH_STORE_RESULT = 0;
  public static final String SEARCH_STORE = "AddProductActivity/SEARCH_STORE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);

		Button btnCheckStore = findViewById(R.id.btnCheckStore);
		btnCheckStore.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        EditText etStoreID = findViewById(R.id.etStoreID);

        Intent i = new Intent(AddProductActivity.this, SearchStoreActivity.class);
        i.putExtra(AddProductActivity.SEARCH_STORE, etStoreID.getText().toString());
        startActivityForResult(i, AddProductActivity.SEARCH_STORE_RESULT);
      }
    });
	}
}
