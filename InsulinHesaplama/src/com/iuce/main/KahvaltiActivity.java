package com.iuce.main;

import java.util.HashMap;
import java.util.Map;
import com.example.insulinhesaplama.R;
import com.iuce.constants.Constant;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class KahvaltiActivity extends Activity {

	private CheckBox peynir;
	private CheckBox dereotu;
	private CheckBox zeytin;
	private CheckBox bal;
	private CheckBox domates;
	private CheckBox tereyag;
	private CheckBox salatalik;
	private CheckBox recel;
	private CheckBox yumurta;
	private CheckBox kasar;
	private CheckBox yogurt;
	private CheckBox ekmek;
	private CheckBox kaymak;
	private CheckBox cay;
	private CheckBox marul;
	private CheckBox meyvesuyu;
	private CheckBox sucuk;
	private CheckBox sut;
	private Button btnBitti;
	private Map<Integer, CheckBox> chkNesne;
	private Map<Integer, Integer> kaloriler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kahvalti);

		// Gerekli nesneler olusturuldu....
		chkNesne = new HashMap<Integer, CheckBox>();
		kaloriler = new HashMap<Integer, Integer>();

		peynir = (CheckBox) findViewById(R.id.chkPeynir);
		dereotu = (CheckBox) findViewById(R.id.chkDereotu);
		zeytin = (CheckBox) findViewById(R.id.chkZeytin);
		bal = (CheckBox) findViewById(R.id.chkBal);
		domates = (CheckBox) findViewById(R.id.chkDomates);
		tereyag = (CheckBox) findViewById(R.id.chkTereyag);
		salatalik = (CheckBox) findViewById(R.id.chkSalatalik);
		recel = (CheckBox) findViewById(R.id.chkRecel);
		yumurta = (CheckBox) findViewById(R.id.chkYumurta);
		kasar = (CheckBox) findViewById(R.id.chkKasar);
		yogurt = (CheckBox) findViewById(R.id.chkYogurt);
		ekmek = (CheckBox) findViewById(R.id.chkEkmek);
		kaymak = (CheckBox) findViewById(R.id.chkKaymak);
		cay = (CheckBox) findViewById(R.id.chkCay);
		marul = (CheckBox) findViewById(R.id.chkMarul);
		meyvesuyu = (CheckBox) findViewById(R.id.chkMeyvesuyu);
		sucuk = (CheckBox) findViewById(R.id.chkSucuk);
		sut = (CheckBox) findViewById(R.id.chkSut);

		// Hashmap e nesneler için eklemeler yapiliyor...
		chkNesne.put(1, peynir);
		chkNesne.put(2, dereotu);
		chkNesne.put(3, zeytin);
		chkNesne.put(4, bal);
		chkNesne.put(5, domates);
		chkNesne.put(6, tereyag);
		chkNesne.put(7, salatalik);
		chkNesne.put(8, recel);
		chkNesne.put(9, yumurta);
		chkNesne.put(10, kasar);
		chkNesne.put(11, yogurt);
		chkNesne.put(12, ekmek);
		chkNesne.put(13, kaymak);
		chkNesne.put(14, cay);
		chkNesne.put(15, marul);
		chkNesne.put(16, meyvesuyu);
		chkNesne.put(17, sucuk);
		chkNesne.put(18, sut);

		// Hashmap e kaloriler için eklemeler yapiliyor...
		kaloriler.put(1, Constant.kaloripeynir);
		kaloriler.put(2, Constant.kaloridereotu);
		kaloriler.put(3, Constant.kalorizeytin);
		kaloriler.put(4, Constant.kaloribal);
		kaloriler.put(5, Constant.kaloridomates);
		kaloriler.put(6, Constant.kaloritereyag);
		kaloriler.put(7, Constant.kalorisalatalik);
		kaloriler.put(8, Constant.kalorirecel);
		kaloriler.put(9, Constant.kaloriyumurta);
		kaloriler.put(10, Constant.kalorikasar);
		kaloriler.put(11, Constant.kaloriyogurt);
		kaloriler.put(12, Constant.kaloriekmek);
		kaloriler.put(13, Constant.kalorikaymak);
		kaloriler.put(14, Constant.kaloricay);
		kaloriler.put(15, Constant.kalorimarul);
		kaloriler.put(16, Constant.kalorimeyvesuyu);
		kaloriler.put(17, Constant.kalorisucuk);
		kaloriler.put(18, Constant.kalorisut);

		btnBitti = (Button) findViewById(R.id.btnTamam);
		btnBitti.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(KahvaltiActivity.this,
						OlcumActivity.class);
				intent.putExtra("toplamKalori",
						String.valueOf(toplamKalori(chkNesne, kaloriler)));
				startActivity(intent);
			}
		});

	}

	public static int toplamKalori(Map<Integer, CheckBox> tempNesne,
			Map<Integer, Integer> tempKalori) {

		int toplamKalori = 0;

		for (int i = 1; i <= 18; i++) {
			if (tempNesne.get(i).isChecked()) {
				toplamKalori = toplamKalori + tempKalori.get(i);
			}
		}
		return toplamKalori;

	}
}
