package com.iuce.main;

import com.example.insulinhesaplama.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	public Button btnKhvlt;
	public Button btnOgle;
	public Button btnAksam;
	public Button btnRapor;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ana);

		btnKhvlt = (Button) findViewById(R.id.btnkah);
		btnOgle = (Button) findViewById(R.id.btnOgle);
		btnAksam = (Button) findViewById(R.id.btnaks);
		btnRapor = (Button) findViewById(R.id.btnrap);

		btnKhvlt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this, KahvaltiActivity.class);
				startActivity(intent);
			}
		});

		btnOgle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this, AksamActivity.class);
				startActivity(intent);
			}
		});

		btnAksam.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this, AksamActivity.class);
				startActivity(intent);
			}
		});

		btnRapor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this, RaporMenu.class);
				startActivity(intent);
			}
		});
	}
}
