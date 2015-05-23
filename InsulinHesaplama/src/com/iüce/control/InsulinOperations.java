package com.iüce.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iuce.constants.Constant;
import com.iuce.contentproviderhelper.InsulinDBProvider;
import com.iüce.entity.Insulin;

public class InsulinOperations implements IInsulinOperations {

	SQLiteDatabase mydb;

	public InsulinOperations(Context context) {
		super();
		// db oluþturup mydb ye atýyoruz
		InsulinDBProvider dbProvider = new InsulinDBProvider(context);
		mydb = dbProvider.openDB();
	}

	@Override
	public boolean addInsulin(Insulin insulin) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();

		values.put(Constant.INSULIN_TARIH, insulin.getDate());
		values.put(Constant.INSULIN_KANSEKERI, insulin.getKansekeri());
		values.put(Constant.INSULIN_MIKTARI, insulin.getInsulinmiktari());

		long id = mydb.insert(Constant.INSULIN_TABLE, null, values);
		if (id > 0) {
			return true;
		}
		return false;

	}

	@Override
	public List<Insulin> listInsulin() {
		List<Insulin> insulins = new ArrayList<Insulin>();
		Cursor cursor = mydb.query(Constant.INSULIN_TABLE, new String[] {
				Constant.INSULIN_ID, Constant.INSULIN_KANSEKERI,
				Constant.INSULIN_MIKTARI, Constant.INSULIN_TARIH }, null, null,
				null, null, null);

		if (Constant.karar == 1) {
			while (cursor.moveToNext()) {

				String temp = cursor.getString(3);
				String[] tempDizi = temp.split(Pattern.quote(" "));

				if (Constant.secim == null) {
					Insulin insulin = new Insulin();
					insulin.setId(cursor.getInt(0));
					insulin.setKansekeri(cursor.getDouble(1));
					insulin.setInsulinmiktari(cursor.getDouble(2));
					insulin.setDate(cursor.getString(3));
					insulins.add(insulin);
				}
				else if (Constant.secim.equals(tempDizi[1])) {

					Insulin insulin = new Insulin();
					insulin.setId(cursor.getInt(0));
					insulin.setKansekeri(cursor.getDouble(1));
					insulin.setInsulinmiktari(cursor.getDouble(2));
					insulin.setDate(cursor.getString(3));
					insulins.add(insulin);
				}
			}
			Constant.karar = 0;
		} else {

			while (cursor.moveToNext()) {
				Insulin insulin = new Insulin();
				insulin.setId(cursor.getInt(0));
				insulin.setKansekeri(cursor.getDouble(1));
				insulin.setInsulinmiktari(cursor.getDouble(2));
				insulin.setDate(cursor.getString(3));
				insulins.add(insulin);
			}
		}
		cursor.close();
		return insulins;
	}

	@Override
	public List<Insulin> getInsulins(Date date) {
		return null;
	}
}
