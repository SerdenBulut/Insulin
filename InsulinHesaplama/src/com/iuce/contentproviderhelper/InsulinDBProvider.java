package com.iuce.contentproviderhelper;

import com.iuce.constants.Constant;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class InsulinDBProvider {

	InsulinDBHelper dbHelper;

	public InsulinDBProvider(Context context) {
		super();
		dbHelper = new InsulinDBHelper(context, Constant.DATABASE_NAME, null,
				Constant.DATABASE_VERSION);
	}

	public SQLiteDatabase openDB() {
		return dbHelper.getWritableDatabase();
	}

}
