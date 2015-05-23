package com.iuce.main;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.example.insulinhesaplama.R;
import com.iuce.constants.Constant;
import com.iüce.control.InsulinOperations;
import com.iüce.entity.Insulin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OlcumActivity extends Activity {

	private EditText edtKanseker;
	private Button btnSonuc;
	private Button btnKaydet;
	private Button btnAnasayfa;
	private TextView txtSonuc;
	private RadioGroup radiogroupIlaclar;
	private RadioButton radioLantus;
	private RadioButton radioHumulin;
	private double toplamKalori;
	private int selectedId;
	private static String date;
	private double kanSekeri;
	private double insulinMiktari;
	private InsulinOperations iOperations;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_olcum);

		Intent intent = getIntent();
		toplamKalori = Double
				.parseDouble(intent.getStringExtra("toplamKalori"));
		txtSonuc = (TextView) findViewById(R.id.txtSonuc);
		edtKanseker = (EditText) findViewById(R.id.edtKanseker);
		radiogroupIlaclar = (RadioGroup) findViewById(R.id.radiogrupIlac);
		radioLantus = (RadioButton) findViewById(R.id.radioLantus);
		radioHumulin = (RadioButton) findViewById(R.id.radioHumulin);
		btnSonuc = (Button) findViewById(R.id.btnTamamOlcum);
		btnKaydet = (Button) findViewById(R.id.btnKaydet);
		btnAnasayfa = (Button) findViewById(R.id.btnAnasayfa);

		iOperations = new InsulinOperations(getApplicationContext());

		btnSonuc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				selectedId = radiogroupIlaclar.getCheckedRadioButtonId();

				if (edtKanseker.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Lütfen kan þekeri deðerinizi giriniz!",
							Toast.LENGTH_LONG).show();
				} else {
					kanSekeri = Double.parseDouble((edtKanseker.getText()
							.toString()));
					SimpleDateFormat simple = new SimpleDateFormat(
							"dd.MMM.yyyy kk-mm-ss");
					Date d = new Date();
					date = simple.format(d);

					if (selectedId == radioLantus.getId()) {

						insulinMiktari = hesaplama(toplamKalori,
								Constant.lantus, kanSekeri);
						txtSonuc.setText("Ýnsülin miktarý: "
								+ String.valueOf(insulinMiktari));
						AlertDialog.Builder alertd = new AlertDialog.Builder(
								OlcumActivity.this);
						alertd.setTitle("Hatýrlatma")
								.setMessage(
										"Sonuçlarý doktorunuza göstermek için kaydediniz.")
								.setCancelable(true)
								.setPositiveButton("Tamam",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												dialog.dismiss();
											}
										});

						alertd.show();
					} else if (selectedId == radioHumulin.getId()) {
						insulinMiktari = hesaplama(toplamKalori,
								Constant.humulin, kanSekeri);
						txtSonuc.setText("Ýnsülin miktarý: "
								+ String.valueOf(insulinMiktari));
						AlertDialog.Builder alertd = new AlertDialog.Builder(
								OlcumActivity.this);
						alertd.setTitle("Hatýrlatma")
								.setCancelable(true)
								.setMessage(
										"Sonuçlarý doktorunuza göstermek için kaydediniz.")
								.setPositiveButton("Tamam",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												dialog.dismiss();
											}
										});

						alertd.show();

					} else {
						Toast.makeText(getApplicationContext(),
								"Lütfen ilacýnýzý seçiniz.", Toast.LENGTH_SHORT)
								.show();

					}
				}
			}
		});

		btnKaydet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (edtKanseker.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Lütfen kan þekeri deðerinizi giriniz!",
							Toast.LENGTH_LONG).show();
				} else {
					if (selectedId == 0) {
						Toast.makeText(getApplicationContext(),
								"Lütfen ilacýnýzý seçiniz.", Toast.LENGTH_SHORT)
								.show();
					} else {
						btnSonuc.setEnabled(false);
						edtKanseker.setFocusable(false);
						edtKanseker.setFocusableInTouchMode(false);
						edtKanseker.setClickable(false);
						saveInsulin();
					}
				}
				Toast.makeText(getApplicationContext(),
						"Deðerleriniz baþarýyla kaydedildi...",
						Toast.LENGTH_SHORT).show();
			}
		});
		btnAnasayfa.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent inte = new Intent(OlcumActivity.this, MainActivity.class);
				startActivity(inte);
			}
		});

	}

	public double hesaplama(double kalori, double ilac, double kanSekeri) {
		double sonuc = 0;
		sonuc = (kalori / 10) + ((kanSekeri - 100) / 30);
		sonuc = Math.ceil(sonuc);
		System.out.println("--------------->"+sonuc);
		return sonuc;

	}

	private boolean saveInsulin() {

		Insulin d = new Insulin();
		d.setDate(date);
		d.setInsulinmiktari(insulinMiktari);
		d.setKansekeri(kanSekeri);

		return iOperations.addInsulin(d);
	}

	public static String gunCevirici() {
		String genel = null;
		Date tarih = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("dd.MMM.yyyy kk-mm-ss");
		String temp = simple.format(tarih);
		String[] tempDizi = temp.split(Pattern.quote("."));
		for (int a = 1; a < Constant.aylar.length; a++) {
			if (tempDizi[1].equals(Constant.aylarIng[a])) {
				genel = tempDizi[0] + " " + Constant.aylar[a] + " "
						+ tempDizi[2];
				break;
			}
		}
		return genel;
	}
}