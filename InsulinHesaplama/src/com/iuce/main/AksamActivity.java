package com.iuce.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.insulinhesaplama.R;
import com.iuce.constants.Constant;

public class AksamActivity extends Activity {

	AlertDialog dialog;
	private EditText edtKalori;
	private Button btnAksamTamam;
	private TextView txtCorba;
	private TextView txtPilav;
	private TextView txtYemek;
	private TextView txtMeyve;
	private TextView txtTatli;
	private TextView txtIcecek;

	private double toplamKalori = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_aksam);
		edtKalori = (EditText) findViewById(R.id.edtKalori);
		txtCorba = (TextView) findViewById(R.id.txtCorba);
		txtPilav = (TextView) findViewById(R.id.txtPilav);
		txtYemek = (TextView) findViewById(R.id.txtYemek);
		txtMeyve = (TextView) findViewById(R.id.txtMeyve);
		txtTatli = (TextView) findViewById(R.id.txtTatlilar);
		txtIcecek = (TextView) findViewById(R.id.txtIcecek);
		btnAksamTamam = (Button) findViewById(R.id.btnTamamAksam);

		btnAksamTamam.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (edtKalori.getText().toString().equals("")) {
					edtKalori.setText("0");
				}

				toplamKalori = toplamKalori
						+ Integer.parseInt(edtKalori.getText().toString());

				Intent intentAksam = new Intent(AksamActivity.this,
						OlcumActivity.class);
				intentAksam.putExtra("toplamKalori", String.valueOf(toplamKalori));
				startActivity(intentAksam);
			}
		});

		txtCorba.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						AksamActivity.this);
				builderSingle.setTitle("Çorbalar");
				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						AksamActivity.this,
						android.R.layout.select_dialog_singlechoice);
				for (int i = 1; i < Constant.corbalar.length; i++) {
					arrayAdapter.add(Constant.corbalar[i]);
				}
				builderSingle.setNegativeButton("Ýptal",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});

				builderSingle.setAdapter(arrayAdapter,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								toplamKalori = (toplamKalori + Constant.corbalarKalori[which]);

							}
						});
				builderSingle.show();
			}
		});

		txtPilav.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						AksamActivity.this);
				builderSingle.setTitle("Pilav, Makarna ve Salatalar");
				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						AksamActivity.this,
						android.R.layout.select_dialog_singlechoice);
				for (int i = 1; i < Constant.pilavlar.length; i++) {
					arrayAdapter.add(Constant.pilavlar[i]);
				}
				builderSingle.setNegativeButton("Ýptal",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});

				builderSingle.setAdapter(arrayAdapter,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								toplamKalori = (toplamKalori + Constant.pilavlarKalori[which]);
							}
						});
				builderSingle.show();
			}
		});

		txtYemek.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						AksamActivity.this);
				builderSingle.setTitle("Ana Yemekler");
				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						AksamActivity.this,
						android.R.layout.select_dialog_singlechoice);
				for (int i = 1; i < Constant.yemekler.length; i++) {
					arrayAdapter.add(Constant.yemekler[i]);
				}
				builderSingle.setNegativeButton("Ýptal",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});

				builderSingle.setAdapter(arrayAdapter,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								toplamKalori = (toplamKalori + Constant.yemeklerKalori[which]);
							}
						});
				builderSingle.show();
			}
		});

		txtMeyve.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						AksamActivity.this);
				builderSingle.setTitle("Meyveler");
				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						AksamActivity.this,
						android.R.layout.select_dialog_singlechoice);
				for (int i = 1; i < Constant.meyveler.length; i++) {
					arrayAdapter.add(Constant.meyveler[i]);
				}
				builderSingle.setNegativeButton("Ýptal",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});

				builderSingle.setAdapter(arrayAdapter,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								toplamKalori = (toplamKalori + Constant.meyvelarKalori[which]);
							}
						});
				builderSingle.show();
			}
		});

		txtTatli.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						AksamActivity.this);
				builderSingle.setTitle("Tatlýlar");
				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						AksamActivity.this,
						android.R.layout.select_dialog_singlechoice);
				for (int i = 1; i < Constant.tatlilar.length; i++) {
					arrayAdapter.add(Constant.tatlilar[i]);
				}
				builderSingle.setNegativeButton("Ýptal",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});

				builderSingle.setAdapter(arrayAdapter,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								toplamKalori = (toplamKalori + Constant.tatlilarKalori[which]);
							}
						});
				builderSingle.show();
			}
		});

		txtIcecek.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						AksamActivity.this);
				builderSingle.setTitle("Ýçecekler");
				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						AksamActivity.this,
						android.R.layout.select_dialog_singlechoice);
				for (int i = 1; i < Constant.icecekler.length; i++) {
					arrayAdapter.add(Constant.icecekler[i]);
				}
				builderSingle.setNegativeButton("Ýptal",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});

				builderSingle.setAdapter(arrayAdapter,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								toplamKalori = (toplamKalori + Constant.iceceklerKalori[which]);
							}
						});
				builderSingle.show();
			}
		});
	}

}
