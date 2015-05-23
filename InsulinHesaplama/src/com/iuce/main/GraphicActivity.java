package com.iuce.main;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;
import com.example.insulinhesaplama.R;
import com.iuce.adapter.InsulinAdapter;
import com.iuce.constants.Constant;
import com.iüce.control.IInsulinOperations;
import com.iüce.control.InsulinOperations;
import com.iüce.entity.Insulin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.FloatMath;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

public class GraphicActivity extends ActionBarActivity {

	// region members
	private XYPlot plot;

	private List<Date> dates;
	private List<Double> insulinKanSekeriList;
	private List<Double> insulinMiktariList;
	private IInsulinOperations insulinOperations;

	private int selectedMonth = 5;
	private int selectedYear = 2015;

	private DatePicker datePicker;
	private Calendar calendar;
	private int year, month, day;

	private PointF minXY;
	private PointF maxXY;

	private double domainStartValue = 0;
	private double domainStopValue;

	private double rangeStartValue;
	private double rangeStopValue = 0;

	// endregion

	// region onCreate
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graphic);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
				WindowManager.LayoutParams.FLAG_SECURE);

		// bulundugumuz ay ve yýla ait bilgilerin grafigini getirsin ilk
		// açýlýþta
		Calendar calend = Calendar.getInstance();
		selectedMonth = calend.get(Calendar.MONTH) + 1;
		selectedYear = calend.get(Calendar.YEAR);

		plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

		insulinOperations = new InsulinOperations(getApplicationContext());
		dates = new ArrayList<Date>();
		insulinKanSekeriList = new ArrayList<Double>();
		insulinMiktariList = new ArrayList<Double>();
		plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.TRANSPARENT);
		setListFromDatabase();
		drawGraphic();

	}

	// endregion

	// region methods
	private void drawGraphic() {
		// Xy serissi olarak insulinKansekerini ekliyoruz
		XYSeries series1 = new SimpleXYSeries(insulinKanSekeriList,
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "KanSekeri");

		// Xy serissi olarak insulinMiktarini ekliyoruz
		XYSeries series2 = new SimpleXYSeries(insulinMiktariList,
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "InsulinMiktari");

		// format oluþturalým
		LineAndPointFormatter series1Format = new LineAndPointFormatter();
		series1Format.setPointLabelFormatter(new PointLabelFormatter());
		series1Format.configure(getApplicationContext(),
				R.xml.line_point_formatter_with_plf1);

		// oluþturdugumuz serileri formatlarýyla birlikte grafiðe ekleyelim
		plot.addSeries(series1, series1Format);

		LineAndPointFormatter series2Format = new LineAndPointFormatter();
		series2Format.setPointLabelFormatter(new PointLabelFormatter());
		series2Format.configure(getApplicationContext(),
				R.xml.line_point_formatter_with_plf2);
		plot.addSeries(series2, series2Format);

		// X eksenin 1 er 1 er artýrýyoruz
		plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
		// kayit sayýmýz kadar X ekseni olsun diyoruz
		domainStopValue = insulinKanSekeriList.size();

		List<Double> tempInsulinKansekeri = insulinKanSekeriList;
		List<Double> tempInsulinMiktari = insulinMiktariList;
		Collections.sort(tempInsulinKansekeri);
		Collections.sort(insulinMiktariList);
		if (tempInsulinKansekeri.get(tempInsulinKansekeri.size() - 1) < tempInsulinMiktari
				.get(tempInsulinMiktari.size() - 1)) {
			rangeStopValue = tempInsulinMiktari
					.get(insulinMiktariList.size() - 1);
		} else
			rangeStopValue = tempInsulinKansekeri
					.get(insulinMiktariList.size() - 1);
		plot.setRangeBoundaries(rangeStartValue, rangeStopValue,
				BoundaryMode.FIXED);
		plot.setDomainBoundaries(domainStartValue, domainStopValue,
				BoundaryMode.FIXED);

		plot.getGraphWidget().setDomainLabelOrientation(-45);
		minXY = new PointF(plot.getCalculatedMinX().floatValue(), plot
				.getCalculatedMinY().floatValue());
		maxXY = new PointF(plot.getCalculatedMaxX().floatValue(), plot
				.getCalculatedMaxY().floatValue());
	}

	private void reDrawPlot() {
		plot.clear();
		drawGraphic();
		plot.redraw();
	}

	private void setListFromDatabase() {
		insulinKanSekeriList = new ArrayList<Double>();
		insulinMiktariList = new ArrayList<Double>();
		List<Insulin> insulinList = insulinOperations.listInsulin();
		for (Insulin insulin : insulinList) {
			DateFormat simple = new SimpleDateFormat("dd.MMM.yyyy kk-mm-ss");
			Calendar cal = Calendar.getInstance();
			Date d = null;
			try {
				cal.setTime(simple.parse(insulin.getDate()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);
			if (month == selectedMonth && year == selectedYear) {
				insulinKanSekeriList.add(insulin.getKansekeri());
				insulinMiktariList.add(insulin.getInsulinmiktari());
			}
		}
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

			showDialog(999);
			Constant.karar = 1;
		}
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@SuppressWarnings("deprecation")
	public void setDate(View view) {
		showDialog(999);
		Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar dueDateCalendar = Calendar.getInstance();
		DatePickerDialog dlg = new DatePickerDialog(this, myDateListener,
				dueDateCalendar.get(Calendar.YEAR),
				dueDateCalendar.get(Calendar.MONTH),
				dueDateCalendar.get(Calendar.DAY_OF_MONTH)) {
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				int day = getContext().getResources().getIdentifier(
						"android:id/day", null, null);
				if (day != 0) {
					View dayPicker = findViewById(day);
					if (dayPicker != null) {
						dayPicker.setVisibility(View.GONE);
					}
				}
			}
		};
		return dlg;
	}

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			// arg1 = year
			// arg2 = month
			// arg3 = day
			selectedYear = arg1;
			selectedMonth = arg2 + 1;
			Toast.makeText(getApplicationContext(),
					selectedYear + " " + selectedMonth, Toast.LENGTH_LONG)
					.show();
			setListFromDatabase();
			reDrawPlot();
		}
	};
	// endregion

	// Definition of the touch states
	static final int NONE = 0;
	static final int ONE_FINGER_DRAG = 1;
	static final int TWO_FINGERS_DRAG = 2;
	int mode = NONE;

	PointF firstFinger;
	float distBetweenFingers;


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: // Start gesture
			System.out
					.println("<-------------dokunmaya baslandi Action_down------------------------------>");
			firstFinger = new PointF(event.getX(), event.getY());
			mode = ONE_FINGER_DRAG;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			break;

		case MotionEvent.ACTION_POINTER_DOWN: // second finger
			System.out
					.println("<-------------second finger dokunmaya baslandi Action_pointer_down------------------------------>");
			distBetweenFingers = distance(event);
			System.out.println("distance-------->" + distBetweenFingers);
			// the distance check is done to avoid false alarms
			mode = TWO_FINGERS_DRAG;

			break;
		case MotionEvent.ACTION_MOVE:
			if (mode == ONE_FINGER_DRAG) {
				PointF lastPoint = firstFinger;
				firstFinger = new PointF(event.getX(), event.getY());
				if (leftRightDecission(lastPoint, firstFinger) == 1) {
					domainStartValue++;
					domainStopValue++;
					plotRedrawToDomain();
				} else {
					if (domainStartValue != 0) {
						domainStartValue--;
						domainStopValue--;
						plotRedrawToDomain();
					}
				}
				Log.d("----------->", "Action_MOVE_one");
			} else if (mode == TWO_FINGERS_DRAG) {
				System.out
						.println("<-------------two fingers move dokunmaya baslandi Two_fingers_drag------------------->");
				float oldDist = distBetweenFingers;
				distBetweenFingers = distance(event);
				System.out.println("newdistance------->" + distance(event));
				if (Math.abs(oldDist) < Math.abs(distBetweenFingers)) {
					domainZoomIncrease();
				} else {
					domainZoomDecrease();
				}
				Log.d("-------------", "Action_MOVE_Two");
			}
			break;
		}
		return true;
	}

	private int leftRightDecission(PointF oldPoint, PointF newPoint) {

		float difference = newPoint.x - oldPoint.x;
		if (difference > 0) {
			return 0;
		} else
			return 1;
	}

	public void domainZoomIncrease() {
		System.out.println("increaseing->>>>>>>>>>>>>>>");
		double fark = domainStopValue - domainStartValue;
		if (fark >= 3) {
			domainStopValue -= 1;
			plotRedrawToDomain();
		}
	}

	public void domainZoomDecrease() {
		System.out.println("decreasing->>>>>>>>>>>>>>>");
		double fark = domainStopValue - domainStartValue;
		domainStopValue += 1;
		plotRedrawToDomain();

	}

	public void plotRedrawToDomain() {

		plot.setDomainBoundaries(domainStartValue, domainStopValue,
				BoundaryMode.FIXED);
		plot.redraw();
	}

	private float distance(MotionEvent event) {
		try {
			float x = event.getX(0) - event.getX(1);
			return x;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}
}
