package com.iuce.main;

import com.example.insulinhesaplama.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RaporMenu extends Activity {
	private Button btnGrafik;

	private Button btnAnaliz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rapormenu);
		btnGrafik = (Button) findViewById(R.id.btnGrafik);

		btnAnaliz = (Button) findViewById(R.id.btnAnaliz);

		btnGrafik.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent iTum = new Intent(RaporMenu.this, GraphicActivity.class);
				startActivity(iTum);
			}
		});

		btnAnaliz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent iAy = new Intent(RaporMenu.this, RaporActivity.class);
				startActivity(iAy);
			}
		});
	}

}
