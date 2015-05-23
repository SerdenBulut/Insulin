package com.iuce.main;

import com.example.insulinhesaplama.R;
import com.iuce.constants.Constant;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class AcilisActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acilis);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent intent = new Intent(AcilisActivity.this,
						LoginActivity.class);
				startActivity(intent);

				AcilisActivity.this.finish();

				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

			}
		}, Constant.SLEEP_TIME);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
			}
		}, Constant.SLEEP_TIME);

	}

	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}
}
