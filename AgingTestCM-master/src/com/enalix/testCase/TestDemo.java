package com.enalix.testCase;

import java.io.IOException;

import android.R.integer;
import android.os.RemoteException;

import com.android.uiautomator.core.*;
import com.enalix.testUtils.*;


//import com.sun.java.util.jar.pack.Package.File;


public class TestDemo extends HighUtils{
	public void testDemo() throws RemoteException, UiObjectNotFoundException, IOException {		

		//在改变EmailAccountNum时一定要进行相应手机上信息的更改
		int EmailAccoutNum = 4;
//				int mesnum = 1;
				long IMnum = Long.MAX_VALUE;
				long emailnum = Long.MAX_VALUE;
				long musicnum = Long.MAX_VALUE;
				long picnum = Long.MAX_VALUE;
				long appnum = Long.MAX_VALUE;
				long attachnum = Long.MAX_VALUE;
				long takePicNum = Long.MAX_VALUE;
				long callnum = Long.MAX_VALUE;
				long music_time = Long.MAX_VALUE;
		    	int music_duration = 10;
		    	
				long UI_navi_duration = 1;
		    	long background_durarion =1;
				long BroPic_duration = 1;
//      
//		LogHelper.Init();
	//	initMobile();

		//you should enable the code in SendSMS(), SendPic(), SendSound() in MidUtils before send message
		//message class shouLd be SMS, MMS
//				SendMessage("MMS",mesnum);
//		SendIM(IMnum);

		// there should be folder named Vedio and Music behind the folder of sdcard0 before this operation
//				SendEmail(EmailAccoutNum,emailnum);

		//      including music, pictures, app, mail attachments
		//		Download();
//		for (int i = 0; i < musicnum; i++) {
//			DownMusic(musicnum,"wifi");	
//			sleep(10000);
//			
//		}
		
//		for (int i = 0; i < picnum; i++) {
//			DownPics(picnum,"wifi");
//			sleep(10000);
//			
//		}
//		TakePicXjInit("flash off");
		//Settings leak test
//		for(int i = 0; i < Long.MAX_VALUE;i++){
	//		settingsmemoryleak();
		//	LogHelper.LogHelp(String.valueOf(i));
	//		
//			TakePicXj(5);
//			sleep(5000);
//
//		}
//				DownPics(picnum,"wifi");
//				DownApps(appnum,"wifi");
//				DownAttach(attachnum,"wifi");
//
	//	for (int i = 0; i < takePicNum; i++) {
	//		TakePic("flash off");
		//	sleep(5000);
//			
	//	}
	/*	for(int i = 10; i < Long.MAX_VALUE;i++){
		calculator(1);
		sleep(5000);
		}
		*/
				TestWechat();
		//for test
	//	calculatorArt();
//		TakePicArt();
//		takeVideoArt();
//		OpenApp("Camera");
//		Runtime.getRuntime().exec("monkey -p org.cyanogenmod.snap -v-v-v --throttle 500 3000");
	//	sleep(100000);
	//	testZhihuPurify();
//		testCheeseSquare();
		//		checkPhotoArt();
		//VoiceCall3G(callnum);
	//	TakePicXjInit("flash off");
	//	for(int i = 0;i < Long.MAX_VALUE;i++){
	//		TakePicXj(5);
	//		sleep(5000);
	//	}
		
	//	for(int i = 0;i< Long.MAX_VALUE;i++){
	//		settingsmemoryleak();
	//		sleep(5000);
//		}
//				for (int i = 0; i < music_time; i++) {
//					playMusic(music_duration);	
//				}
//				
//				UINavi(UI_navi_duration);
//				BackGroundAct(background_durarion);
//
//				BrowsePeople();
//				BrowseCalender();
//				BrowseClock();
//				calculator(1);
		//对于照片的查看无法进行，因为uiautomator不识别相应的控件
//				BrowsePhoto(BroPic_duration);
				
				
				
//				browser("open", 3);



		//selenium part
		// 		System.setProperty("webdriver.firefox.bin","/home/qfy/software/firefox/firefox-bin");  
	
			// 启动手机端的webdriver——作为服务端，没有其他前台应用
//		System.out.println("1");
//			try {
//				//打开webdriver
//				
//	Runtime.getRuntime().exec( "adb shell am start -a android.intent.action.MAIN " +	"-n org.openqa.selenium.android.app/.MainActivity");
//	/*连接服务端webdriver，连接用的端口为8080，如果本地有开启了或使用了该端口，需要关闭（http://localhost:8080/exit），不然连接不上服务端webdriver   */
//	 Runtime.getRuntime().exec("adb forward tcp:8080 tcp:8080");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
////			
//			System.out.println("2");
//			
//WebDriver objDriver = new AndroidDriver();
//System.out.println("3");
////		System.out.println("12111111111");
//	objDriver.get("http://www.baidu.com");
//		System.out.println("13");

//
//		testdemo4 obj = new testdemo4();
//
//		obj.setUp();
//		System.out.println("13");
//		obj.testDemo4();
//		System.out.println("12111111111");
//		obj.tearDown();
//		System.out.println("11");
		//selenium part






		/*		//###########init
		int initAppNum = 6;
		int initContNum = 6;
		int initEmailNum = 3;

		String[] app = initApp(initAppNum);
		Contact[] cont = AddContact(initContNum);
		String[] email = initEmailMM(initEmailNum);

		//###########run
		Behavior bh = TestHelper.getGamerBehavior();
		runBehavior(bh, cont, app, email);
		//Behavior[] customer = TestHelper.getCustomer();
		//runCustomer(customer, cont, app, email);

		//###########clear
		clearEmailMM(email);
		clearContact(cont);
		clearApp(app);


		 */		
		
		LogHelper.commit();
	}
}



