package com.iuce.adapter;

import java.util.List;
import com.example.insulinhesaplama.R;
import com.iüce.entity.Insulin;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class InsulinAdapter extends ArrayAdapter<Insulin> {

	public InsulinAdapter(Context context, int resource, List<Insulin> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Insulin insulin = getItem(position);
		View view = null;

		if (convertView == null) {
			LayoutInflater lf = LayoutInflater.from(getContext());
			view = lf.inflate(R.layout.kayit_list_row, null);
		} else {
			view = convertView;
		}

		TextView txtId = (TextView) view.findViewById(R.id.txtId);
		TextView txtKansekeri = (TextView) view.findViewById(R.id.txtKansekeri);
		TextView txtInsulinmiktari = (TextView) view
				.findViewById(R.id.txtInsulinmiktari);
		TextView txtTarih = (TextView) view.findViewById(R.id.txtTarih);

		txtId.setText(String.valueOf(insulin.getId()));
		txtKansekeri.setText(String.valueOf(insulin.getKansekeri()));
		txtInsulinmiktari.setText(String.valueOf(insulin.getInsulinmiktari()));
		txtTarih.setText(insulin.getDate());

		return view;

	}

}
