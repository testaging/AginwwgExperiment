# Usage
#ADT with eclipse is currently unavailable, use Android-studio instead
* Windows
  * download JDK7 for Windows
  * download [ADT](developer.android.com)
  * download [Git for windows](msysgit.github.io)
  * clone this repository `git clone https://github.com/enali/AgingTestCM` int Git Bash
  * import project in eclipse, File->Import->General(Existing Project ...), select project directory. you can get it by input `pwd` in Git Bash, please convert it to Windows style path. convert to windows' style path.
  * change the `local.properties`, set your sdk.dir
  * right click project, add external archives to build path, choose `sdkpath\platforms\android-xx\(android.jar|uiautomator.jar)`
  * connect your phone, make sure your phone has install CyanogenMod
  * run uitest.bat at cmd.exe. You need change the adb.exe's path. (always be `sdkpath\platform-tools\adb.exe`)
* Linux
  * download JDK7, ADT for Linux
  * install git. `sudo apt-get install git git-core` for Ubuntu users. run git clone
  * import project, set sdk.dir, add external archives, connect your phone, run uitest.sh.


# Low Utils
## init
* unlock():void
* openSet(String):void
* clearNotifications():void

## App
* openAppList():void
* openApp(String):void

## UiObject
* getObjByTxt(String):UiObject
* getObjByTxtContains(String):UiObject
* getObjByClsTxt(String, String):UiObject
* getObjByClsTxtContains(String, String):UiObject
* getObjById(String):UiObject
* getObjByClsIdx(String, String):UiObject
* getObjByDesc(String):UiObject
* getObjByDescContains(String):UiObject
* getChildByClsTxt(String, String):UiObject

## UiScrollable
* getScrObj():UiScrollable
* getScrObjByCls(String):UiScrollable

## Editor
* getEdit():UiObject
* getEditByTxt(String):UiObject
* getMultiEdit():UiObject
* getMultiEditByTxt(String):UiObject
* clearEditTxt(UiObject):void
* setEditTxt(UiObject, String):void

## RecentApp
* getRecentApp(String):UiObject
* openRecentApp(String):void
* clearRecentApp(String):void
* clearAllRecentApp():void

## Action
* toScreenX(float):int
* toScreenY(float):int
* pressHome():void
* pressBack():void
* PressDelete():void
* pressMenu():void
* pressEnter():void
* swipe(String):boolean
* longPress(UiObject):void      //TODO:
* longPress(int, int):void      //TODO:
* longPress(float, float):void  //TODO:


# Mid Utils

## Settings
### Wifi
* getWifiStat():String
* openWifi():void
* closeWifi():void
* connectWifi(String, String):void

### ApMode
* openApMode():void
* closeApMode():void

### Bluetooth
* openBluetooth():void
* closeBluetooth():void

### DataSet
* openDataSet():void
* closeDataSet():void

## Contact
* openContact(String):void
* addContact(Contact):void
* delContact(String):void
* getContact(String):Contact

## Sms
* sendSms(String, String):void
* sendSmsByTelnum(String, String):void
* delSms(String):void

## Phone
* callTelnum(String):void
* callName(String):void

## Browser
* openUrl(String):void

## Calculator
* calculate(String):void

## File Manager
* openDir(String):void
* delFile(String):boolean   //TODO:

# High Utils
## app
* appManager(String, String[]):void
* fileManager(String, String, String[]):void
* browser(String, int):void
* calculator(int):void
* emailMM(String, Contact):void
* gamePlay(int):void
* messaging(String, Contact):void
* people(String, Contact[]):void
* phone(String, Contact):void   //TODO:
* play2048():int
* settings():void   //TODO:
* tapet():void  //TODO:

## init/clear
* initApp(int):String[]
* clearApp(String[]):void
* initContact(int):Contact[]
* clearContact(Contact[]):void
* clearAllContact():int
* initEmailMM(int):String[]
* clearEmailMM(String[]):void
* clearAllEmailMM():int

## runtest
* runBehavior(Behavior, Contact[], String[], String[]):void
* runCustomer(Behavior[], Contact[], String[], String[]):void

# Test Helper
## Contact
* generateName():String
* generatePhone():String
* generatePhone(int):String[]
* generateEmail():String
* generateEmail(int):String[]
* generateAddress():String
* generateContact():Contact
* generateContact(int):Contact[]

## generate utils
* generateString(String, int, int):String
* generateString(String, int):String
* generateNoReptNumber(int, int):int[]
* generatePath(int):String
* generateFile(int, String):String[]
* generateCalExpr():String
* generateCalExpr(int):String[]

## get utils
* getFileLines(String):String[]
* getRandLines(String, int):String[]
* getDirFiles(String, int):String[]
* getDirFiles(String):String[]
* getRandFiles(String, final String, int):String[]
* getSubStrArray(String[], int):String[]
* getSubArray(Object[], int):Object[]   //TODO:not work

* getUrlDown(int):String[]
* getUrlOpen(int):String[]
* getMessage(int):String[]
* getEmail():String[]
* getEmail(int):String[]
* getAppList():String[]
* getAppList(int):String[]

## behavior/customer
* getGamerBehavior():Behavior

* getBusinessWorkdayBehavior():Behavior
* getBusinessWorkdayBehavior(int):Behavior[]
* getBusinessWeekdayBehavior():Behavior
* getBusinessCustomer():Behavior[]

* getStudentWorkdayBehavior():Behavior
* getStudentWorkdayBehavior(int):behavior[]
* getStudentWeekdayBehavior():Behavior
* getStudentCustomer():Behavior[]

* getWorkdayBehavior():Behavior
* getWorkdayBehavior(int):Behavior[]
* getWeekdayBehavior():Behavior
* getCustomer():Behavior[]
