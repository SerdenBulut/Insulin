package com.iuce.main;

import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.insulinhesaplama.R;
import com.iuce.adapter.InsulinAdapter;
import com.iuce.constants.Constant;
import com.iüce.control.IInsulinOperations;
import com.iüce.control.InsulinOperations;
import com.iüce.entity.Insulin;

public class RaporActivity extends ActionBarActivity {

	private IInsulinOperations insulinOperation;
	private ListView listDiary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_insulin);
		listDiary = (ListView) findViewById(R.id.listInsulin);

		insulinOperation = new InsulinOperations(this);

		List<Insulin> kayitlar = insulinOperation.listInsulin();
		InsulinAdapter adapter = new InsulinAdapter(getApplicationContext(),
				R.layout.kayit_list_row, kayitlar);
		listDiary.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case R.id.menu_aylar: {

			Constant.karar = 1;
			AlertDialog.Builder builderSingle = new AlertDialog.Builder(
					RaporActivity.this);
			builderSingle.setTitle("Aylar");
			final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
					RaporActivity.this,
					android.R.layout.select_dialog_singlechoice);
			for (int i = 1; i < Constant.aylar.length; i++) {
				arrayAdapter.add(Constant.aylar[i]);
			}
			builderSingle.setNegativeButton("Ýptal",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});

			builderSingle.setAdapter(arrayAdapter,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Constant.secim = arrayAdapter.getItem(which);
							insulinOperation = new InsulinOperations(
									getApplicationContext());

							List<Insulin> kayitlar = insulinOperation
									.listInsulin();
							InsulinAdapter adapter = new InsulinAdapter(
									getApplicationContext(),
									R.layout.kayit_list_row, kayitlar);
							listDiary.setAdapter(adapter);
						}
					});
			builderSingle.show();
		}
		default:
			return super.onOptionsItemSelected(item);
		}

	}

}