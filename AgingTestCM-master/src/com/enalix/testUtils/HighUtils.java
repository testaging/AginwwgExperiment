package com.enalix.testUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



import android.R.integer;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;



public class HighUtils extends MidUtils{

	public static Uri mSmsUri = Uri.parse("content://sms/inbox");  


	//power on the mobile and unlock it.
	public void initMobile() throws RemoteException, UiObjectNotFoundException {
		//		System.out.println("init mobile start");
		LogHelper.LogHelp("init mobile start");
		unlock();
		//		 FileOutputStream fos;
		//		try {
		//			fos = new FileOutputStream("/sdcard/uitest/LogInformation.txt");
		//			PrintStream ps = new PrintStream(fos);
		//	        System.setOut(ps);
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

	}
	//Test zhihu purify
/*	public void testZhihuPurify() throws UiObjectNotFoundException{
		 for(int i = 0; i< 100000;i++){
		    	System.out.println(i);
		    	getObjByTxt("Zhihu Daily Purify").clickAndWaitForNewWindow();
		    	sleep(5000);
		    	getObjById("io.github.izzyleung.zhihudailypurify:id/fab_pick_date").clickAndWaitForNewWindow();
		    	sleep(1000);
		    	pressBack();
		    	sleep(1000);
		    	pressBack();
		    	sleep(2000);
		    	
		    }
	}*/
/*	public void testCheeseSquare() throws UiObjectNotFoundException{
		for(int i = 0; i < 10000;i++){
	    	System.out.println(i);
	    	getObjByTxt("Cheesesquare").clickAndWaitForNewWindow();
	    	sleep(2000);
	    	getObjByClsIdx("android.widget.LinearLayout", 1).clickAndWaitForNewWindow();
	    	sleep(2000);
	    	getObjByDesc("Navigate up").clickAndWaitForNewWindow();
	    	sleep(2000);
	    	getObjByTxt("Category 2").clickAndWaitForNewWindow();
	    	sleep(2000);
	    	getObjByClsIdx("android.widget.LinearLayout", 1).clickAndWaitForNewWindow();
	    	sleep(2000);
	    	getObjByDesc("Navigate up").clickAndWaitForNewWindow();	
	    	sleep(2000);
	    	getObjByTxt("Category 3").clickAndWaitForNewWindow();
	    	sleep(2000);
	    	getObjByClsIdx("android.widget.LinearLayout", 1).clickAndWaitForNewWindow();
	    	sleep(2000);
	    	getObjByDesc("Navigate up").clickAndWaitForNewWindow();	
	    	sleep(2000);
	    	pressBack();
	    	sleep(2000);
		}
	}
*/
	
	//send the email
	public void SendEmail(int EmailAccNum, long num) throws RemoteException, UiObjectNotFoundException, IOException{
		WorkWithWifi();

		for (int i = 0; i < num; i++) {

			LogHelper.LogHelp("send Email start");
			//			System.out.println("open WeChat");
			if (i%10==0) {
				OpenApp("邮箱大师", TestHelper.TAG_MAIL_SENT);
			}else {
				OpenApp("邮箱大师");
			}

			getObjByDesc("写邮件").clickAndWaitForNewWindow();
			ChoCont(EmailAccNum);

			int randnum = GetRandNum(4);
			switch (randnum) {
			case 0:
				break;
			case 1:
				EmailWithPic();
				break;
			case 2:
				EmailWithSound();
				break;
			case 3:
				EmailWithVedio();
				break;

			}

			String[] EmailContent = TestHelper.getEmailContent();
			SetEmailCon(EmailContent);
			LogHelper.LogHelp("the "+num +"th message has sent");
			//			System.out.println("the "+num +"th message has sent");
			PressSend();
			ExitApp("邮箱大师");
		}

		//			for (int i = 0; i < num; i++) {
		//
		//				System.out.println("send Email start");
		//				OpenApp("邮箱大师");
		//				getObjByDesc("写邮件").clickAndWaitForNewWindow();
		//				ChoCont(EmailAccNum);
		//
		//				int randnum = GetRandNum(4);
		//				switch (randnum) {
		//				case 0:
		//					break;
		//				case 1:
		//					EmailWithPic();
		//					break;
		//				case 2:
		//					EmailWithSound();
		//					break;
		//				case 3:
		//					EmailWithVedio();
		//					break;
		//
		//				}
		//
		//				String[] EmailContent = TestHelper.getEmailContent();
		//				SetEmailCon(EmailContent);
		//				System.out.println("the "+num +"th message has sent");
		//				PressSend();
		//				ExitApp("邮箱大师");
		//
		//			}
	}

	public void Download() throws RemoteException, UiObjectNotFoundException {
		DownMusic(1,"wifi");
		DownPics(1, "wifi");
		DownApps(1, "wifi");
		DownAttach(1, "wifi");

	}


	public void DownMusic(long num, String net) throws RemoteException, UiObjectNotFoundException {
		String tag = null;
		if (net.equals("wifi")){
			WorkWithWifi();
			tag = TestHelper.TAG_DOWN_MUSIC_WIFI;
		}

		if (net.equals("3G")) {
			WorkWith3G();
			tag = TestHelper.TAG_DOWN_MUSIC_3G;
		}

		for (int i = 0; i < num; i++) {

			LogHelper.LogHelp("open Browser");
			if (i%10==0) {
				OpenApp("Browser", tag);
				sleep(2000);
				BegDownMus(i);
				ExitApp("Browser");
				sleep(2*60000);
			}else {
				OpenApp("Browser");
				sleep(2000);
				BegDownMus(i);
				ExitApp("Browser");
				sleep(30000);
			}
			
			
		}

		//
		//		for (int i = 0; i < num; i++) {
		//			System.out.println("open Browser");
		//			OpenApp("Browser");
		//			BegDownMus();
		//			ExitApp("Browser");
		//		}			
	}


	//	//本程序中音乐的下载默认是从酷狗音乐下载的。务必保证安装酷狗音乐，同时不要进行版本更新
	//	public void DownMusic(int num, String net) throws RemoteException, UiObjectNotFoundException {
	//		if (net.equals("wifi"))
	//			WorkWithWifi();
	//		if (net.equals("3G")) 
	//			WorkWith3G();
	//		for (int i = 0; i < num; i++) {
	//			System.out.println("open 酷狗音乐");
	//			OpenApp("酷狗音乐");
	//			int libnum = ChooseMusicLib();
	//			UiObject ChosenSong = ChooseMusic(libnum);
	//			DownSongs(ChosenSong);
	//			ExitMusic();	
	//		}			
	//	}



	//本部分内容对网络的要求比较高，如果网络不好，出现下载失败的概率会比价大,有些图片无法下载
	public void DownPics(long num, String net) throws RemoteException, UiObjectNotFoundException{

		String tag = null;
		if (net.equals("wifi")){
			WorkWithWifi();
			tag = TestHelper.TAG_DOWN_PIC_WIFI;
		}

		if (net.equals("3G")) {
			WorkWith3G();
			tag = TestHelper.TAG_DOWN_PIC_3G;
		}

		for (int i = 0; i < num; i++) {

			LogHelper.LogHelp("open Browser");
			if (i%10==0) {
				OpenApp("Browser", tag);
			}else {
				OpenApp("Browser");
			}

			sleep(10000);
			IputWebSite("image.baidu.com/i?tn=wiseala&ie=utf8&iswiseala=1&word=%E8%8D%B7%E8%8A%B1%E9%AB%98%E6%B8%85%E5%9B%BE%E7%89%87%E5%A4%A7%E5%85%A8");
			while (!getObjByDesc("搜索 Heading").exists()) {
				getUiDevice().waitForWindowUpdate(null, 500);
			}
			ChoPic();
			BegDownPic(i);
			ExitApp("Browser");

		}


		//		if (net.equals("wifi"))
		//			WorkWithWifi();
		//		if (net.equals("3G")) 
		//			WorkWith3G();
		//		for (int i = 0; i < num; i++) {
		//			System.out.println("open Browser");
		//			OpenApp("Browser");
		//			IputWebSite("image.baidu.com/i?tn=wiseala&ie=utf8&iswiseala=1&word=%E8%8D%B7%E8%8A%B1%E9%AB%98%E6%B8%85%E5%9B%BE%E7%89%87%E5%A4%A7%E5%85%A8");
		//			while (!getObjByDesc("搜索 Heading").exists()) {
		//				getUiDevice().waitForWindowUpdate(null, 500);
		//			}
		//			ChoPic();
		//			BegDownPic();
		//			ExitApp("Browser");
		//		}
	}

	//本程序是用豌豆荚下载，务必保证不要更新应用
	//	public void DownApps(int num, String net) throws RemoteException, UiObjectNotFoundException{
	//		if (net.equals("wifi"))
	//			WorkWithWifi();
	//		if (net.equals("3G")) 
	//			WorkWith3G();
	//		for (int i = 0; i < num; i++) {
	//			System.out.println("open SnapPea");
	//			OpenApp("SnapPea");
	//			ChooseAppClass();
	//			ChooseAppDown();
	//			ExitApp("SnapPea");
	//		}
	//	}


	public void DownApps(long num, String net) throws RemoteException, UiObjectNotFoundException{

		String tag = null;
		if (net.equals("wifi")){
			WorkWithWifi();
			tag = TestHelper.TAG_DOWN_APP_WIFI;
		}

		if (net.equals("3G")) {
			WorkWith3G();
			tag = TestHelper.TAG_DOWN_APP_3G;
		}

		for (int i = 0; i < num; i++) {

			LogHelper.LogHelp("open Browser");
			if (i%10==0) {
				OpenApp("Browser", tag);
			}else {
				OpenApp("Browser");
			}

			BegDownApp();
			ExitApp("Browser");

		}	

		//		if (net.equals("wifi"))
		//			WorkWithWifi();
		//		if (net.equals("3G")) 
		//			WorkWith3G();
		//		for (int i = 0; i < num; i++) {
		//			System.out.println("open Browser");
		//			OpenApp("Browser");
		//			BegDownApp();
		//			ExitApp("Browser");
		//		}	
	}

	//Download Email attachment, be sure that the attachment is during the dates it allowed
	//up to now, only movie,music,exe can be recognised by the phone
	public void DownAttach(long num, String net) throws RemoteException, UiObjectNotFoundException{
		String tag = null;
		if (net.equals("wifi")){
			WorkWithWifi();
			tag = TestHelper.TAG_DOWN_ATT_WIFI;
		}

		if (net.equals("3G")) {
			WorkWith3G();
			tag = TestHelper.TAG_DOWN_ATT_3G;
		}

		for (int i = 0; i < num; i++) {

			LogHelper.LogHelp("Download Attachment start");
			if (i%10==0) {
				OpenApp("邮箱大师", tag);
			}else {
				OpenApp("邮箱大师");
			}
			int MailNum = ChooseMail();
			BeginToDown(MailNum);
			pressBack();
			ExitApp("邮箱大师");
		}

		//		if (net.equals("wifi"))
		//			WorkWithWifi();
		//		if (net.equals("3G")) 
		//			WorkWith3G();
		//		for (int i = 0; i < num; i++) {
		//			System.out.println("Download Attachment start");
		//			OpenApp("邮箱大师");
		//			int MailNum = ChooseMail();
		//			BeginToDown(MailNum);
		//			pressBack();
		//			ExitApp("邮箱大师");
		//			System.out.println("exit 邮箱大师");
		//
		//		}
	}

	public void TestWechat() throws UiObjectNotFoundException, IOException {
		
		getObjByCls("android.widget.EditText").clickAndWaitForNewWindow();
		for(int i = 0;i < 3;i++){
			getObjByCls("android.widget.EditText").setText("stupid");
			sleep(1000);
			getObjByClsId("android.widget.ImageButton","com.tencent.mm:id/a30").clickAndWaitForNewWindow();
			sleep(1000);
		}
		
		
	}

	public void TakePic(String action) throws RemoteException, UiObjectNotFoundException{

		//		System.out.println("start camera");
		//		openApp("Camera");
		//		SetPhotoMod();
		//		SetFlash(action);
		//		TakePhotoNum(num);
		//		System.out.println("exit carema");
		//		ExitApp("Camera");
		//		
		//		

			LogHelper.LogHelp("start camera");
			//			System.out.println("open WeChat");
	
				OpenApp("Camera", TestHelper.TAG_PIC_NOFLASH);
		
			SetPhotoMod();
			SetFlash(action);
			TakePhotoNum(10);
			ExitApp("Camera");
	}
	public void TakePicXj(int num) throws RemoteException, UiObjectNotFoundException{

		//		System.out.println("start camera");
		//		openApp("Camera");
		//		SetPhotoMod();
		//		SetFlash(action);
		//		TakePhotoNum(num);
		//		System.out.println("exit carema");
		//		ExitApp("Camera");
		//		
		//		

			LogHelper.LogHelp("start camera");
			//			System.out.println("open WeChat");	
			OpenApp("Camera", TestHelper.TAG_PIC_NOFLASH);
			sleep(1000);
		//	SetPhotoMod();
		//	SetFlash(action);
			TakePhotoNum(num);
			pressBack();
			//ExitAppBack("Camera");
			ExitApp("Camera");
		//	pressBack();
	}
	//By QY
	public void TakePicArt() throws RemoteException, UiObjectNotFoundException{
		OpenApp("Camera");
		takePhotoSettingsArt();
		sleep(1000);
		takePhotoArt(true);
		takeVideoArt();
		
	}
	
	public void TakePicXjInit(String action) throws RemoteException, UiObjectNotFoundException{

		//		System.out.println("start camera");
		//		openApp("Camera");
		//		SetPhotoMod();
		//		SetFlash(action);
		//		TakePhotoNum(num);
		//		System.out.println("exit carema");
		//		ExitApp("Camera");
		//		
		//		

			LogHelper.LogHelp("start camera");
			//			System.out.println("open WeChat");	
			OpenApp("Camera", TestHelper.TAG_PIC_NOFLASH);	
			SetPhotoMod();
			SetFlash(action);
			pressBack();
		//	TakePhotoNum(num);
			ExitApp("Camera");
	}
	

	/*
	public void playMusic(int cycle) throws RemoteException, UiObjectNotFoundException{
		System.err.println("start playback music");
		for (int i = 0; i < cycle; i++) {
			System.out.println("open 酷狗音乐");
			OpenApp("酷狗音乐");
			BegToPlayMus();
			ExitMusic();	

		}		

	}
	 */
	//	public void playMusic(int dur) throws RemoteException, UiObjectNotFoundException{
	//		System.err.println("start playback music");
	//		System.out.println("open 酷狗音乐");
	//		OpenApp("酷狗音乐");
	//		BegToPlayMus(dur);	
	//
	//	}

	public void playMusic(int dur) throws RemoteException, UiObjectNotFoundException{

      
		LogHelper.LogHelp("start playback music");
		LogHelper.LogHelp("open Apollo");
		OpenApp("Apollo", TestHelper.TAG_MUSIC_PLAY);
		BegToPlayMus(dur);	

	}

	public void SendMessage(String cls, int num) throws RemoteException, UiObjectNotFoundException, IOException{
		for (int i = 0; i < num; i++) {

			System.out.println("open Messaging");
			MesChoseCont();
			switch (cls) {
			case "SMS":
				SendSMS();
				break;
			case "MMS":
				SendMMS();
				break;

			}
			System.out.println("exit Messaging");
			pressBack();
			pressBack();
			pressHome();
		}

	}

	public void SendIM(long num) throws RemoteException, UiObjectNotFoundException, IOException{

		WorkWithWifi();
		for (int i = 0; i < num; i++) {

			LogHelper.LogHelp("open WeChat");
			//			System.out.println("open WeChat");
			if (i%1==0) {
				OpenApp("WeChat", TestHelper.TAG_IMSENT);
			}else {
				OpenApp("WeChat");
			}

			getObjByTxt("Contacts").clickAndWaitForNewWindow();
			getscrObjByClsDesc("android.view.View", "DSAtest4").clickAndWaitForNewWindow();
			//			getObjByTxt("高洋").clickAndWaitForNewWindow();
			getObjByTxt("Message").clickAndWaitForNewWindow();
			SendIMCont();
			pressBack();
			ExitApp("WeChat");
			LogHelper.LogHelp("close WeChat");
			//		getObjById("com.android.mms:id/send_button_sms").click();


		}
		LogHelper.LogHelp(num +" IM messages has sent");
	}


	public void BrowsePeople() throws RemoteException, UiObjectNotFoundException{
		int randnum = GetRandNum(11);
		//再加一组浏览
		if (randnum>7) {
			System.out.println("add contact");
			AddContact(1);
		}
		else if (randnum<1) {
			System.out.println("delete contact");
			DeleteContact(1);
		}
		else {
			SearchPeople();
		}
	}


	public void DeleteContact(int num) throws UiObjectNotFoundException, RemoteException{
		openApp("People");
		UiScrollable scrobj = getScrbyId("android:id/list");
		int scrostep = scrobj.getMaxSearchSwipes();

		for (int i = 0; i < scrostep; i++) {
			scrobj.scrollForward();
		}
		int randnum;
		UiObject obj;
		boolean YesorNo;
		do {
			if (scrostep == 0) {
				randnum = GetRandNum(4)+3;
			}
			else {
				randnum = GetRandNum(6);
			}
			obj = getObjByClsIdx("android.view.View", randnum);
			UiObject obj2 = obj.getChild(new UiSelector().resourceId("com.android.contacts:id/cliv_name_textview"));
			String ContactName = obj2.getText();
			YesorNo = ContactName.equals("ASAtest1")||ContactName.equals("BSAtest2")||
					ContactName.equals("CSAtest3")||ContactName.equals("DSAtest4");
		} while (YesorNo);

		obj.clickAndWaitForNewWindow();
		getObjByDesc("More options").clickAndWaitForNewWindow();
		getObjByTxt("Delete").clickAndWaitForNewWindow();
		getObjByTxt("OK").clickAndWaitForNewWindow();
		pressBack();
		pressBack();
	}

	public void SearchPeople() throws UiObjectNotFoundException, RemoteException{
		System.out.println("open People");
		openApp("People");
		UiScrollable scrobj = getScrbyId("android:id/list");
		int scrostep = scrobj.getMaxSearchSwipes();

		for (int i = 0; i < scrostep; i++) {
			scrobj.scrollForward();
		}
		int randnum;
		UiObject obj;
		if (scrostep == 0) {
			randnum = GetRandNum(4)+3;
		}
		else {
			randnum = GetRandNum(6);
		}
		obj = getObjByClsIdx("android.view.View", randnum);
		obj.clickAndWaitForNewWindow();
		sleep(2000);
		pressBack();
		pressBack();
		pressBack();
	}

	public void BrowseCalender() throws RemoteException, UiObjectNotFoundException{
		openApp("Calendar");
		int randnum1 = GetRandNum(5)+1;
		int randnum2 = GetRandNum(2);
		UiScrollable scrobj = getScrbyId("android:id/list");
		for (int i = 0; i < randnum1; i++) {
			if (randnum2 == 0) {
				scrobj.scrollForward();
			}
			else {
				scrobj.scrollBackward();
			}
		}
		sleep(2000);
		pressBack();
		pressHome();
	}

	public void BrowseClock() throws RemoteException, UiObjectNotFoundException{
		OpenApp("Clock");
		ChooseAlarm();
		int randnum = GetRandNum(2);
		if (randnum == 0) {
			SetAlarm();
		}
		else {
			DelAlarm();
		}
		System.out.println("begin to exit clock");
		pressBack();
		pressHome();	
	}


	public void BrowsePhoto(int timedur) throws RemoteException, UiObjectNotFoundException{
		openApp("Gallery"); 		
		clickPoint(300, 1500);
		sleep(3000);
		clickPoint(100, 400);
		sleep(3000);
		BegBrowPho(timedur);

	}


	public void VoiceCall3G(long num) throws RemoteException, UiObjectNotFoundException{

		WorkWith3G();
		for (int i = 0; i < num; i++) {
			LogHelper.LogHelp("open Phone");
			if (i%20==0) {
				OpenApp("Phone", TestHelper.TAG_CALL);
			}else {
				OpenApp("Phone");
			}
			Dial10010();
			ChosePhoneMode();
			ExitApp("Phone");
			sleep(3*60000);
		}

		//		WorkWith3G();
		//		OpenApp("Phone");
		//		System.out.println("open Phone");
		//		Dial10010();
		//		ChosePhoneMode();
		//		ExitApp("Phone");
		//		System.out.println("exit Phone");
	}


	public void UINavi(int timedur) throws UiObjectNotFoundException{
		getObjByTxt("Apps").clickAndWaitForNewWindow();
		sleep(2000);
		BegUINavi(timedur);

	}



	public void BackGroundAct(int timedur) throws RemoteException, UiObjectNotFoundException{
		WorkWithWifi();
		System.out.println("open QQ as background activity");
		OpenApp("QQ");
		QQBackAct(timedur);
		System.out.println("open WeChat as background activity");
		OpenApp("WeChat");
		WeChatBackAct(timedur);
	}






	/**
	 * send, recv, del message
	 * @param action	send, recv, del
	 * @param cont	using contact's phone number
	 * @throws IOException
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public void messaging(String action, Contact cont) throws IOException, RemoteException, UiObjectNotFoundException {
		System.out.println("Messaging " + action +" " +  cont.getName() + " start");
		openApp("Messaging");
		switch (action) {
		case "send":
			String[] mess = TestHelper.getMessage(4);	//get 4 message
			sendSmsByTelnum(cont.getPhone(), mess);
			pressBack();
			break;
		case "recv":
			break;
		case "del":
			delSms(cont.getName());
			break;
		}
		clearRecentApp("Messaging");
		pressHome();
		System.out.println("Messaging " + action +" " +  cont.getName() + " end");
	}

	/**
	 * send, recv, del email(mail master)
	 * @param action	send, recv, del
	 * @param cont	to/from contact's email
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 * @throws IOException
	 */
	public void emailMM(String action, Contact cont) throws RemoteException, UiObjectNotFoundException, IOException {
		System.out.println("Email mail master " + action + " " + cont.getName() + " start");
		openApp("Mail Master");
		switch (action) {
		case "send":
			String[] mess = TestHelper.getMessage(2);
			String content = mess[0] + "\n" + mess[1] + "\n";
			sendEmailMM(cont.getEmail(), TestHelper.EMAIL_SUBJECT, content);
		case "recv":
			break;
		case "del":
			break;
		}
		clearRecentApp("Mail Master");
		pressHome();
		System.out.println("Email mail master " + action + " " + cont.getName() + " end");
	}

	/**
	 * open/down url, getted via TestHelper.getUrl
	 * @param action	open, download
	 * @param num	url's number
	 * @throws IOException
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public void browser(String action, int num) throws IOException, RemoteException, UiObjectNotFoundException {
		System.out.println("Browser " + action + " " + num + " start");
		openApp("Browser");
		switch (action) {
		case "open":
			String[] urlopen = TestHelper.getUrlOpen(num);
			for (int i=0; i<num; i++) {
				openUrl(urlopen[i]);
				randSwipe(2);
				sleep(TestHelper.OPERATION_DELAY);
				randClick(2);
				sleep(TestHelper.OPERATION_DELAY);
			}
			break;
		case "down":
			String[] urldown = TestHelper.getUrlDown(num);
			for (int i=0; i<num; i++) {
				openUrl(urldown[i]);
				//TODO:
			}
			break;
		}
		clearRecentApp("Browser");
		pressHome();
		System.out.println("Browser " + action + " " + num + " end");
	}

	/**
	 * creat/edit/del/mv file
	 * @param action	creat, edit, del, mv
	 * @param dirPath	directory path
	 * @param fileList	filename array
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 * @throws IOException
	 */
	public void fileManager(String action, String dirPath, String[] fileList) throws UiObjectNotFoundException, RemoteException, IOException {
		System.out.println("file manager " + action + " start");
		openApp("File Manager");
		switch(action) {
		case "creat":
			System.out.println("creat dir start");
			creatDir(dirPath);
			System.out.println("creat dir end");
			for (int i=0; i<fileList.length; i++) {
				System.out.println("creat file " + fileList[i] + " start");
				String[] content = TestHelper.getMessage(3);
				String str = "";
				for (int j=0; j<content.length; j++)
					str += content[j] + "\n";
				creatFile(fileList[i], str);
				System.out.println("creat file " + fileList[i] + " start");
			}
			break;
		case "edit":
			for (int i=0; i<fileList.length; i++) {
				System.out.println("edit file " + fileList[i] + " start");
				String[] content = TestHelper.getMessage(3);
				String str="";
				for (int j=0; j<content.length; j++)
					str += content[j] + "\n";
				editFile(fileList[i], str);
				System.out.println("edit file " + fileList[i] + " end");
			}
			break;
		case "del":
			delFile(fileList);
			delDir(dirPath);
			break;
		case "mv":
			//TODO:
			break;
		}
		clearRecentApp("File Manager");
		pressHome();
		System.out.println("file manager " + action + " end");
	}



	/**
	 * install/uninstall app
	 * @param action	install/uninstall
	 * @param app	appName array
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public void appManager(String action, String[] app) throws RemoteException, UiObjectNotFoundException {
		System.out.println("App manager " + action + " " + app.length + " start");
		switch (action) {
		case "install":
			openApp("File Manager");
			openDir(TestHelper.APP_PATH);
			installApp(app);
			clearRecentApp("File Manager");
			break;
		case "uninstall":
			openSet("Settings->Apps");
			uninstallApp(app);	
			clearRecentApp("Settings");
			break;
		}
		pressHome();
		System.out.println("App manager " + action + " " + app.length + " end");
	}

	public void phone(String action, Contact cont) {
		switch (action) {
		case "call":
			break;
		}
		//TODO:
	}

	public void settings() {
		//TODO:
	}
	public void settingsmemoryleak() throws RemoteException, UiObjectNotFoundException{
		
		OpenApp("Settings", TestHelper.TAG_SETTINGS );
		//for(int i = 0;i < Long.MAX_VALUE;i++){
			openSet("Settings->Apps");
			pressBack();
			pressBack();
	//	}

	}

	/**
	 * add, del, edit contact
	 * @param action	add, del, edit
	 * @param cont	contact array
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public void people(String action, Contact[] cont) throws RemoteException, UiObjectNotFoundException{
		System.out.println("People " + action + " " + cont.length + " start");
		openApp("People");
		switch(action) {
		case "add":
			addContact(cont);
			break;
		case "del":
			delContact(cont);
			break;
		case "edit":
			break;
		}
		clearRecentApp("People");
		pressHome();
		System.out.println("People " + action + " " + cont.length + " end");
	}



	/* ---------------------------------------------------------------------For Calculator*/
	public void calculator(int num) throws RemoteException, UiObjectNotFoundException {
		LogHelper.LogHelp("Calculator start");
		String[] expr = TestHelper.generateCalExpr(num);
		OpenApp("Calculator");
		for (int i=0; i<num; i++)
			calculate(expr[i]);
		pressBack();
		clearRecentApp("Calculator");
		pressHome();
	//	pressBack();
		LogHelper.LogHelp("Calculator end");
	}
	//By QY
	public void calculatorArt() throws RemoteException, UiObjectNotFoundException {
		LogHelper.LogHelp("Calculator start");
		OpenApp("Calculator");
		calReadLicenses();
		calculateArt();
		pressBack();
		LogHelper.LogHelp("Calculator end");

		
	}
	/* ---------------------------------------------------------------------End For Calculator*/


	/* ---------------------------------------------------------------------For Tapet*/
	public void tapet() throws UiObjectNotFoundException, RemoteException {
		System.out.println("tapet start");
		openApp("Tapet");
		Random rand = new Random();
		int num = rand.nextInt(10);
		randSwipe(num);
		getObjByClsId("android.widget.ImageView", "com.sharpregion.tapet:id/applyEffect").clickAndWaitForNewWindow();
		clearRecentApp("Tapet");
		pressHome();
		System.out.println("tapet end");
	}
	/* ---------------------------------------------------------------------End For Tapet*/


	/* ---------------------------------------------------------------------For Game Play*/
	public void gamePlay(int num) throws UiObjectNotFoundException, RemoteException {
		Random rand = new Random();
		String game = TestHelper.GAMES_LIST[rand.nextInt(TestHelper.GAMES_LIST.length)];
		System.out.println("Game play " + game + " start");
		if (game.equalsIgnoreCase("2048")) {
			openApp("2048");
			for (int i=0; i<num; i++) {
				getObjByDesc("Reset").clickAndWaitForNewWindow();
				int step = play2048();
				//System.out.println(i + " times");
				Log.v(TAG, "play game 2048, using " + step + " steps");
				getObjByTxt("Try Again").clickAndWaitForNewWindow();
				if (!getObjByDesc("Reset").exists()) {
					pressBack();
					getObjByDesc("Reset").waitForExists(30000);
				}
				//System.out.println("reset exists");
			}
			pressBack();
			clearRecentApp("2048");
		}
		pressHome();
		System.out.println("Game play " + game + " end");
	}
	public int play2048() {
		String[] direction = {"right", "left", "up", "down"	};
		Random rand = new Random();
		int step = 0;
		while (getObjByDesc("Reset").exists()) {
			swipe(direction[rand.nextInt(4)]);
			step++;
		}
		return step;
	}
	/* --------------------------------------------------------------------End For Game Play*/



	/* --------------------------------------------------------------------For Mail Master*/
	public String[] initEmailMM(int num) throws UiObjectNotFoundException, RemoteException, IOException {
		System.out.println("init email start");
		String[] emailList = TestHelper.getEmail(num);
		openApp("Mail Master");
		String[] emailAcc = new String[emailList.length];
		//System.out.println(emailList.length);
		for (int i=0; i<emailList.length; i++) {
			String[] email = emailList[i].split("\\s+");
			emailAcc[i] = email[0];
			addAccountMM(email[0], email[1]);
			//System.out.printf("%s\t%s\n", email[0], email[1]);
		}
		clearRecentApp("Mail Master");
		Log.v(TAG, "init Email Mail Master");
		pressHome();
		System.out.println("init email end");
		return emailAcc;
	}
	public void clearEmailMM(String[] emailAcc) throws RemoteException, UiObjectNotFoundException {
		System.out.println("clear Email start");
		openApp("Mail Master");
		delAccountMM(emailAcc);
		clearRecentApp("Mail Master");
		Log.v(TAG, "clear Email Mail Master");
		pressHome();
		System.out.println("clear Email end");
	}
	public int clearAllEmailMM() throws RemoteException, UiObjectNotFoundException {
		openApp("Mail Master");
		int num = delAllAccountMM();
		clearRecentApp("Mail Master");
		Log.v(TAG, "clear all Email Mail Master");
		pressHome();
		return num;
	}
	/* --------------------------------------------------------------------End For Mail Master*/



	/* ----------------------------------------------------------------------For Contact*/
	public Contact[] AddContact(int num) throws RemoteException, UiObjectNotFoundException {
		System.out.println("init contact start");
		openApp("People");
		while (!getUiDevice().getCurrentPackageName().equalsIgnoreCase("com.android.contacts"));
		Contact[] cont = TestHelper.generateContact(num);
		addContact(cont);
		clearRecentApp("People");
		Log.v(TAG, "init People with " + cont.length + "contacts");
		pressHome();
		System.out.println("init contact end");
		return cont;
	}
	public void clearContact(Contact[] cont) throws RemoteException, UiObjectNotFoundException {
		System.out.println("clear contact start");
		openApp("People");
		for (int i=0; i<cont.length; i++)
			delContact(cont[i].getName());
		clearRecentApp("People");
		Log.v(TAG, "clear People with " + cont.length + "contacts");
		pressHome();
		System.out.println("clear contact end");
	}
	public int clearAllContact() throws UiObjectNotFoundException, RemoteException {
		openApp("People");
		int contNumber = delAllContact();
		Log.v(TAG, "clear all People with " + contNumber + "contacts");
		clearRecentApp("People");
		pressHome();
		return contNumber;
	}
	/* -----------------------------------------------------------------------End For Contact*/



	/* ---------------------------------------------------------------------For App Manager*/
	public String[] initApp(int num) throws RemoteException, UiObjectNotFoundException {
		System.out.println("init app start");
		openApp("File Manager");
		openDir(TestHelper.APP_PATH);
		String[] appArray = TestHelper.getAppList(num);
		installApp(appArray);
		clearRecentApp("File Manager");
		Log.v(TAG, "init App with " + appArray.length + "app");
		pressHome();
		System.out.println("init app end");
		return appArray;
	}
	public void clearApp(String[] appArray) throws RemoteException, UiObjectNotFoundException {
		System.out.println("clear app start");
		openSet("Settings->Apps");
		uninstallApp(appArray);	
		clearRecentApp("Settings");
		Log.v(TAG, "clear App with " + appArray.length + "app");
		pressHome();
		System.out.println("clear app end");
	}
	/* ---------------------------------------------------------------------End For App Manager*/


	/* ---------------------------------------------------------------------For Run Behavior*/
	//一个behavior实例是用户一天的行为, 行为与行为间延时TestHelper.HOUR_DELAY
	//Behavior可以自己配置，也可以通过TestHelper的getBehavior系列函数获取。
	public void runBehavior(Behavior bh, Contact[] cont, String[] app, String[] email) throws RemoteException, UiObjectNotFoundException, IOException {
		Random rand = new Random();

		int SendSmsNum = bh.getSendSmsNum();
		int SendEmailNum = bh.getSendEmailNum();
		int RecvSmsNum = bh.getRecvSmsNum();
		int RecvEmailNum = bh.getRecvEmailNum();
		int UrlOpenNum = bh.getUrlOpenNum();
		int UrlDownNum = bh.getUrlDownNum();

		int GameNum = bh.getGameNum();
		int ContactNum = bh.getContactNum();
		int AppNum = bh.getAppNum();
		int FileNum = bh.getFileNum();
		int CalculateNum = bh.getCalculateNum();

		for (int i=0; i<GameNum; i++)
			gamePlay(5);
		sleepHourDelay();

		//###########send message
		for (int i=0; i<SendSmsNum; i++)
			messaging("send", cont[rand.nextInt(cont.length)]);
		sleepHourDelay();

		//###########open url
		browser("open", UrlOpenNum);
		sleepHourDelay();

		//###########uninstall app
		String[] uninstallApps = TestHelper.getSubStrArray(app, AppNum);
		appManager("uninstall", uninstallApps);
		sleepHourDelay();

		//###########install app
		appManager("install", uninstallApps);
		sleepHourDelay();

		//###########delete contact
		int[] select = TestHelper.generateNoReptNumber(ContactNum, cont.length);
		Contact[] delCont = new Contact[ContactNum];
		for (int i=0; i<ContactNum; i++)
			delCont[i] = cont[select[i]];
		people("del", delCont);
		sleepHourDelay();

		//###########add contact what you delete forward
		people("add", delCont);
		sleepHourDelay();

		//###########send email
		for (int i=0; i<SendEmailNum; i++)
			emailMM("send", cont[rand.nextInt(cont.length)]);
		sleepHourDelay();

		//###########create file
		String dirPath = TestHelper.generatePath(4);
		String[] fileList = TestHelper.generateFile(FileNum, ".txt");
		fileManager("creat", dirPath, fileList);
		sleepHourDelay();

		//###########edit file what you create forward
		fileManager("edit", dirPath, fileList);
		sleepHourDelay();

		//###########delete file waht you create forward
		fileManager("del", dirPath, fileList);
		sleepHourDelay();

		//###########calculate expression
		calculator(CalculateNum);
		sleepHourDelay();
	}
	/* ---------------------------------------------------------------------End For Run Behavior*/


	/* ---------------------------------------------------------------------For run customer*/
	//customer即是behavior数组。若以周为单位，Behavior[7] customer。以月为单位， Behavior[30] customer。
	//customer可以通过配置behavior数组得到， 也可以通过TestHelper的getCustomer系列函数得到
	public void runCustomer(Behavior[] customer, Contact[] cont, String[] app, String[] email) throws RemoteException, UiObjectNotFoundException, IOException {
		for (int i=0; i<customer.length; i++) {
			runBehavior(customer[i], cont, app, email);
			sleepDayDelay();
		}
	}
	
		
		
		

	}
	/* ---------------------------------------------------------------------End for run customer*/


