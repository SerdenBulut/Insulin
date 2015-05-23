package com.iuce.main;

import com.example.insulinhesaplama.R;
import com.iüce.control.ILoginControl;
import com.iüce.control.LoginControl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button btnLogin;
	private EditText edtTxtPassword;
	private boolean isFirst = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_giris);

		edtTxtPassword = (EditText) findViewById(R.id.edttxtPincode);
		btnLogin = (Button) findViewById(R.id.btnLogin);

		final ILoginControl logControl = new LoginControl(
				getApplicationContext());
		isFirst = logControl.controlFirstOpen();
		if (isFirst) {
			btnLogin.setText("Ýlk Þifreniz");

		}
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String password = edtTxtPassword.getText().toString();
				if (isFirst) {
					logControl.savePassword(password);
					Toast.makeText(getApplicationContext(),
							"Þifreniz baþarýyla kaydedilmiþtir...",
							Toast.LENGTH_LONG).show();
					Intent intent = new Intent(LoginActivity.this,
							MainActivity.class);
					startActivity(intent);
				} else {
					if (logControl.controlPassword(password)) {
						Intent intent = new Intent(LoginActivity.this,
								MainActivity.class);
						startActivity(intent);
					} else
						Toast.makeText(getApplicationContext(),
								"Geçersiz þifre...", Toast.LENGTH_LONG).show();

				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}

}
