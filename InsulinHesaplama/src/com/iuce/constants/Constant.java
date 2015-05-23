package com.iuce.constants;

public class Constant {
	public static long SLEEP_TIME = 500; // Thread Calisma Suresi
	public static String PACKAGE_NAME = "com.iuce.insulinHesaplama";
	public static String PROJECT_TAG = "DiaryProject";

	// genel bilgiler
	public static String DATABASE_NAME = "insulin.db";
	public static int DATABASE_VERSION = 1;
	public static String INSULIN_TABLE = "insulin";

	public static String DB_PATH = "content://" + PACKAGE_NAME + "/insulin";

	// insulin tablosu kolon isimleri
	public static String INSULIN_ID = "id";
	public static String INSULIN_KANSEKERI = "kanSekeri";
	public static String INSULIN_MIKTARI = "insuliMiktari";
	public static String INSULIN_TARIH = "insulinDate";

	// insulin tablo create
	public static String CREATE_INSULIN_TABLE = "create table " + INSULIN_TABLE
			+ "( " + INSULIN_ID + " integer primary key autoincrement, "
			+ INSULIN_KANSEKERI + " float, " + INSULIN_MIKTARI + " float, "
			+ INSULIN_TARIH + " text);";

	// Kahvaltiliklerin 100 gr üzerinden kalori degerleri...
	public static int kaloripeynir = 393;
	public static int kaloridereotu = 55;
	public static int kalorizeytin = 353;
	public static int kaloribal = 304;
	public static int kaloridomates = 17;
	public static int kaloritereyag = 717;
	public static int kalorisalatalik = 12;
	public static int kalorirecel = 285;
	public static int kaloriyumurta = 210;
	public static int kalorikasar = 413;
	public static int kaloriyogurt = 66;
	public static int kaloriekmek = 256;
	public static int kalorikaymak = 208;
	public static int kaloricay = 0;
	public static int kalorimarul = 16;
	public static int kalorimeyvesuyu = 45;
	public static int kalorisucuk = 452;
	public static int kalorisut = 45;

	// Ýlaçlarýn deðerleri...
	public static double lantus = 0;
	public static double humulin = 0;

	// Aylar...
	public static String aylar[] = { null, "Ocak", "Þubat", "Mart", "Nisan",
			"Mayýs", "Haziran", "Temmuz", "Aðustos", "Eylül", "Ekim", "Kasým",
			"Aralýk" };

	public static String aylarIng[] = { null, "Jan", "Feb", "Mar", "Apr",
			"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	// Raporlama için Sabit...
	public static int karar;
	public static String secim;

	// Yiyecekler
	public static String corbalar[] = { null, " Mercimek ", " Ezogelin ",
			" Yayla ", " Tarhana ", "Domates" };
	public static String pilavlar[] = { null, " Pirinç ", " Bulgur",
			" Makarna", " Keþkek ", "Mantý", "Salata" };
	public static String yemekler[] = { null, " Sarma ", " Dolma",
			" Karadeniz Pidesi", " Lahmacun ", "Pide", "Ýskender",
			"Urfa - Adana", "Çifköfte", "Tas Kebabý", "Hünkar Beðendi",
			"Kadýnbudu Köfte", "Ýzmir Köfte", "Fýrýn Tavuk(Sade)", "Mücver",
			"Ýmam Bayýldý", "Karnýyarýk", "Nohut", "Bezelye" };
	public static String meyveler[] = { null, " Elma ", " Kayýsý ", " Ýncir ",
			" Kiraz ", "Portakal", "Limon", "Mandalina", "Üzüm", "Erik",
			"Þeftali", "Armut", "Kavun", "Karpuz", "Kestane" };
	public static String tatlilar[] = { null, " Kuru Pasta ", " Kek ",
			" Piþmaniye ", " Yaþ Pasta ", "Dondurma", "Tulumba", "Þekerpare",
			"Revani", "Sütlaç", "Aþure", "Baklava", "Kadayýf", "Muhallebi",
			"Kazandibi" };
	public static String icecekler[] = { null, " Salep ", " Boza", " Kola",
			" Gazoz ", "Ayran", "Bira", "Raký", "Þarap" };

	// Kaloriler
	public static double corbalarKalori[] = { 22.5, 24.5, 19.9, 13.4, 14.3 };
	public static double pilavlarKalori[] = { 48, 40, 39.1, 40, 51, 7 };
	public static double yemeklerKalori[] = { 35, 34.6, 65, 40, 60, 80, 95, 10,
			18, 10, 25, 12.4, 0, 10, 7, 7, 16.4, 15 };
	public static double meyvelarKalori[] = { 14.5, 15.3, 16.3, 15.3, 15.2,
			8.2, 17.4, 17.3, 14, 14.5, 15.3, 15.4, 12.8, 53 };
	public static double tatlilarKalori[] = { 10, 30, 83.2, 75, 9, 50, 25, 75,
			45, 50, 17, 75, 29.7, 40 };
	public static double iceceklerKalori[] = { 21, 57.5, 36.9, 40.3, 7.5, 3.8,
			0.5, 4.2 };

}