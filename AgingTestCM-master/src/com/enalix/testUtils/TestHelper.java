package com.enalix.testUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestHelper {
	public static final String TEST_PATH = "/sdcard/uitest";
	public static final String APP_PATH = TEST_PATH + "/app";
	public static final String EMAIL_PATH = TEST_PATH + "/email.list";
	public static final String OPEN_URL_PATH = TEST_PATH + "/open_url.list";
	public static final String DOWN_URL_PATH = TEST_PATH + "/download_url.list";
	public static final String CONTACT_PATH = TEST_PATH + "/contact.list";
	public static final String ADDRESS_PATH = TEST_PATH + "/address.list";
	public static final String MESSAGE_PATH = TEST_PATH + "/message.list";
	public static final String EMAIL_CONTENT = TEST_PATH + "/EmailContent.list";
	public static long TIMEOUT = 20000;
	public static String LOG_TAG = "CyanogenMod-nexus5";
	public static String SCREENSHOTS = "uiAutoTest";
	
	public static String TAG_IMSENT = "IMsent";
	public static String TAG_MAIL_SENT = "EmailSent";
	public static String TAG_DOWN_MUSIC_WIFI = "MusicDownWifi";
	public static String TAG_DOWN_APP_WIFI = "AppDownWifi";
	public static String TAG_DOWN_PIC_WIFI = "PicDownWifi";
	public static String TAG_DOWN_ATT_WIFI = "AttDownWifi";
	public static String TAG_DOWN_MUSIC_3G = "MusicDown3G";
	public static String TAG_DOWN_APP_3G = "AppDown3G";
	public static String TAG_DOWN_PIC_3G = "PicDown3G";
	public static String TAG_DOWN_ATT_3G = "AttDown3G";
	public static String TAG_PIC_FLASH = "PicFlash";
	public static String TAG_PIC_NOFLASH = "PicNoFlash";
	public static String TAG_CALL = "Call";
	public static String TAG_MUSIC_PLAY = "MusicPlay";
	
	public static String TAG_SETTINGS = "Settings";
	
	public static String EMERGENCY_CALL_NUMBER = "911";
	public static final String CHINAMOBILE_CALL_NUMBER = "10086";
	public static final String CHINAUNICOM_CALL_NUMBER = "10010";
	
	public static final int  LONG_CALL_TIME=4*60;
	
	private static final String[] SUB_NUMBER = {"134", "156", "189", "135", "139", "131" };
	private static final int NUMBER_LENGTH = 11;
	
	private static final String[] EMAIL_END = {"com", "net", "cn", "org" };
	
	//for generate rand contact's address
    private static final String[] CITY_LIST = {
   	"Shanghai", "Beijing", "Taibei", "Guangzhou", "Shenzhen", "Tianjin", "Nanjing", "Hangzhou", "Chengdu",
   	"Wuhan", "Chongqing", "Shenyang", "Xi'an", "Aomen", "Gaoxiong", "Dalian", "QingDao", "Suzhou", "Ningbo",
   	"Wuxi", "Changsha", "Zhengzhou", "Xiamen", "Haerbin", "Jinan", "Changchun", "Fuzhou", "Hefei", "Foshan", 
   	"Shijiazhuang", "Nanning", "Taiyuan", "Wulumuqi", "Kunming", "Nanchang", "Lanzhou", "Gunyang", "Taizhong",
   	"Dongguan", "Shaoxing", "Changzhou", "Yantai", "Quanzhou", "HongKong", "Wenzhou", "Tangshan", "Nantong"};
   
	private static final String[] OPERATOR = {"+", "−", "×", "÷" };
	//private static final String[] SMS_ACTION = {"send", "recv", "del"};
	//private static final String[] EMALI_ACTION = {"send", "recv", "del"};
	//do something , then sleep
	
	public  static final int OPERATION_DELAY = 1000;
	public  static final int HOUR_DELAY = 60000;
	public  static final int DAY_DELAY = 600000;

	public static  String[] GAMES_LIST = {"2048"};
	public static final String EMAIL_SUBJECT = "uitest";
	
	
	/* -----------------------------------------------------------------For contact*/
	public static String generateName() {
        Random ran = new Random();
        String name = "" + (char)(65+ran.nextInt(26));	//capitalize
        return name+generateString("lower", 5, 9);	//name's length: 6-10
    }
	public  static String generatePhone() {
		Random rand = new Random();
		String telnum = SUB_NUMBER[rand.nextInt(SUB_NUMBER.length)];
		return telnum + generateString("digit", NUMBER_LENGTH-3);
	}
	public static String[] generatePhone(int num) {
		String[] phone = new String[num];
		for (int i=0; i<num; i++)
			phone[i] = generatePhone();
		return phone;
	}
	public static String generateEmail() {
		Random rand = new Random();
		String account = generateString("alnum", 7, 15);		//account's length: 7-15
		String company = generateString("lower", 2, 7);		//company's length: 2-7
		return account+"@"+company+"."+EMAIL_END[rand.nextInt(EMAIL_END.length)];
	}
	public static String[] generateEmail(int num) {
		String[] email = new String[num];
		for (int i=0; i<num; i++)
			email[i] = generateEmail();
		return email;
	}
	public static String generateAddress() {
		Random rand = new Random();
		return CITY_LIST[rand.nextInt(CITY_LIST.length)];
	}
	
	public static Contact generateContact() {
		return new Contact(generateName(), generatePhone(), generateEmail(), generateAddress()); 
	}
	
	public static Contact[] generateContact(int num) {
		Contact[] cont = new Contact[num];
		for (int i=0; i<num; i++)
			cont[i] = generateContact();
		return cont;
	}
	/* ----------------------------------------------------------------End for contact*/
	
	
	/* ----------------------------------------------------------------Generate Utils*/
	// generate expr, digit, alpha, lower, upper, alnum, graph string
	public static String generateString(String type, int lenMin, int lenMax) {
		Random rand = new Random();
		String str="";
		int num = lenMin;
		if (lenMax != lenMin)
			num += rand.nextInt(lenMax-lenMin);
		if (type.equalsIgnoreCase("expr")) {	//a cal expr's first nubmer can't be 0
			str += rand.nextInt(9) + 1;
			if (num>1)
				if (rand.nextInt(2) == 1) {
					int num1 = rand.nextInt(num-1);
					for (int i=0; i<num1; i++)
						str += rand.nextInt(10);
					str += ".";
					for (int i=0; i<num-1-num1-1; i++)
						str += rand.nextInt(10);
					str += rand.nextInt(9) + 1;		//kill 23.450, the last number will not be 0, when the number has "."
				} else {
					for (int i=1; i<num; i++)
						str += rand.nextInt(10);
				}
		}
		if (type.equalsIgnoreCase("digit"))		//0-9
			for (int i=0; i<num; i++)
				str += rand.nextInt(10);
		if (type.equalsIgnoreCase("alpha"))	//a-zA-Z
			for (int i=0; i<num; i++) {
				int choice = rand.nextInt() % 2 == 0? 65:97;
				str += (char)(choice + rand.nextInt(26));						
			}
		if (type.equalsIgnoreCase("lower"))	//a-z
			for (int i=0; i<num; i++)
				str += (char)(97+rand.nextInt(26));
		if (type.equalsIgnoreCase("upper"))	//A-Z
			for (int i=0; i<num; i++)
				str += (char)(65+rand.nextInt(26));
		if (type.equalsIgnoreCase("alnum"))	//0-9a-zA-Z
			for (int i=0; i<num; i++) {
				int choice = rand.nextInt(3);
				if (choice == 0)
					str += (char)(65+rand.nextInt(26));
				else if (choice == 1)
					str += (char)(97+rand.nextInt(26));
				else
					str += rand.nextInt(10);
			}
		if (type.equalsIgnoreCase("graph"))	//32-127, all visiable char
			for (int i=0; i<num; i++)
				str += (char)(rand.nextInt(95)+32);
		return str;
	}
	public static String generateString(String type, int len) {
		return generateString(type, len, len);
	}
	// generate "num" number of int, no repeat, within "within" 
	public static int[] generateNoReptNumber(int num, int within) {
		int[] numArray = new int[num];
		if (num>within)
			num = within;
		boolean[] bool = new boolean[within];
		Random rand = new Random();
		int randnum;
		for (int i=0; i<num; i++) {
			do {
				randnum = rand.nextInt(within);
			} while(bool[randnum]);
			bool[randnum] = true;
			numArray[i] = randnum;
		}
		return numArray;
	}
	//num level path, not num path
	public static String generatePath(int num) {
		String path = "/sdcard";
		for (int i=0; i<num; i++)
			path += "/" + generateString("lower", 5, 10);
		return path;
	}
	public static String[] generateFile(int num, String suffix) {
		String[] fileList = new String[num];
		for (int i=0; i<num; i++)
			fileList[i] = generateString("alnum", 3, 10) + "suffix";
		return fileList;
	}
	/* -----------------------------------------------------------------End generate utils*/
	
	
	/* -----------------------------------------------------------------Get Utils*/
	//################get all lines of a file
	public static String[] getFileLines(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		ArrayList<String> fileline = new ArrayList<>();
		while ((line = in.readLine()) != null) {
			if (!line.startsWith("#") && !line.isEmpty())
				fileline.add(line);
		}
		in.close();
		fileline.trimToSize();
		String[] cont = new String[fileline.size()];
		fileline.toArray(cont);
		return cont;
	}
	//###############get num lines of a file
	public static String[] getRandLines(String fileName, int num) throws IOException {
		String[] fileLines = getFileLines(fileName);
		return getSubStrArray(fileLines, num);
	}
	//###############get all files with the suffix of the directory
	public static String[] getDirFiles(String dirPath, final String suffix) {
		File dir = new File(dirPath);
		FilenameFilter ft = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(suffix))
					return true;
				else
					return false;
			}
		};
		return dir.list(ft);
	}
	public static String[] getRandFiles(String dirPath, final String suffix, int num) {
		String[] fileList = getDirFiles(dirPath, suffix);
		return getSubStrArray(fileList, num);
	}
	//###############get all files of the directory , include file, directory...
	public static String[] getDirFiles(String dirPath) {
		return new File(dirPath).list();
	}
	//TODO:not a effective method, I have to get all String[] , then get sub String[]
	public static String[] getSubStrArray(String[] fullStr, int num) {
		if (num>= fullStr.length)
			return fullStr.clone();
		else {
			String[] subStr = new String[num];
			int[] select = generateNoReptNumber(num, fullStr.length);
			for (int i=0; i<num; i++)
				subStr[i] = fullStr[select[i]];
			return subStr;
		}
	}
	//TODO:not work, may use the template code.
	public static Object[] getSubArray(Object[] fullObj, int num) {
		if (num>= fullObj.length)
			return fullObj.clone();
		else {
			Object[] subObj = new Object[num];
			int[] select = generateNoReptNumber(num, fullObj.length);
			for (int i=0; i<num; i++)
				subObj[i] = fullObj[select[i]];
			return subObj;
		}
	}
	/* ----------------------------------------------------------------End get utils*/
	
	
	/* ----------------------------------------------------------------For calculator*/
	public static String generateCalExpr() {
		Random rand = new Random();
		int num = rand.nextInt(3)+2;	//a cal expr has 2 number at least, 5 number at most.
		String expr = generateString("expr", 1, 7);	//a cal number's length between 1~7
		for (int i=0; i<num; i++) {
			expr += OPERATOR[rand.nextInt(OPERATOR.length)] + generateString("expr", 1, 7);
		}
		return expr;		
	}
	public static String[] generateCalExpr(int num) {
		String[] str = new String[num];
		for (int i=0; i<num; i++)
			str[i] = generateCalExpr();
		return str;
	}
	/* ----------------------------------------------------------------End for calculator*/
	
	
	/* ----------------------------------------------------------------For browser*/
	public static String[] getUrlDown(int num) throws IOException {
		return getRandLines(DOWN_URL_PATH, num);
	}
	public static String[] getUrlOpen(int num) throws IOException {
		return getRandLines(OPEN_URL_PATH, num);
	}
	/* ----------------------------------------------------------------End for browser*/
	
	
	/* ----------------------------------------------------------------For Messaging*/
	public static String[] getMessage(int num) throws IOException {
		return getRandLines(MESSAGE_PATH, num);
	}
	/* ----------------------------------------------------------------End for messaging*/
	public static String[] getEmailContent() throws IOException{
		String[] EmailContent= getFileLines(EMAIL_CONTENT);
		return EmailContent;
	}
	
	
	/* ----------------------------------------------------------------For Mail Master*/
	public static String[] getEmail() throws IOException {
		return getFileLines(EMAIL_PATH);
	}
	public static String[] getEmail(int num) throws IOException {
		return getRandLines(EMAIL_PATH, num);
	}
	/* ----------------------------------------------------------------End for Mail Master*/
	
	
	/* ----------------------------------------------------------------For App Manager*/
	public static String[] getAppList(int num) {
		return getSubStrArray(getDirFiles(APP_PATH, "apk"), num);
	}
	public static String[] getAppList() {
		return getDirFiles(APP_PATH, "apk");
	}
	/* ---------------------------------------------------------------End for app manager*/
	
	
	/* ----------------------------------------------------------------For behavior*/
	//############for gamer habit
	public static Behavior getGamerBehavior() {
		Behavior bh = new Behavior();
		bh.setAppNum(3);	//install/uninstall app number
		bh.setContactNum(3);	//add/del contact number
		bh.setGameNum(1);	//the number of play game
		bh.setFileNum(5);
		
		bh.setRecvEmailNum(5);
		bh.setRecvSmsNum(5);
		bh.setSendEmailNum(5);
		bh.setSendSmsNum(5);
		bh.setUrlDownNum(5);
		bh.setUrlOpenNum(5);
		return bh;
	}
	
	//############for business' man habit
	public static Behavior getBusinessWorkdayBehavior() {
		//TODO:
		return new Behavior();
	}
	public static Behavior[] getBusinessWorkdayBehavior(int num) {
		Behavior[] behavior = new Behavior[num];
		for (int i=0; i<num; i++)
			behavior[i] = getBusinessWorkdayBehavior();
		return behavior;
	}
	public static Behavior getBusinessWeekdayBehavior() {
		return new Behavior();
	}
	public static Behavior[] getBusinessCustomer() {
		Behavior[] cust = new Behavior[7];
		for (int i=0; i<5; i++)
			cust[i] = getBusinessWorkdayBehavior();
		for (int i=5; i<7; i++)
			cust[i] = getBusinessWeekdayBehavior();
		return cust;
	}
	
	//############for student's habit
	public static Behavior getStudentWorkdayBehavior() {
		//TODO:
		return new Behavior();
	}
	public static Behavior[] getStudentWorkdayBehavior(int num) {
		Behavior[] behavior = new Behavior[num];
		for (int i=0; i<num; i++)
			behavior[i] = getStudentWorkdayBehavior();
		return behavior;
	}
	public static Behavior getStudentWeekdayBehavior() {
		return new Behavior();
	}
	public static Behavior[] getStudentCustomer() {
		Behavior[] cust = new Behavior[7];
		for (int i=0; i<5; i++)
			cust[i] = getStudentWorkdayBehavior();
		for (int i=5; i<7; i++)
			cust[i] = getStudentWeekdayBehavior();
		return cust;
	}
	
	//############for general customer's habit
	public static Behavior getWeekdayBehavior() {
		return new Behavior();
	}
	public static Behavior getWorkdayBehavior() {
		//TODO:
		return new Behavior();
	}
	public static Behavior[] getWorkdayBehavior(int num) {
		Behavior[] cust = new Behavior[num];
		for (int i=0; i<num; i++)
			cust[i] = getWorkdayBehavior();
		return cust;
	}
	public static Behavior[] getCustomer() {
		Behavior[] cust = new Behavior[7];
		for (int i=0; i<5; i++)
			cust[i] = getWorkdayBehavior();
		for (int i=5; i<7; i++)
			cust[i] = getWeekdayBehavior();
		return cust;
	}
	/* ---------------------------------------------------------------End for behavior*/

	
	//main method for test
	public static void main(String[] args) throws IOException {
		String[] str = getDirFiles("/home/enali");
		for (int i=0; i<str.length; i++)
			System.out.println(str[i]);
	}
}
