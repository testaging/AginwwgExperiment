package com.enalix.testUtils;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.R.integer;
import android.os.RemoteException;
import android.util.Log;

import com.android.uiautomator.core.*;

public class MidUtils extends LowUtils {
	/**
	 * get wifi state, open/close wifi
	 * @return "ON" or "OFF"
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
public void PreSet() throws RemoteException, UiObjectNotFoundException{
	System.out.println("begine to set settings");
	OpenApp("Settings");
	SetDisplay();
	SetDateTime();
	SetDeveloper();
	ExitApp("Settings");
}

public void SetDateTime() throws UiObjectNotFoundException{
	getscrObjByClsTxtCont("android.widget.TextView", "Date & time").clickAndWaitForNewWindow();
	if (!getObjByTxt("GMT+08:00, China Standard Time").exists()) {
		getObjByTxt("Select time zone").clickAndWaitForNewWindow();
	getObjByTxt("Beijing").clickAndWaitForNewWindow();
	}
	UiObject uiCbox = getObjByClsIns("android.widget.CheckBox", 2);
	if (uiCbox.exists() && !uiCbox.isChecked())
		uiCbox.click();
	pressBack();
}

public void SetDisplay() throws UiObjectNotFoundException{
	getscrObjByClsTxtCont("android.widget.TextView", "Display & lights").clickAndWaitForNewWindow();
	if (!getObjByTxt("After 30 minutes of inactivity").exists()) {
		getObjByTxt("Sleep").clickAndWaitForNewWindow();
		getObjByTxt("30 minutes").clickAndWaitForNewWindow();	
	}
	pressBack();
	
}

public void SetDeveloper() throws UiObjectNotFoundException{
	getscrObjByClsTxtCont("android.widget.TextView", "Developer options").clickAndWaitForNewWindow();
	UiObject uiCbox = getObjByClsIns("android.widget.CheckBox", 1);
	if (uiCbox.exists() && !uiCbox.isChecked())
		uiCbox.click();
	pressBack();
}



	//choose contact
	public void ChoCont(int EmailAccNum) throws UiObjectNotFoundException,RemoteException{
		getObjById("com.netease.mobimail:id/mailcompose_btn_add").clickAndWaitForNewWindow();
		getObjByTxt("手机通讯录").clickAndWaitForNewWindow();
		int randnum = GetRandNum(EmailAccNum);
		//如果邮件收件人的数量发生变化，则对于EmailRecArr中的初始值数量应发生变化
		String[] EmailRecArr = new String[]{"A","B","C","D"};
		String EmailRec = EmailRecArr[randnum]+"SAtest"+(randnum+1);
		LogHelper.LogHelp(EmailRec);
//		System.out.println(EmailRec);
		getChildByClsTxt("android.widget.TextView",EmailRec).click();
		getObjByTxt("确定").clickAndWaitForNewWindow();
	}

	//set email content
	public void SetEmailCon(String[] EmailContent) throws UiObjectNotFoundException {

		String SendEmailContent = EmailContent[0];
		Random rand = new Random();
		int randnum2=rand.nextInt(EmailContent.length);
		for (int i = 0; i < randnum2; i++) {
			SendEmailContent = SendEmailContent + EmailContent[i+1];
		}
		System.out.println(SendEmailContent);
		getEditById("com.netease.mobimail:id/mailcompose_content").setText(SendEmailContent);
	}

	public void EmailWithPic() throws UiObjectNotFoundException{
		getObjByDesc("添加附件").clickAndWaitForNewWindow();
		getObjById("com.netease.mobimail:id/btn_attach_image").clickAndWaitForNewWindow();
		clickPoint(280, 458);
		sleep(2000);
		clickPoint(200, 385);
		sleep(500);
		clickPoint(550, 385);
		sleep(500);
		clickPoint(890, 385);
		sleep(500);
		clickPoint(200, 680);
		sleep(500);
		clickPoint(550, 680);
		sleep(500);
		clickPoint(890, 680);
		sleep(500);
		clickPoint(200, 1000);
		sleep(500);
		clickPoint(550, 1000);
		sleep(500);
		clickPoint(890, 1000);
		sleep(500);
		clickPoint(200, 1300);
		sleep(500);
		getObjByTxtContains("完成").clickAndWaitForNewWindow();		
	}

	public void EmailWithSound() throws UiObjectNotFoundException{
		getObjByDesc("添加附件").clickAndWaitForNewWindow();
		getObjById("com.netease.mobimail:id/btn_attach_file").clickAndWaitForNewWindow();
		while (!getObjByTxt("sdcard0").exists()) {
			getObjByTxt("返回上一级").clickAndWaitForNewWindow();			
		}
		getChildByClsTxt("android.widget.TextView", "uitest").clickAndWaitForNewWindow();
		getChildByClsTxt("android.widget.TextView", "Music").clickAndWaitForNewWindow();
		UiObject obj = getObjByClsId("android.widget.ListView", "com.netease.mobimail:id/file_explorer_listview");
		int ChildCount = obj.getChildCount();
		int randnum = GetRandNum(ChildCount-1)+1;
		System.out.println(randnum);
		UiObject obj2 = obj.getChild(new UiSelector().className("android.widget.RelativeLayout").index(randnum));
		obj2.clickAndWaitForNewWindow();
	}


	public void EmailWithVedio() throws UiObjectNotFoundException{
		getObjByDesc("添加附件").clickAndWaitForNewWindow();
		getObjById("com.netease.mobimail:id/btn_attach_file").clickAndWaitForNewWindow();
		while (!getObjByTxt("sdcard0").exists()) {
			getObjByTxt("返回上一级").clickAndWaitForNewWindow();			
		}
		getChildByClsTxt("android.widget.TextView", "uitest").clickAndWaitForNewWindow();
		getChildByClsTxt("android.widget.TextView", "Vedio").clickAndWaitForNewWindow();
		UiObject obj = getObjByClsId("android.widget.ListView", "com.netease.mobimail:id/file_explorer_listview");
		int ChildCount = obj.getChildCount();
		int randnum = GetRandNum(ChildCount-1)+1;
		UiObject obj2 = obj.getChild(new UiSelector().className("android.widget.RelativeLayout").index(randnum));
		obj2.clickAndWaitForNewWindow();

	}
	public void PressSend() throws UiObjectNotFoundException {
		getObjByTxt("发送").clickAndWaitForNewWindow();
	}

	public void BegDownMus(long num) throws UiObjectNotFoundException{
		IputWebSite("http://m.xiami.com/");
		while (!getObjByDesc("排行榜 Link").exists()) {
			getUiDevice().waitForWindowUpdate(null, 500);
		}
		getObjByDesc("排行榜 Link").clickAndWaitForNewWindow();
		while (!getObjByDesc("虾米音乐榜").exists()) {
			sleep(1000);	
		}
		getObjByDesc("虾米音乐榜").clickAndWaitForNewWindow();
//		swipe("up");
		sleep(2000);
		clickPoint(1000, 1160);
		LogHelper.LogHelp("begin to download the " + num +" music");
		while (!getObjByDesc("下载标准品质 Link").exists()) {
			sleep(1000);
		}
//		int randnum2 = GetRandNum(4);
//		clickPoint(1010, 538+237*randnum2);
//		LogHelper.LogHelp("click the "+randnum2+" music");
//		System.out.println("click the "+randnum2+" music");
		getObjByDesc("下载标准品质 Link").clickAndWaitForNewWindow();
		sleep(5000);
		clickPoint(530, 1345);
	}

	//	public int ChooseMusicLib() throws UiObjectNotFoundException{
	//		getObjByTxt("乐库").clickAndWaitForNewWindow();
	//		String[] MusicLib = {"新歌首发","精选专题","猜你喜欢","酷狗TOP500","DJ热碟","经典","网络红歌","小清新"};
	//		Random rand = new Random();
	//		int randnum = rand.nextInt(MusicLib.length);
	//		while (randnum ==1 ) {
	//			randnum = rand.nextInt(MusicLib.length);		
	//		}
	//		if (randnum == 0) {
	//			getObjByTxt("欧美").clickAndWaitForNewWindow();
	//		}
	//		String ChoMusLib = MusicLib[randnum];
	//		System.out.println(ChoMusLib);
	//		UiObject uiObj = getObjByTxt(ChoMusLib);
	//		if (uiObj.exists())
	//			uiObj.clickAndWaitForNewWindow();
	//		while (!getObjByTxt(ChoMusLib).exists()) {
	//			swipe("up");
	//			getUiDevice().waitForWindowUpdate(null, 500);
	//		}
	//		getObjByTxt(ChoMusLib).clickAndWaitForNewWindow();
	//		return randnum;
	//	}
	//
	//	public UiObject ChooseMusic_pre() throws UiObjectNotFoundException{
	//		int randnum2 = GetRandNum(5)+2;
	//		UiSelector SeleCri = new UiSelector().className("android.widget.LinearLayout").index(randnum2);
	//		return getObjById("android:id/list").getChild(SeleCri);		
	//	}
	//
	//
	//
	//
	//	public UiObject ChooseMusic(int chlibnum) throws UiObjectNotFoundException{
	//		UiObject ChoseSong_pre = ChooseMusic_pre();
	//		UiSelector seleCriSelector = new UiSelector().resourceId("com.kugou.android:id/audio_item_local_icon");
	//		while (ChoseSong_pre.getChild(seleCriSelector).exists()){
	//			if (chlibnum == 0 || chlibnum == 3) {
	//				swipe("up");
	//			}
	//
	//			if (chlibnum == 2 || chlibnum == 4 || chlibnum == 5 || chlibnum == 6 || chlibnum == 7){
	//				getObjByTxt("换一批").clickAndWaitForNewWindow();
	//			}
	//			ChoseSong_pre = ChooseMusic_pre();
	//		}
	//		return ChoseSong_pre;
	//
	//	}
	//
	//	public void DownSongs(UiObject obj) throws UiObjectNotFoundException{
	//		obj.getChild(new UiSelector().resourceId("com.kugou.android:id/btn_toggle_menu")).clickAndWaitForNewWindow();
	//		getobjByIdIdx("com.kugou.android:id/menu_item_view", 0).clickAndWaitForNewWindow();
	//		System.out.println("begin to download songs");
	//		getObjByTxtContains("标准音质").clickAndWaitForNewWindow();
	//		sleep(20000);
	//	}

	public void ExitMusic() throws UiObjectNotFoundException{
		pressMenu();
		getObjByTxt("退出").clickAndWaitForNewWindow();
		System.out.println("exit 酷狗音乐");
		pressHome();
	}

	public void IputWebSite(String website) throws UiObjectNotFoundException{
		getObjByClsId("android.widget.EditText", "com.android.browser:id/url").setText(website);
//		getObjByCls("android.widget.EditText").setText(website);
		sleep(1000);
		pressEnter();

	}

	//选择要进行下载的图片
	public void ChoPic() throws UiObjectNotFoundException{
		UiObject obj = getObjByClsIdx("android.widget.ListView", 9);
		obj.getChild(new UiSelector().index(0)).clickAndWaitForNewWindow();
		while (!getObjByDesc("下载 Link").exists()) {
			sleep(1000);
		}
		
		Random rand = new Random();
		int randnum = rand.nextInt(20);
		for (int j = 1; j <= randnum +1; j++) {
			swipe("left");
			sleep(1000);
		}
	}

	//开始下载
	public void BegDownPic(long i) throws UiObjectNotFoundException{
		getObjByCls("android.widget.ListView").click();
		sleep(2000);
		LogHelper.LogHelp("begin to download the " + i + " pictures");
//		System.out.println("begin to download pictures");
		getObjByDesc("下载 Link").click();
		sleep(10000);

	}

	//退出浏览器Browser
	public void ExitApp(String Appname) throws RemoteException, UiObjectNotFoundException{
		LogHelper.LogHelp("exit "+Appname);
		pressRecentApps();
		clearRecentApp(Appname);
		pressHome();
	}
	//back退出app，不清除task stack
	public void ExitAppBack(String Appname)throws RemoteException, UiObjectNotFoundException{
		LogHelper.LogHelp("back exit "+Appname);
		pressBack();
		pressHome();
	}
	//home将app退出前台，不清除内存和task stack
	public void ExitAppHome(String Appname)throws RemoteException, UiObjectNotFoundException{
		LogHelper.LogHelp("home exit "+Appname);
		pressHome();
	}
	public void BegDownApp() throws UiObjectNotFoundException{
		IputWebSite("www.topber.com/special/6.html");
		sleep(2000);
		while (!getObjByDesc("专题列表 » ").exists()) {
			getUiDevice().waitForWindowUpdate(null, 500);
		}
		int randnum1 = GetRandNum(3);
		LogHelper.LogHelp("swipe "+randnum1+" steps");
//		System.out.println("swipe "+randnum1+" steps");
		for (int i = 0; i <randnum1; i++) {
			swipe("up");
			sleep(2000);
		}
		int randnum2;
		if (randnum1 == 0) {
			randnum2 = GetRandNum(4);
			clickPoint(170, 1080+randnum2*228);
		}
		else {
			randnum2 = GetRandNum(7);
			clickPoint(170,360+randnum2*228);
		}
		LogHelper.LogHelp("choose "+randnum2+"th app");
//		System.out.println("choose "+randnum2+"th app");
		while (!getObjByDesc("下 载 Link").exists()) {
			getUiDevice().waitForWindowUpdate(null, 500);
		}
		getObjByDesc("下 载 Link").clickAndWaitForNewWindow();
		while (!getObjByDesc("下 载 Link").exists()) {
			getUiDevice().waitForWindowUpdate(null, 500);
		}

	}


	/*	
	public void ChooseAppClass() throws UiObjectNotFoundException{
		getObjByTxt("Apps").clickAndWaitForNewWindow();
		getObjByTxt("CATEGORIES").clickAndWaitForNewWindow();
		String[] appClass = {"Personalization","Transportation","Sports","Health","Comics",
				"Medical","Business","Books","Weather","Entertainment","Media","Tools",
				"Photography","Productivity","Education","News","Travel","Lifestyle",
				"Social","Finance","Shopping","Libraries","Communication","Music","Casual",
				"Sports","Action","Family","Educational","Word","Board","Simulation","Trivia",
				"Racing","Strategy","Card","Arcade","Role","Puzzle","Casino","Music"};
		Random rand = new Random();
		int randnum = rand.nextInt(appClass.length);
		String ChosenClass = appClass[randnum];
		System.out.println("appClass "+ChosenClass);
		String id = "com.snappea.premium:id/listview";
		while (!getObjByTxt(ChosenClass).exists()) {
			getScrbyId(id).scrollForward();
		}
		getObjByTxt(ChosenClass).clickAndWaitForNewWindow();
	}

	public void ChooseAppDown() throws UiObjectNotFoundException{
		int randnum2 = GetRandNum(30);
		for (int j = 0; j < randnum2; j++) {
			while (getObjById("com.snappea.premium:id/listview").exists()) {
				getScrbyId("com.snappea.premium:id/listview").scrollForward();
				sleep(2000);
			}
		}
		int randnum3 = GetRandNum(3);
		getobjByidxChid(randnum3+1, "Install").clickAndWaitForNewWindow(20000);
		if (getObjByTxt("Ask me later").exists()) {
			getObjByTxt("Ask me later").click();
		}
		getObjByTxt("Cancel").click();
	}		

	 */


//	public void BegToPlayMus(int timedur) throws UiObjectNotFoundException{
//		getObjByTxt("本地音乐").clickAndWaitForNewWindow();   
//		if (!getObjByTxt("随机播放").exists()) {
//			getObjById("com.kugou.android:id/list_common_bar_header_randomplay").clickAndWaitForNewWindow();
//			clickPoint(269, 578);
//			sleep(1000);
//		}
//		
//			int randnum = GetRandNum(5);
//			for (int i = 0; i < randnum; i++) {
//				swipe("up");
//				sleep(2000);
//			}
//	
//		UiSelector selcri = new UiSelector().className("android.widget.LinearLayout").index(2); 
//		UiObject obj = getObjByClsId("android.widget.ListView", "android:id/list").getChild(selcri);
//		obj.click();
//		System.out.println("play music for "+timedur+" minites");
//		Timer timer = new Timer(); 
//		TimerTask mytask = new TimerTask() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					getObjById("com.kugou.android:id/playing_bar_toggle").click();
//					sleep(2000);
//					ExitMusic();
//		
//				} catch (UiObjectNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		};
//		timer.schedule(mytask, timedur*60000);
//		while (!getObjByTxt("Apps").exists()) {
//			sleep(2000);
//		}
//	}
	
	
	public void BegToPlayMus(int timedur) throws UiObjectNotFoundException{
		sleep(2000);
		for (int i = 0; i < 5; i++) {
			swipe("left");
			sleep(3000);
		}
		
		swipe("right");
		sleep(3000);
		UiScrollable scrObj = getScrbyId("com.andrew.apollo:id/list_base");
		if (scrObj.exists()) {
			int MaxScr = scrObj.getMaxSearchSwipes();
			int randnum = GetRandNum(MaxScr/2);
			LogHelper.LogHelp("max swipe step is "+MaxScr);
			LogHelper.LogHelp("scroll "+ randnum +" steps");
			for (int i = 0; i < randnum; i++) {
				scrObj.scrollForward();
				sleep(1000);
			}
		}
		int randnum2 = GetRandNum(6);
		LogHelper.LogHelp("play the "+randnum2 +"th music");
		UiObject obj = getObjById("com.andrew.apollo:id/list_base").getChild(new UiSelector().index(randnum2));
		obj.click();
		sleep(2000);
		LogHelper.LogHelp("play music for "+timedur+" minites");
		Timer timer = new Timer(); 
		TimerTask mytask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					getObjByDesc("Pause").click();
					sleep(2000);
					ExitApp("Apollo");
					
				} catch (UiObjectNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		timer.schedule(mytask, timedur*60000);
		while (!getObjByTxt("Apps").exists()) {
			sleep(2000);
		}
		
		
		
//
//		if (!getObjByTxt("随机播放").exists()) {
//			getObjById("com.kugou.android:id/list_common_bar_header_randomplay").clickAndWaitForNewWindow();
//			clickPoint(269, 578);
//			sleep(1000);
//		}
//		
//			int randnum = GetRandNum(5);
//			for (int i = 0; i < randnum; i++) {
//				swipe("up");
//				sleep(2000);
//			}
//	
//		UiSelector selcri = new UiSelector().className("android.widget.LinearLayout").index(2); 
//		UiObject obj = getObjByClsId("android.widget.ListView", "android:id/list").getChild(selcri);
//		obj.click();
//		System.out.println("play music for "+timedur+" minites");
//		Timer timer = new Timer(); 
//		TimerTask mytask = new TimerTask() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					getObjById("com.kugou.android:id/playing_bar_toggle").click();
//					sleep(2000);
//					ExitMusic();
//		
//				} catch (UiObjectNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		};
//		timer.schedule(mytask, timedur*60000);
//		while (!getObjByTxt("Apps").exists()) {
//			sleep(2000);
//		}
	}


	public int ChooseMail() throws UiObjectNotFoundException{
//		int random2 = GetRandNum(3)+1;
		int random2 = 3;
		UiObject obj = getObjById("com.netease.mobimail:id/mail_center_list");
		UiObject obj2 = obj.getChild(new UiSelector().className("android.widget.LinearLayout").index(random2));
		obj2.clickAndWaitForNewWindow();
		System.out.println("select the "+random2+"th mail");
		return random2;
	}

	public void BeginToDown(int MailNum) throws UiObjectNotFoundException{
		getObjById("com.netease.mobimail:id/btn_show_attach").clickAndWaitForNewWindow();
		switch (MailNum) {
		case 1:
			clickPoint(985, 963);
			while (!getObjByTxt("保存所有图片").exists()) {
				getUiDevice().waitForWindowUpdate(null, 500);				
			}
			getObjByTxt("保存所有图片").clickAndWaitForNewWindow();
			sleep(10000);
			break;
		case 2:
			for (int i = 0; i < 3; i++) {
				clickPoint(992, 1385+i*130);
				while (!getObjByTxt("保存").exists()) {
					getUiDevice().waitForWindowUpdate(null, 500);	
				}
				getObjByTxt("保存").clickAndWaitForNewWindow();
				getObjByTxt("确定").clickAndWaitForNewWindow();
				sleep(10000);
			}
			break;
		case 3:
			for (int i = 0; i < 2; i++) {
				clickPoint(985, 1527+i*130);
				while (!getObjByTxt("保存").exists()) {
					getUiDevice().waitForWindowUpdate(null, 500);	
				}
				getObjByTxt("保存").clickAndWaitForNewWindow();
				getObjByTxt("确定").clickAndWaitForNewWindow();
				sleep(10000);
			}
			break;
		}
	}
	public void MesChoseCont() throws RemoteException, UiObjectNotFoundException{

		int randnum = GetRandNum(4);
		String[] RecArr = new String[]{"A","B","C","D"};
		String Receiver = RecArr[randnum]+"SAtest"+(randnum+1);
		openApp("Messaging");
		getObjByDesc("New message").clickAndWaitForNewWindow();
		getObjById("com.android.mms:id/recipients_selector").clickAndWaitForNewWindow();
		getChildByClsTxt("android.widget.TextView",Receiver).click();
		getObjByDesc("Done").clickAndWaitForNewWindow();	
	}

	public void SendSMS() throws IOException, UiObjectNotFoundException{
		String[] mess = TestHelper.getMessage(1);	//get 1 message
		for (int j=0; j<mess.length; j++) {
			getEditByTxt("Type message").setText(mess[j]);
		}		
		pressBack();
		//		getObjById("com.android.mms:id/send_button_sms").click();
	}

	public void SendMMS() throws UiObjectNotFoundException{
		int randnum = GetRandNum(2);
		if (randnum == 0) {
			SendPic();
		}
		else {
			SendSound();
		}
	}

	public void SendPic() throws UiObjectNotFoundException{
		getObjByDesc("Attach").clickAndWaitForNewWindow();
		getObjByTxt("Pictures").clickAndWaitForNewWindow();
		UiObject obj = getScr("android.widget.GridView").getChild(new UiSelector().index(0));
		obj.clickAndWaitForNewWindow();
		//    getObjById("com.android.mms:id/send_button_mms").clickAndWaitForNewWindow();

	}

	public void SendSound() throws UiObjectNotFoundException{
		getObjByDesc("Attach").clickAndWaitForNewWindow();
		getObjByTxt("Record audio").clickAndWaitForNewWindow();
		getObjById("com.android.soundrecorder:id/recordButton").click();
		sleep(180000);
		getObjById("com.android.soundrecorder:id/stopButton").click();
		getObjByTxt("Done").clickAndWaitForNewWindow();
		//    getObjById("com.android.mms:id/send_button_mms").clickAndWaitForNewWindow();
	}

	public void SendIMCont() throws UiObjectNotFoundException, IOException{
		getObjByClsId("android.widget.EditText", "com.tencent.mm:id/q0").clickAndWaitForNewWindow();
		String[] mess = TestHelper.getMessage(1);	//get 1 message
		System.out.println(mess);
		for (int j=0; j<mess.length; j++) {
			getObjByClsId("android.widget.EditText", "com.tencent.mm:id/q0").setText(mess[j]);
		}	
		getObjByTxt("Send").clickAndWaitForNewWindow();
	}


	public void SetPhotoMod() throws UiObjectNotFoundException{
		getObjByDesc("Camera, video, or panorama selector").clickAndWaitForNewWindow();
		getObjByDesc("Switch to photo").click();
		sleep(2000);	
		getObjById("com.android.camera2:id/on_screen_indicators").click();
		sleep(2000);
	}

	public void SetFlash(String flash){

		clickPoint(710,1274);
		sleep(2000);
		switch (flash) {
		case "flash on":
			System.out.println("take photo with flash");
			clickPoint(640,1224);
			sleep(2000);	
			break;
		case "flash off":
			System.out.println("take photo without flash");
			clickPoint(260,1150);
			sleep(2000);
			break;
		}

	}

	public void clickPoint(float _xRel, float _yRel) {
		getUiDevice().click(toScreenX(_xRel), toScreenY(_yRel));
	}
	/* work in settings list screen */
	public String getWifiStat() throws UiObjectNotFoundException, RemoteException {
		return getSwitch("Wi‑Fi").getText();
	}
	public void openWifi() throws UiObjectNotFoundException, RemoteException {
		UiObject uiSwitch = getSwitch("Wi‑Fi");
		if (uiSwitch.exists() && !uiSwitch.isChecked()) {
			uiSwitch.click();
		}
	}
	public void closeWifi() throws UiObjectNotFoundException, RemoteException {
		UiObject uiSwitch = getSwitch("Wi‑Fi");
		if (uiSwitch.exists() && uiSwitch.isChecked()) {
			uiSwitch.click();
		}
	}

	public void open3G() throws UiObjectNotFoundException, RemoteException {
		UiObject uiSwitch = getSwitch("Mobile networks");
		if (uiSwitch.exists() && !uiSwitch.isChecked()) {
			uiSwitch.click();
		}
	}
	public void close3G() throws UiObjectNotFoundException, RemoteException {
		UiObject uiSwitch = getSwitch("Mobile networks");
		if (uiSwitch.exists() && uiSwitch.isChecked()) {
			uiSwitch.click();
		}
	}

	public void WorkWithWifi() throws RemoteException, UiObjectNotFoundException{
		openApp("Settings");
		LogHelper.LogHelp("work with WiFi");
		openWifi();
		close3G();
		ExitApp("Settings");
	}

	public void WorkWith3G() throws RemoteException, UiObjectNotFoundException{
		openApp("Settings");
		LogHelper.LogHelp("work with 3G");
		open3G();
		closeWifi();
		ExitApp("Settings");
	}



	/* work in wifi list screen */
	public void connectWifi(String wifiName, String wifiPasswd) throws RemoteException, UiObjectNotFoundException {
		Log.d(TAG, "Connect to Wifi with password...");
		UiObject uiObjWifi = getObjByTxt(wifiName);
		if (uiObjWifi.exists()) {
			Log.d(TAG, "Ap finded");
			uiObjWifi.clickAndWaitForNewWindow();
		}
		UiObject uiFor = getObjByTxt("Forget");
		if (uiFor.exists()) {
			uiFor.clickAndWaitForNewWindow();
			uiObjWifi.clickAndWaitForNewWindow();
		}
		getEdit().setText(wifiPasswd);
		pressBack();
		UiObject uiOk = getObjByTxt("Connect");
		uiOk.waitForExists(10);
		if (uiOk.exists())
			uiOk.clickAndWaitForNewWindow();
		String state = getObjByTxt(wifiName)
				.getFromParent(new UiSelector().className("android.widget.TextView")).getText();
		while (!state.equalsIgnoreCase("Connected"));
		Log.d(TAG, "wifi connected");
	}

	public void Dial10010() throws UiObjectNotFoundException{
		getObjByDesc("dial pad").clickAndWaitForNewWindow();
		getObjByTxt("1").click();
		sleep(1000);
		getObjByTxt("0").click();
		sleep(1000);
		getObjByTxt("0").click();
		sleep(1000);
		getObjByTxt("1").click();
		sleep(1000);
		getObjByTxt("0").click();
		sleep(1000);
		getObjByDesc("dial").clickAndWaitForNewWindow();
		sleep(42000);
	}


	public void ChosePhoneMode() throws UiObjectNotFoundException{
		getObjByDesc("Dialpad").clickAndWaitForNewWindow();
		getObjByTxt("1").click();
		sleep(10000);
		int randnum = GetRandNum(4);
		switch (randnum) {
		case 0:
			getObjByTxt("1").click();
			sleep(3000);
			getObjByTxt("#").click();
			sleep(5000);
			getObjByTxt("2").click();			
			break;
		case 1:
			getObjByTxt("9").click();
			break;
		case 2:
			getObjByTxt("*").click();
			break;
		case 3:
			getObjByTxt("#").click();
			break;
		}
		while (!getObjByTxt("ALL CONTACTS").exists()) {
			sleep(1000);
		}
	}

	public void BegUINavi(int timedur){
		System.out.println("UI navigation for "+timedur+" minites");
		Timer timer = new Timer(); 
		TimerTask mytask = new TimerTask() {
			@Override
			public void run() {
				pressHome();	
			}
		};
		timer.schedule(mytask, timedur*60000);
		while (!getObjByTxt("Apps").exists()) {
			for (int i = 0; i < 2; i++) {
				if (!getObjByTxt("Apps").exists()) {
					swipe("left");
					sleep(2000);
				}			
			}
			for (int i = 0; i < 2; i++) {
				if (!getObjByTxt("Apps").exists()) {
					swipe("right");
					sleep(2000);
				}
			}

		}
	}


	public void QQBackAct(int timedur){
		Timer timer = new Timer(); 
		TimerTask mytask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					ExitQQ();	
				} catch (UiObjectNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		};
		timer.schedule(mytask, timedur*60000);
		while (!getObjByTxt("Apps").exists()) {
			sleep(2000);
			}	
	}
		
	
	public void	ExitQQ() throws UiObjectNotFoundException{
		pressMenu();
		sleep(2000);
		getObjByTxt("退出QQ").clickAndWaitForNewWindow();
		getObjByTxt("OK").clickAndWaitForNewWindow();
		pressBack();	
	}
	
	
	public void WeChatBackAct(int timedur){
		Timer timer = new Timer(); 
		TimerTask mytask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					ExitApp("WeChat");	
				} catch (UiObjectNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		};
		timer.schedule(mytask, timedur*60000);
		while (!getObjByTxt("Apps").exists()) {
			sleep(2000);
			}	
	}
	

	
	
	
	
	
	/**
	 * call directly a Telphone number
	 * @param number tel number
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	/* work in phone screen */
	public void callTelnum(String number) throws UiObjectNotFoundException, RemoteException {
		UiObject uiObj = getObjByDesc("dial pad");
		if (uiObj.exists())
			uiObj.clickAndWaitForNewWindow();
		clearEditTxt(getEdit());
		for (int i=0; i<number.length(); i++)
			getObjByTxt(String.valueOf(number.charAt(i))).click();
		getObjByDesc("dial").clickAndWaitForNewWindow();
	}
	/* work in contact list screen */
	public void callName(String name) throws RemoteException, UiObjectNotFoundException {
		openContact(name);
		getObjByClsIdx("android.widget.FrameLayout", 2).clickAndWaitForNewWindow();
	}

	public void endCall() {
		//TODO:
	}
	/**
	 * open/add/delete a contact
	 * @param name
	 * @param phone
	 * @param email
	 * @param address
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	/* now is the contacts list, will open contact window */
	public void openContact(String name) throws UiObjectNotFoundException, RemoteException {
		UiObject uiCont = getScr().getChildByText(new UiSelector().className("android.widget.TextView"), name);
		if (uiCont.exists())
			uiCont.clickAndWaitForNewWindow();
	}
	/* now is contacts list, will back to it after add a contact */
	public void addContact(Contact contact) throws RemoteException, UiObjectNotFoundException {
		if (getObjByTxt("Create a new contact").exists())
			getObjByTxt("Create a new contact").clickAndWaitForNewWindow();
		if (getObjByDesc("Add Contact").exists())
			getObjByDesc("Add Contact").clickAndWaitForNewWindow();
		getEditByTxt("Name").setText(contact.getName());
		getUiDevice().pressBack();
		getEditByTxt("Phone").setText(contact.getPhone());
		getUiDevice().pressBack();
		getEditByTxt("Email").setText(contact.getEmail());
		getUiDevice().pressBack();
		getEditByTxt("Address").setText(contact.getAddress());
		getUiDevice().pressBack();
		getObjByTxt("Done").clickAndWaitForNewWindow();	
		pressBack();
	}
	public void addContact(Contact[] cont) throws RemoteException, UiObjectNotFoundException {
		for (int i=0; i<cont.length; i++)
			addContact(cont[i]);
	}
	/* now is the contact screen */
	public void delContact(String name) throws UiObjectNotFoundException, RemoteException {
		if (getScr("android.widget.ListView").exists())
			getScr("android.widget.ListView")
			.getChildByText(new UiSelector().resourceId("com.android.contacts:id/cliv_name_textview"), name)
			.clickAndWaitForNewWindow();
		else
			getObjByTxtId(name, "com.android.contacts:id/cliv_name_textview")
			.clickAndWaitForNewWindow();
		pressMenu();
		getObjByTxt("Delete").clickAndWaitForNewWindow();
		getObjByTxt("OK").clickAndWaitForNewWindow();
	}
	public void delContact(Contact cont) throws RemoteException, UiObjectNotFoundException {
		delContact(cont.getName());
	}
	public void delContact(String[] nameList) throws RemoteException, UiObjectNotFoundException {
		for (int i=0; i<nameList.length; i++)
			delContact(nameList[i]);
	}
	public void delContact(Contact[] cont) throws RemoteException, UiObjectNotFoundException {
		for (int i=0; i<cont.length; i++)
			delContact(cont[i].getName());
	}
	public int delAllContact() throws UiObjectNotFoundException, RemoteException {
		int contNumber = 0;
		UiObject uiCont = getObjByClsId("android.widget.TextView", "com.android.contacts:id/cliv_name_textview");
		while (uiCont.exists()) {
			String contName = uiCont.getText();
			delContact(contName);
			Log.v(TAG, "delete contact: " + contName);
			contNumber++;
		}
		return contNumber;
	}

	public void exportContact() {
		//TODO:
	}
	public void importContact() {
		//TODO:
	}
	/* now is the contact screen */
	public Contact getContact(String name) throws RemoteException, UiObjectNotFoundException {
		UiObject uiObj = getObjByClsIdx("android.widget.FrameLayout", 2);
		String phone = uiObj.getChild(new UiSelector().resourceId("com.android.contacts:id/data")).getText();
		phone = phone.replaceAll("-", "").replace(" ", "");
		uiObj = getObjByClsIdx("android.widget.FrameLayout", 4);
		String email = uiObj.getChild(new UiSelector().resourceId("com.android.contacts:id/data")).getText();
		uiObj = getObjByClsIdx("android.widget.FrameLayout", 6);
		String address = uiObj.getChild(new UiSelector().resourceId("com.android.contacts:id/data")).getText();
		return new Contact(name, phone, email, address);
	}
	public void editContact(Contact cont) {

	}
	/**
	 * send message
	 * @param num
	 * @param mes
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	/* work in messaging list screen */
	public void sendSmsByTelnum(String num, String mes) throws UiObjectNotFoundException, RemoteException {
		getObjByDesc("New message").clickAndWaitForNewWindow();
		getMultiEditByTxt("To").setText(num);
		getEditByTxt("Type message").setText(mes);
		pressBack();
		getObjByDesc("Send").clickAndWaitForNewWindow();
	}
	public void sendSmsByTelnum(String num, String[] mes) throws UiObjectNotFoundException {
		getObjByDesc("New message").clickAndWaitForNewWindow();
		getMultiEditByTxt("To").setText(num);
		for (int i=0; i<mes.length; i++) {
			getEditByTxt("Type message").setText(mes[i]);
			pressBack();
			//			getObjByDesc("Send").clickAndWaitForNewWindow();
		}
	}
	public void sendSmsByTelnum(String num[], String[] mes) throws UiObjectNotFoundException {
		getObjByDesc("New message").clickAndWaitForNewWindow();
		for (int i=0; i<num.length; i++) {
			getMultiEditByTxt("To").setText(num[i]);
			pressEnter();
		}
		for (int i=0; i<mes.length; i++) {
			getEditByTxt("Type message").setText(mes[i]);
			pressBack();
			getObjByDesc("Send").clickAndWaitForNewWindow();
		}
	}
	/* work in contact list screen , will keep in contact sms screen */
	public void sendSms(String name, String mes) throws UiObjectNotFoundException, RemoteException {
		openContact(name);
		getObjByDesc("Text mobile").clickAndWaitForNewWindow();
		getEditByTxt("Type message").setText(mes);
		pressBack();
		getObjByDesc("Send").clickAndWaitForNewWindow();
	}
	public void sendSms(String name, String[] mes) throws UiObjectNotFoundException, RemoteException {
		openContact(name);
		getObjByDesc("Text mobile").clickAndWaitForNewWindow();
		for (int i=0; i<mes.length; i++) {
			getEditByTxt("Type message").setText(mes[i]);
			pressBack();
			getObjByDesc("Send").clickAndWaitForNewWindow();
		}
	}
	/* work in messaging list screen */
	public void delSms(String name) throws UiObjectNotFoundException, RemoteException {
		getObjByTxtContains(name).clickAndWaitForNewWindow();
		pressMenu();
		getObjByTxtContains("Delete").clickAndWaitForNewWindow();
		getObjByTxt("Delete").clickAndWaitForNewWindow();
	}
	public void delAllSms() {
		//TODO:
	}

	/**
	 * open web url with browser
	 * @param url, such as "www.baidu.com"
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	/* work in browser main screen */
	public void openUrl(String url) throws RemoteException, UiObjectNotFoundException {
		setEditTxt(getEdit(), url);
		UiObject uiProc = getObjById("com.android.browser:id/progress");
		if (uiProc.exists())
			uiProc.waitUntilGone(60000);
	}
	public void openUrl(String[] urlArray) throws RemoteException, UiObjectNotFoundException {
		for (int i=0; i<urlArray.length; i++)
			openUrl(urlArray[i]);
	}
	/**
	 * do some simply calculate
	 * @param cal, such as "1+2", "1*9/4+2"
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	/* work in calculator main screen */
	public void calculate(String cal) throws RemoteException, UiObjectNotFoundException {
		clearEditTxt(getEdit());
		cal = cal.replace("*", "×").replace("/", "÷").trim();
		if (!cal.endsWith("="))
			cal = cal + "=";
		for (int i=0; i<cal.length(); i++)
			getObjByTxt(String.valueOf(cal.charAt(i))).click();
		getObjByTxt("CLR").click();
		
	}
	//BY QY CalculaotrReadDocuments
	public void calReadLicenses() throws UiObjectNotFoundException{
    	Random rand = new Random();
		int i = rand.nextInt(2);
		getObjByDesc("More options").clickAndWaitForNewWindow();
		getObjByTxt("Open source licenses").clickAndWaitForNewWindow();
		randSwipeTwo(10);
		if(i == 0)
			getObjByDesc("Navigate up").click();
		else {
			pressBack();
		}
		
	}
	//By QY ART calculate
	public void calculateArt() throws UiObjectNotFoundException{
		String[] numTxt = {"0","1","2","3","4","5","6","7","8","9"};
		String[] operatorDescription1 = {"point","delete","divide","multiply","minus","plus"};
		String[] operatorDescription2 = {"switch to degrees","sine","cosine","tangent","natural logarithm","factorial","power","square root"};
		String[] operatorDescription3 = {"left parenthesis","right parenthesis"};
		String percentString = "percent";
		String[] numTxt2 = {"pi","Euler's number"};
		String txtStrings = "Bad expression";
		String endString = "equals";
		String propertyString = "hide inverse functions";
		Random randNumRandom = new Random();
		int times = randNumRandom.nextInt(10);
		
		getObjByDesc(endString);
	}
	/**
	 * open a dirpath
	 * @param dirPath, such as "baidu/ime", "/storage/emulated/0/baidu/ime"
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	/* work in FileManager main screen , but will go to the path that you have gave*/
	public void openDir(String dirPath) throws RemoteException, UiObjectNotFoundException {
		String[] dir = getPathArray(dirPath);
		for (int i=1; i<dir.length; i++)
			getChildByClsTxt("android.widget.TextView", dir[i]).clickAndWaitForNewWindow();
	}
	public void creatDir(String dirPath) throws UiObjectNotFoundException {
		if (dirPath.startsWith("/sdcard/"))
			dirPath = dirPath.replace("/sdcard/", "");
		String[] dir = dirPath.split("/");
		for (int i=0; i<dir.length; i++) {
			//UiObject uiDir = getChildByClsTxt("android.widget.TextView", dir[i]);
			UiSelector uiSel = new UiSelector().className("android.widget.TextView").text(dir[i]);
			if (isObjExist("android.widget.ListView", uiSel))
				getChild("android.widget.ListView", uiSel).clickAndWaitForNewWindow();
			else {
				getObjByDesc("Actions").click();
				getObjByTxt("New folder").clickAndWaitForNewWindow();
				getEditByTxt("New folder").setText(dir[i]);
				getObjByClsTxt("android.widget.Button", "OK").clickAndWaitForNewWindow();
				getChild("android.widget.ListView", uiSel).clickAndWaitForNewWindow();
			}
		}
	}
	public void delDir(String dirPath) throws UiObjectNotFoundException {
		if (dirPath.startsWith("/sdcard/"))
			dirPath = dirPath.replace("/sdcard/", "");
		String dir = dirPath.split("/")[0];
		while (!getCwd().equalsIgnoreCase("/sdcard"))
			pressBack();
		delFile(dir);
	}
	public void creatFile(String fileName) throws UiObjectNotFoundException {
		if (!isObjExist("android.widget.ListView", new UiSelector().text(fileName))) {
			getObjByDesc("Actions").click();
			getObjByTxt("New file").clickAndWaitForNewWindow();
			getEditByTxt("New file").setText(fileName);
			getObjByClsTxt("android.widget.Button", "OK").clickAndWaitForNewWindow();
		}
	}
	public void creatFile(String fileName, String content) throws UiObjectNotFoundException {
		creatFile(fileName);
		editFile(fileName, content);
	}
	public void creatFile(String[] fileArray) throws UiObjectNotFoundException {
		for (int i=0; i<fileArray.length; i++)
			creatFile(fileArray[i]);
	}
	public void editFile(String fileName, String content) throws UiObjectNotFoundException {
		UiSelector uiSel = new UiSelector()
		.resourceId("com.cyanogenmod.filemanager:id/navigation_view_item_name")
		.className("android.widget.TextView")
		.text(fileName);
		if (isObjExist("android.widget.ListView", uiSel)) {
			getChild("android.widget.ListView", uiSel).clickAndWaitForNewWindow();
			getEdit().setText(content);
			UiObject uiSave = getObjByClsDesc("android.widget.ImageButton", "Save");
			uiSave.waitForExists(30000);
			uiSave.click();
			uiSave.waitUntilGone(30000);
			pressBack();
		}
	}
	/* work in dirpath, you have openDir(dirPath) */
	public void selectFile(String fileName) throws UiObjectNotFoundException {
		if (getScr("android.widget.ListView").exists())
			getScr("android.widget.ListView")
			.getChildByText(new UiSelector().className("android.widget.LinearLayout"), fileName)
			.getChild(new UiSelector().className("android.widget.ImageButton"))
			.click();
		else {
			getColByCls("android.widget.ListView")
			.getChildByText(new UiSelector().className("android.widget.LinearLayout"), fileName)
			.getChild(new UiSelector().className("android.widget.ImageButton"))
			.click();
		}
	}
	public void delFile(String fileName) throws UiObjectNotFoundException {
		selectFile(fileName);
		getObjByDesc("Actions").click();
		getObjByTxt("Delete selection").clickAndWaitForNewWindow();
		getObjByTxt("Yes").clickAndWaitForNewWindow();
		if (!getObjByTxt(fileName).exists())
			Log.d(TAG, "file" + fileName + "has deleted");
	}
	public void delFile(String[] fileArray) throws UiObjectNotFoundException {
		for (int i=0; i<fileArray.length; i++)
			selectFile(fileArray[i]);
		getObjByDesc("Actions").click();
		getObjByTxt("Delete selection").clickAndWaitForNewWindow();
		getObjByTxt("Yes").clickAndWaitForNewWindow();
	}
	public void delAllFile() throws UiObjectNotFoundException {
		getObjByDesc("Actions").click();
		getObjByTxt("Select all").clickAndWaitForNewWindow();
		getObjByDesc("Actions").click();
		getObjByTxt("Delete selection").clickAndWaitForNewWindow();
		getObjByTxt("Yes").clickAndWaitForNewWindow();
	}
	public void renameFile(String oldName, String newName) throws UiObjectNotFoundException {
		selectFile(oldName);
		getObjByDesc("Actions").click();
		//TODO:can't do it, without longpress
	}
	public String getCwd() throws UiObjectNotFoundException {
		String cwd = "/sdcard";
		int i=1; 
		UiObject uiObj = getObjByIdIns("com.cyanogenmod.filemanager:id/breadcrumb_item", i);
		while (uiObj.exists()) {
			cwd += "/" + uiObj.getText();
			i++;
			uiObj = getObjByIdIns("com.cyanogenmod.filemanager:id/breadcrumb_item", i);
		}
		return cwd;
	}
	/* convert /sdcard/wandoujia/app to {"wandoujia", "app"} */
	public String[] getPathArray(String path) {
		if (path.startsWith("/sdcard/"))
			path = path.replace("/sdcard/", "sdcard/");
		return path.split("/");
	}
	/**
	 * mv file from oldpath to newpath
	 * @param oldpath, must be absolute path
	 * @param newpath, can be absolute path, such as /sdcard/wandoujia/app, or relative path, such as ../../abb
	 * @param fileName, the file what you want mv
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException 
	 */
	public void mvFile(String oldpath, String newpath, String fileName) throws UiObjectNotFoundException, RemoteException {
		//TODO:
	}
	public String[] getFileList(String dirPath, String suffix) throws RemoteException, UiObjectNotFoundException {
		openDir(dirPath);
		String regex = "[-_a-zA-Z0-9\u4e00-\u9fa5]+\\."+suffix+"$";
		UiCollection uiCol = new UiCollection(new UiSelector().className("android.widget.ListView"));
		int num = uiCol.getChildCount(new UiSelector().className("android.widget.TextView").textMatches(regex));
		String[] fileList = new String[num];
		for (int i=0; i<num; i++) {
			fileList[i] = getObjByTxtMatchesIns(regex, i).getText();
		}
		return fileList;
		//TODO: not work when scrollable exists.
	}
	/**
	 * turn on airplane mode
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	/* work in Settings -> Wireless & networks screen */
	public void openApMode() throws UiObjectNotFoundException, RemoteException {
		UiObject uiCbox = getObjByClsIdx("android.widget.CheckBox", 0);
		if (!uiCbox.isChecked())
			uiCbox.click();
	}
	public void closeApMode() throws RemoteException, UiObjectNotFoundException {
		UiObject uiCbox = getObjByClsIdx("android.widget.CheckBox", 0);
		if (uiCbox.isChecked())
			uiCbox.click();
	}
	/**
	 * turn on bluetooth
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	/* work in Settings */
	public void openBluetooth() throws RemoteException, UiObjectNotFoundException {
		UiObject uiSwitch = getSwitch("Bluetooth");
		if (uiSwitch.exists() && !uiSwitch.isChecked())
			uiSwitch.click();
	}
	public void closeBluetooth() throws RemoteException, UiObjectNotFoundException {
		UiObject uiSwitch = getSwitch("Bluetooth");
		if (uiSwitch.exists() && uiSwitch.isChecked())
			uiSwitch.click();
	}
	/**
	 * turn on mobile networks
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	/* work in Settings */
	public void openDataNet() throws RemoteException, UiObjectNotFoundException {
		UiObject uiSwitch = getSwitch("Mobile networks");
		if (uiSwitch.exists() && !uiSwitch.isChecked())
			uiSwitch.click();
	}
	public void closeDataNet() throws RemoteException, UiObjectNotFoundException {
		UiObject uiSwitch = getSwitch("Mobile networks");
		if (uiSwitch.exists() && uiSwitch.isChecked())
			uiSwitch.click();
	}
	/**
	 * add email with address, passwd, and email type, such as POP3, IMAP, Exchange
	 * @param address
	 * @param passwd
	 * @param type
	 * @throws UiObjectNotFoundException
	 */
	/* work in Account setup screen */
	public void addAccount(String account, String passwd, String type) throws UiObjectNotFoundException {
		getObjById("com.android.email:id/account_email").setText(account);
		getObjById("com.android.email:id/account_password").setText(passwd);
		getObjById("com.android.email:id/manual_setup").clickAndWaitForNewWindow();
		getObjByTxt(type).clickAndWaitForNewWindow();
		pressBack();
		getObjByClsTxt("android.widget.Button", "Next").clickAndWaitForNewWindow();
		pressBack();
		getObjByClsTxt("android.widget.Button", "Next").clickAndWaitForNewWindow();
	}
	/* just work for the app of Mail Master */
	public void addAccountMM(String account, String passwd) throws UiObjectNotFoundException {
		if (getObjByTxt("Add Account").exists()) {
			getObjByTxt("Add Account").clickAndWaitForNewWindow();
		}
		UiObject uiTitle = getObjByClsId("android.widget.TextView", "android:id/action_bar_title");
		switch(uiTitle.getText()) {
		case "Inbox":
			pressMenu();
			getObjByTxt("Settings").clickAndWaitForNewWindow();
		case "Settings":
			getScr("android.widget.ScrollView")
			.getChildByText(new UiSelector().className("android.widget.TextView"), "Add Account")
			.clickAndWaitForNewWindow();
		case "Add Account":
			getEditById("com.netease.mobimail:id/editor_email").setText(account);
			getEditById("com.netease.mobimail:id/editor_password").setText(passwd);
			pressBack();
			getObjByTxt("Sign In").clickAndWaitForNewWindow();
			getObjByTxt("Inbox").waitForExists(3000);
			Log.v(TAG, "add mail master account:" + account);
			break;
		}
	}
	/* work in Inbox screen */
	public void delAccountMM(String account) throws UiObjectNotFoundException {
		UiObject uiTitle = getObjByClsId("android.widget.TextView", "android:id/action_bar_title");
		switch(uiTitle.getText()) {
		case "Inbox":
			pressMenu();
			getObjByTxt("Settings").clickAndWaitForNewWindow();
		case "Settings":
			UiObject uiAcc = getScr("android.widget.ScrollView")
			.getChildByText(new UiSelector().className("android.widget.TextView"), account);
			if (uiAcc.exists()) {
				uiAcc.clickAndWaitForNewWindow();
				getObjByTxt("Delete Account").clickAndWaitForNewWindow();
				getObjByTxt("Delete Account").clickAndWaitForNewWindow();
				Log.v(TAG, "delete mail master account:" + account);
			}
			break;
		}
	}
	public void delAccountMM(String[] account) throws UiObjectNotFoundException {
		for (int i=0; i<account.length; i++)
			delAccountMM(account[i]);
	}
	public int delAllAccountMM() throws UiObjectNotFoundException {
		pressMenu();
		int accNum = 0;
		getObjByTxt("Settings").clickAndWaitForNewWindow();
		String regex = "\\w+@\\w+\\.\\w+";
		UiObject uiAcc = getObjByTxtMatches(regex);
		while (uiAcc.exists()) {
			String account = uiAcc.getText();
			uiAcc.clickAndWaitForNewWindow();
			getObjByTxt("Delete Account").clickAndWaitForNewWindow();
			getObjByTxt("Delete Account").clickAndWaitForNewWindow();
			Log.v(TAG, "delete mail master account:" + account);
			accNum++;
			uiAcc = getObjByTxtMatches(regex);
		}
		return accNum;
	}
	public void selectAccountMM(String account) {

	}
	public void sendEmailMM(String account, String subject, String content, String[] cc, String[] bcc) throws UiObjectNotFoundException {
		UiObject uiTitle = getObjByClsId("android.widget.TextView", "android:id/action_bar_title");
		switch(uiTitle.getText()) {
		case "Settings":
			pressBack();
		case "Inbox":
			getObjByDesc("New Message").clickAndWaitForNewWindow();
			getSib("To: ", "android.widget.EditText").setText(account);//"To: "copy from uiautomatorviewer, not type it
			pressBack();
			if (cc.length + bcc.length > 0) {
				getObjByTxt("Cc/Bcc: ").clickBottomRight();
				if (cc.length>0) {
					UiObject uiCc = getSib("Cc: ", "android.widget.EditText");	//"Cc: "copy from uiautomatorviewer, not type it
					for (int i=0; i<cc.length; i++) {
						uiCc.setText(cc[i]);
						pressEnter();
					}
				}
				if (bcc.length>0) {
					UiObject uiBcc = getSib("Bcc: ", "android.widget.EditText");//"Bcc: "copy from uiautomatorviewer, not type it
					for (int i=0; i<bcc.length; i++) {
						uiBcc.setText(bcc[i]);
						pressEnter();
					}
				}		
				pressBack();
			}
			getEditByTxt("Subject").setText(subject);
			getEditById("com.netease.mobimail:id/mailcompose_content").setText(content);
			pressBack();
			if (getObjByTxt("Send").isEnabled())
				getObjByTxt("Send").clickAndWaitForNewWindow();
			if (getObjByTxt("Enter your name").exists()) {
				String name = account.split("@")[0];
				getEdit().setText(name);
				pressBack();
				if (getObjByTxt("Save and Send").isEnabled())
					getObjByTxt("Save and Send").clickAndWaitForNewWindow();
			}
		}
		sleep(500);
	}
	public void sendEmailMM(String account, String subject, String content) throws UiObjectNotFoundException {
		sendEmailMM(account, subject, content, new String[0], new String[0]);
	}
	// TODO:not work!
	public void switchTabMM(String tabName) throws UiObjectNotFoundException {
		String[] tabs = {"Inbox", "Outbox", "Drafts", "Sent", "Spam", "Trash"};
		boolean flag = false;
		for (int i=0; i<tabs.length; i++)
			if (tabName.equalsIgnoreCase(tabs[i])) {
				flag = true;
				break;
			}
		if (flag) {
			UiObject uiTitle = getObjByClsId("android.widget.TextView", "android:id/action_bar_title");
			if (uiTitle.getText().equalsIgnoreCase("Settings"))
				pressBack();
			swipe("lfmargin");
			getObjByClsTxtId("android.widget.TextView", tabName, "com.netease.mobimail:id/mail_folder_list_item_name").clickAndWaitForNewWindow();
		}
	}

	public void delEmailMM(String account, String subject) throws UiObjectNotFoundException {
		//TODO:can't do.
	}
	/* work in clock main screen */

	public void ChooseAlarm() throws UiObjectNotFoundException{
		UiObject uiTab = getObjByClsIdx("android.app.ActionBar$Tab", 0);
		if (!uiTab.isSelected())
			uiTab.clickAndWaitForNewWindow();
	}

	public void SetAlarm() throws UiObjectNotFoundException {

		System.out.println("begine to add alarm");
		getObjByDesc("Add alarm").clickAndWaitForNewWindow();
		int randnum1 = GetRandNum(24);
		String hour = String.format("%02d", randnum1);
		int randnum2 = GetRandNum(60);
		String minute = String.format("%02d", randnum2);
		getObjById("com.android.deskclock:id/hours").setText(hour);
		getObjById("com.android.deskclock:id/minutes").setText(minute);
		getObjByTxt("Done").clickAndWaitForNewWindow();

	}
	public void DelAlarm() throws UiObjectNotFoundException {

		UiObject dustbin = getObjById("com.android.deskclock:id/delete");
		if (dustbin.exists()) {
			dustbin.clickAndWaitForNewWindow();
		}

	}
	
	
	
	public void BegBrowPho(int timedur){
		System.out.println("browse pictures for "+timedur+" minites");
		Timer timer = new Timer(); 
		TimerTask mytask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("close Gallery");	
					pressBack();				
					pressBack();				
					pressBack();				
					pressHome();
					
			}
		};
		timer.schedule(mytask, timedur*60000);
		while (!getObjByTxt("Apps").exists()) {
			for (int i = 0; i < 15; i++) {
				if (getObjByTxt("Apps").exists()) {
					break;
				}
				swipe("left");
				sleep(2000);
			
			}
			
			for (int i = 0; i < 15; i++) {
				if (getObjByTxt("Apps").exists()) {
					break;
				}
				swipe("right");
				sleep(2000);
			}
		}
		
	}
	
	/* time format:	00:00:00 */
	public void addCountdown(String time) throws UiObjectNotFoundException {
		UiObject uiTab = getObjByClsIdx("android.app.ActionBar$Tab", 2);
		if (!uiTab.isSelected())
			uiTab.clickAndWaitForNewWindow();
		UiObject uiTim = getObjByDesc("Add Timer");
		if (uiTim.exists())
			uiTim.clickAndWaitForNewWindow();
		time = time.replaceAll(":", "");
		getObjByDesc("Delete").longClick();
		for (int i=0; i<time.length(); i++)
			getObjByTxt(String.valueOf(time.charAt(i))).click();
		getObjByTxt("Start").click();
	}
	public void addCityClock(String cityName) throws UiObjectNotFoundException {
		UiObject uiTab = getObjByClsIdx("android.app.ActionBar$Tab", 1);
		if (!uiTab.isSelected())
			uiTab.clickAndWaitForNewWindow();
		getObjByDesc("Cities").clickAndWaitForNewWindow();
		getScr().getChildByText(new UiSelector().className("android.widget.TextView"), "cityName").click();
		pressBack();
	}
	public void getCityTime(String cityName) {
		//TODO:
	}
	/* work in every screen, but will keep in Date & time */
	public void set24HourFormat() throws RemoteException, UiObjectNotFoundException {
		openSet("Settings->Date & time");
		UiObject uiCbox = getObjByClsIns("android.widget.CheckBox", 2);
		if (uiCbox.exists() && !uiCbox.isChecked())
			uiCbox.click();
	}
	public void set12HourFormat() throws RemoteException, UiObjectNotFoundException {
		openSet("Settings->Date & time");
		UiObject uiCbox = getObjByClsIns("android.widget.CheckBox", 2);
		if (uiCbox.exists() && uiCbox.isChecked())
			uiCbox.click();
	}
	/* work in Camera screen */
	public void takePhoto() throws UiObjectNotFoundException {
		getObjByDesc("Shutter").click();
		sleep(4000);
	}
	public void TakePhotoNum(int num) throws UiObjectNotFoundException{
		for (int i = 0; i < num; i++) {
			getObjByDesc("Shutter").click();
			sleep(1000);
		}
	}
	//By QY
	public void takePhotoArt(Boolean self) throws UiObjectNotFoundException {
		if(self == true){
			getObjByClsIdx("android.widget.ImageView", 1).click();
			sleep(2000);
		}
		for (int i = 0; i < GetRandNum(5); i++){
			getObjByDesc("Shutter").click();
			sleep(2000);
		}
	}
	public void takePhotoPanoramaArt() throws UiObjectNotFoundException{
		getObjByDesc("Camera, video, or panorama selector").clickAndWaitForNewWindow();
		getObjByDesc("Switch to panorama").clickAndWaitForNewWindow();
		for (int i = 0; i < GetRandNum(5); i++){
			getObjByDesc("Shutter").click();
			sleep(2000);
		}
		
	}
	public void takeVideoSettingsArt() throws UiObjectNotFoundException{
		getObjByClsIdx("android.widget.ImageView", 2).clickAndWaitForNewWindow();
		int i = GetRandNum(7);
		while(!getObjByClsIdx("android.view.ViewGroup", i).exists()){
			LogHelper.LogHelp(String.valueOf(i));
			swipeMargin("up");
        	getUiDevice().waitForWindowUpdate(null, 500);
		}
		getObjByClsIdx("android.view.ViewGroup", i).click();
		sleep(1000);
		pressBack();
		int[] index = {0,1,2,3,4,5,6,7,8};
		int num = GetRandNum(9);
		getObjByClsIdx("android.widget.RelativeLayout", index[num]).clickAndWaitForNewWindow();
		switch(num){
		case 0:
			String[] txtStrings = {"Off","On"};
			getObjByTxt(txtStrings[GetRandNum(2)]).click();
			break;
		case 1:
			String[] txtStrings2 = {"HD 1080p","HD 720p","SD 480p","CIF","QVGA"};
			getObjByTxt(txtStrings2[GetRandNum(5)]).click();
			break;
		case 2:
			String[] txtStrings3 = {"Auto","Continuous"};
			getObjByTxt(txtStrings3[GetRandNum(2)]).click();
			break;
		case 3:
			String[] txtStrings4 = {"0s","3s","5s","10s","Infinite"};;
			getObjByTxt(txtStrings4[GetRandNum(5)]).click();
			break;
		case 4:
			String[] txtStrings5 = {"Off","On"};
			getObjByTxt(txtStrings5[GetRandNum(2)]).click();
			break;
		case 5:
			String[] txtStrings6 = {"-2","-1","0","+1","+2"};
			getObjByTxt(txtStrings6[GetRandNum(5)]).click();
			break;
		case 6:
			String[] txtStrings7 = {"Incandescent","Fluorescent","Auto","Daylight","Cloudy"};
			getObjByTxt(txtStrings7[GetRandNum(5)]).click();
			break;
		case 7:
			String[] txtStrings8 = {"Off","On"};
			getObjByTxt(txtStrings8[GetRandNum(2)]).click();
			break;
		case 8:
			String[] txtStrings9 = {"Off","On"};
			getObjByTxt(txtStrings9[GetRandNum(2)]).click();
			break;
	}
		pressBack();
		pressBack();
	}
	public void takePhotoSettingsArt() throws UiObjectNotFoundException {
		getObjByDesc("Camera, video, or panorama selector").clickAndWaitForNewWindow();
		getObjByDesc("Switch to photo").clickAndWaitForNewWindow();
		getObjByClsIdx("android.widget.ImageView", 3).clickAndWaitForNewWindow();
		int ii = GetRandNum(14);
		while(!getObjByClsIdx("android.view.ViewGroup", ii).exists()){
			swipeMargin("up");
        	getUiDevice().waitForWindowUpdate(null, 500);
		}
		getObjByClsIdx("android.view.ViewGroup", ii).click();
		
		sleep(1000);
		pressBack();
		LogHelper.LogHelp("11");

		if(getObjByClsIdx("android.widget.ImageView", 2).isEnabled()){
			LogHelper.LogHelp("22");
			getObjByClsIdx("android.widget.ImageView", 2).clickAndWaitForNewWindow();
			int i = GetRandNum(7);
			while(!getObjByClsIdx("android.view.ViewGroup", i).exists()){
				LogHelper.LogHelp(String.valueOf(i));
				swipeMargin("up");
	        	getUiDevice().waitForWindowUpdate(null, 500);
			}
			getObjByClsIdx("android.view.ViewGroup", i).click();
			sleep(1000);
			pressBack();
			
		}
		getObjByDesc("Menu button").clickAndWaitForNewWindow();
		int[] indexStrings = {1,2,3,4,8,9,10,11};
		int selNum = GetRandNum(8);
		getObjByClsIdx("android.widget.RelativeLayout", indexStrings[selNum]).clickAndWaitForNewWindow();
		switch(selNum){
			case 0:
				String[] txtStrings = {"Off","On"};
				getObjByTxt(txtStrings[GetRandNum(2)]).click();
				break;
			case 1:
				String[] txtStrings2 = {"8 MP","7.7 MP","5 MP","5 MP","2.1 MP (16:9)","2 MP","1.3 MP","1 MP","WXGA","SVGA","WVGA","VGA"};
				getObjByTxt(txtStrings2[GetRandNum(12)]).click();
				break;
			case 2:
				String[] txtStrings3 = {"55%","65% (normal)","75% (fine)","85% (super fine)","95%","100%"};
				getObjByTxt(txtStrings3[GetRandNum(6)]).click();
				break;
			case 3:
				String[] txtStrings4 = {"Off","2 seconds","5 seconds","10 seconds"};
				getObjByTxt(txtStrings4[GetRandNum(4)]).click();
				break;
			case 4:
				String[] txtStrings5 = {"0s","3s","5s","10s","Infinite"};
				getObjByTxt(txtStrings5[GetRandNum(5)]).click();
				break;
			case 5:
				String[] txtStrings6 = {"Off","On"};
				getObjByTxt(txtStrings6[GetRandNum(2)]).click();
				break;
			case 6:
				String[] txtStrings7 = {"Off","On"};
				getObjByTxt(txtStrings7[GetRandNum(2)]).click();
				break;
			case 7:
				String[] txtStrings8 = {"Auto","50 Hz (Europe)","60 Hz (USA)"};
				getObjByTxt(txtStrings8[GetRandNum(3)]).click();
				break;
		}
		LogHelper.LogHelp("11");
		pressBack();
		pressBack();
		
	}
	public void takeVideoArt() throws UiObjectNotFoundException {
		getObjByDesc("Camera, video, or panorama selector").clickAndWaitForNewWindow();
		getObjByDesc("Switch to video").clickAndWaitForNewWindow();
		getObjByDesc("Shutter").click();
		sleep(GetRandNum(30)*1000);
		getObjByDesc("Shutter").click();
	}
	public void checkPhotoArt() throws UiObjectNotFoundException{
		getObjByDesc("Filmstrip view").clickAndWaitForNewWindow();
		for(int i = 0; i < GetRandNum(5); i++){
			swipe("left");
		}
		sleep(200);
		clickPoint(0.5f,0.5f);
		clickPoint(0.5f,0.5f);
		sleep(200);
		getObjByDesc("More options").clickAndWaitForNewWindow();
		String[] tStrings = {"Delete","Slideshow","Edit","Rotate left","Rotate right","Crop","Set picture as","Details","Print"};
		//int i = GetRandNum(tStrings.length);
		int i = 8;// for test
		getObjByTxt(tStrings[i]).clickAndWaitForNewWindow();
		switch(i){
			case 0:
				String[] str = {"OK","Cancel"};
				getObjByTxt(str[GetRandNum(2)]).click();
				break;
			case 1:
				sleep(4000);
				break;
			case 2:
				getObjByTxt("Photo Editor").clickAndWaitForNewWindow();
				//to be done in another application
				break;
			case 3:
				sleep(1000);
				break;
			case 4:
				sleep(1000);
				break;
			case 5:
				swipeRel(0.5f, 0.2f, 0.5f, 0.4f, 30);
				swipeRel(0.5f, 0.9f, 0.5f, 0.8f, 30);
				swipeRel(0.01f, 0.3f, 0.01f, 0.5f, 30);
				swipeRel(0.99f, 0.3f, 0.99f, 0.5f, 30);
				getObjByTxt("Save").clickAndWaitForNewWindow();
				break;
			case 6:
				int rand2 = GetRandNum(2);
				if(rand2 == 0)
				{
					getObjByTxt("Wallpaper").clickAndWaitForNewWindow();
					// to be done in another application
				}else{
					getObjByTxt("Contact photo").clickAndWaitForNewWindow();
					// to be done in another application
				}
				break;
			case 7:
				sleep(1000);
				getObjByTxt("Close").clickAndWaitForNewWindow();
				break;
			case 8:
				getObjByDesc("Expand handle").clickAndWaitForNewWindow();
				sleep(1000);
				LogHelper.LogHelp("11");
				clickPoint(221,614);
			//	getObjByClsIdx("android.widget.Spinner", 6).clickAndWaitForNewWindow();
				String[] str3 = {"Black & White","Color"};
				getObjByTxt(str3[GetRandNum(2)]).clickAndWaitForNewWindow();
		//		getObjByClsIdx("android.widget.Spinner", 4).click();
				String[] str4 = {"Foolscap","Government Letter","Index Card 3x5","Index Card 4x6","Index Card 5x8","Junior Legal","Ledger","Legal","Letter","Monarch","Quarto","Tabloid"};
				int randNum = GetRandNum(12);
				LogHelper.LogHelp("12");   
				sleep(2000);
				LogHelper.LogHelp("w");
				getObjByTxt("Letter").clickAndWaitForNewWindow();
				//clickPoint(700, 400);
				LogHelper.LogHelp("q");

				sleep(200);
				while(!getObjByTxt(str4[randNum]).exists()){
					LogHelper.LogHelp("find");

					swipeRel(0.8f,0.3f, 0.8f, 0.6f, 30);
					getUiDevice().waitForWindowUpdate(null, 500);
				}
				getObjByTxt(str4[randNum]).clickAndWaitForNewWindow();
				sleep(1000);
				LogHelper.LogHelp("13");
				clickPoint(730,620);
				sleep(200);
			//	getObjByClsIdx("android.widget.Spinner", 8).clickAndWaitForNewWindow();
				String[] str5 = {"Portrait","Landscape"};
				getObjByTxt(str5[GetRandNum(2)]).clickAndWaitForNewWindow();
				LogHelper.LogHelp("14");

				sleep(1000);
				getObjByDesc("Collapse handle").clickAndWaitForNewWindow();
				sleep(500);
				getObjByDesc("Save to PDF").clickAndWaitForNewWindow();
				sleep(2000);
				pressBack();
			//	String[] str6 = {"Recent","Downloads"};
			//	getObjByTxt(str6[GetRandNum(2)]).clickAndWaitForNewWindow();
				getObjByDesc("Create folder").clickAndWaitForNewWindow();
			//	getObjByClsIdx("android.widget.EditText", 1).clickAndWaitForNewWindow();
				getObjByCls("android.widget.EditText");
				//edit text keyboard behavior 
				if(!getObjByClsIdx("android.widget.EditText", 1).exists()){
					LogHelper.LogHelp("false");
				}
				setEditTxt(getObjByCls("android.widget.EditText"), createString(GetRandNum(10)));			
				getObjByTxt("OK").clickAndWaitForNewWindow();
				LogHelper.LogHelp("15");
				sleep(500);
				getObjByDesc("Sort by").clickAndWaitForNewWindow();
				sleep(500);
				String[] str7 = {"By date modified","By name"};
				getObjByTxt(str7[GetRandNum(2)]).clickAndWaitForNewWindow();
				sleep(500);
				getObjByDesc("More options").clickAndWaitForNewWindow();
				int j = GetRandNum(3);
				if(j == 0){
					getObjByClsIdx("android.widget.LinearLayout", 0).clickAndWaitForNewWindow();
				}else if(j == 1){
					getObjByClsIdx("android.widget.LinearLayout", 1).clickAndWaitForNewWindow();
				}else{
					getObjByClsIdx("android.widget.LinearLayout", 2).clickAndWaitForNewWindow();
				}
				sleep(500);
				setEditTxt(getObjByCls("android.widget.EditText"), createString(GetRandNum(10)));			
				sleep(500);
				getObjByTxt("Save").clickAndWaitForNewWindow();
				break;
			
		}
	}
	public void takeVideo(int tms) throws UiObjectNotFoundException {
		getObjByDesc("Camera, video, or panorama selector").clickAndWaitForNewWindow();
		getObjByDesc("Switch to video").clickAndWaitForNewWindow();
		getObjByDesc("Shutter").click();
		sleep(tms);
		getObjByDesc("Shutter").click();
	}
	/* work in Apollo music play screen */
	public void playMusicBySearch(String songName) throws UiObjectNotFoundException {
		getObjByDesc("Search").click();
		getEdit().setText(songName);
		pressEnter();
		pressEnter();
		pressBack();
		pressBack();
	}

	public UiObject getscrObjByClsTxtCont(String Cls,String text) throws UiObjectNotFoundException{
		if (getScr().exists()){
			return getScr().getChildByText(new UiSelector().className(Cls), text);
		}

		else
			return getObjByTxt(text);
	}

	
	public void controlMusic(String control) throws UiObjectNotFoundException {
		if (control.equalsIgnoreCase("pause")) {
			UiObject uiPause = getObjByClsDesc("android.widget.ImageButton", "Pause");
			if (uiPause.exists())	uiPause.click();
		}
		else if (control.equalsIgnoreCase("next"))
			getObjByClsDesc("android.widget.ImageButton", "Next").click();
		else if (control.equalsIgnoreCase("previous"))
			getObjByClsDesc("android.widget.ImageButton", "Previous").click();
		else if (control.equalsIgnoreCase("play")) {
			UiObject uiPlay = getObjByClsDesc("android.widget.ImageButton", "Play");
			if (uiPlay.exists())	uiPlay.click();
		}	
	}
	/* work in wechat main screen */
	public void sendWMessage(String contactName, String message) throws UiObjectNotFoundException {
		getObjByClsTxt("android.widget.TextView", "CHATS").clickAndWaitForNewWindow();
		UiObject uiCont = getScr("android.widget.ListView")
				.getChildByText(new UiSelector().className("android.view.View").resourceId("com.tencent.mm:id/nickname_tv"), contactName);
		if (uiCont.exists())
			uiCont.clickAndWaitForNewWindow();
		else {
			getObjByClsTxt("android.widget.TextView", "CONTACTS").clickAndWaitForNewWindow();
			uiCont = getScr("android.widget.ListView")
					.getChildByText(new UiSelector().className("android.view.View").resourceId("com.tencent.mm:id/myview"), contactName);
			uiCont.clickAndWaitForNewWindow();
			getObjByTxt("Message").clickAndWaitForNewWindow();
		}
		getEdit().setText(message);
		pressEnter();
	}
	public void addWContact(String nickName) throws UiObjectNotFoundException {
		getObjByDesc("My account and settings").click();
		getObjByTxt("Add Contacts").clickAndWaitForNewWindow();
		getEdit().setText(nickName);
		pressEnter();
		UiObject uiObj = getObjByTxt("Follow");
		if (uiObj.exists())
			uiObj.clickAndWaitForNewWindow();
		else
			getObjByTxt("Add").clickAndWaitForNewWindow();
	}
	public void setSleep(String time) throws RemoteException, UiObjectNotFoundException {
		openSet("Settings->Display & lights->Sleep");
		getObjByTxt(time).clickAndWaitForNewWindow();
	}
	public void installApp(String app) throws UiObjectNotFoundException {
		String appName = app;
		if (!appName.endsWith(".apk"))
			appName = appName + ".apk";
		UiObject uiApp;
		if (getScr("android.widget.ListView").exists())
			uiApp = getScr("android.widget.ListView")
			.getChildByText(new UiSelector().resourceId("com.cyanogenmod.filemanager:id/navigation_view_item_name"), appName);
		else
			uiApp = getObjByTxtId(appName, "com.cyanogenmod.filemanager:id/navigation_view_item_name");
		if (uiApp.exists()) {
			uiApp.clickAndWaitForNewWindow();
			while (getObjByTxt("Next").exists())
				getObjByTxt("Next").clickAndWaitForNewWindow();
			getObjByTxt("Install").clickAndWaitForNewWindow();
			getObjByTxt("Done").waitForExists(30000);
			getObjByTxt("Done").clickAndWaitForNewWindow();
			Log.v(TAG, "install app: " + appName);
		}
	}
	public void installApp(String[] appList) throws UiObjectNotFoundException {
		for (int i=0; i<appList.length; i++)
			installApp(appList[i]);
	}
	public void uninstallApp(String app) throws UiObjectNotFoundException {
		UiObject uiApp;
		String appName = app;
		if (appName.endsWith(".apk"))
			appName = appName.replace(".apk", "");
		if (getScr("android.widget.ListView").exists())
			uiApp = getScr("android.widget.ListView")
			.getChildByText(new UiSelector().resourceId("com.android.settings:id/app_name"), appName);
		else
			uiApp = getObjByTxtId(appName, "com.android.settings:id/app_name");
		if (uiApp.exists()) {
			uiApp.clickAndWaitForNewWindow();
			getObjByClsTxt("android.widget.Button", "Uninstall").clickAndWaitForNewWindow();
			getObjByClsTxt("android.widget.Button", "OK").click();
			getObjByClsTxt("android.widget.TextView", "Downloaded").waitForExists(30000);
			Log.v(TAG, "uninstall app: " + appName);
		}
	}
	public void uninstallApp(String[] appArray) throws UiObjectNotFoundException {
		for (int i=0; i<appArray.length; i++) 
			uninstallApp(appArray[i]);
	}


	public void switchNetMode(String netMode) {
		//TODO:
	}
	/* Settings-> About Phone-> Notice: the type equal excatly what you see at phone */
	public String getPhoneStatus(String type) throws RemoteException, UiObjectNotFoundException {
		openSet("Settings->About Phone");
		UiObject uiType = getScr("android.widget.ListView")
				.getChildByText(new UiSelector().resourceId("android:id/title"), type);
		return uiType.getFromParent(new UiSelector().resourceId("android:id/summary")).getText();
	}
	/* Settings-> About Phone-> Status */
	public String getStatus(String type) throws RemoteException, UiObjectNotFoundException {
		openSet("Settings->About Phone->Status");
		UiObject uiType = getScr("android.widget.ListView")
				.getChildByText(new UiSelector().resourceId("android:id/title"), type);
		return uiType.getFromParent(new UiSelector().resourceId("android:id/summary")).getText();
	}

	// sleep
	public void sleepHourDelay() {
		System.out.println("sleep hour delay start");
		sleep(TestHelper.HOUR_DELAY);
		System.out.println("sleep hour delay end");
	}
	public void sleepDayDelay() {
		System.out.println("sleep day delay start");
		sleep(TestHelper.DAY_DELAY);
		System.out.println("sleep day delay end");
	}
}
