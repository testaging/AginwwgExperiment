#!/usr/bin/env python3
import multiprocessing as mp
import time, os

class Daemon(mp.Process):
    def __init__(self, tq, rq):
        mp.Process.__init__(self)
        self.tq = tq
        self.rq = rq
    def run(self):
        while True:
            nt = self.tq.get()
            if nt is None:
                self.tq.task_done()
                break
            ans = nt()
            self.tq.task_done()
            self.rq.put(ans)
        return

class CameraData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.android.camera2').readlines()
        timestamp = time.ctime()
        return ('camera', timestamp, temp)

class CalculatorData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.android.calculator2').readlines()
        timestamp = time.ctime()
        return ('calculator', timestamp, temp)

class CalendarData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.android.calendar').readlines()
        timestamp = time.ctime()
        return ('calendar', timestamp, temp)

class DeskClockData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.android.deskclock').readlines()
        timestamp = time.ctime()
        return ('deskclock', timestamp, temp)

class MusicData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.cyanogenmod.eleven').readlines()
        timestamp = time.ctime()
        return ('music', timestamp, temp)

class MusicServiceData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo android.cyanogenmod.eleven:main').readlines()
        timestamp = time.ctime()
        return ('mediaservice', timestamp, temp)
class WechatData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.tencent.mm').readlines()
        timestamp = time.ctime()
        return ('wechat', timestamp, temp)

class WechatServiceData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.tencent.mm:tools').readlines()
        timestamp = time.ctime()
        return ('wechatservice', timestamp, temp)
class WechatServiceDataTwo(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.tencent.mm:push').readlines()
        timestamp = time.ctime()
        return ('wechatservicetwo', timestamp, temp)
class WechatServiceDataThree(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.tencent.mm:sandbox').readlines()
        timestamp = time.ctime()
        return ('wechatservicethree', timestamp, temp)


class NewsData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.ss.android.article.news').readlines()
        timestamp = time.ctime()
        return ('news', timestamp, temp)

class NewsServiceData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.ss.android.article.news:push').readlines()
        timestamp = time.ctime()
        return ('newsservice', timestamp, temp)

class NewsServiceDataTwo(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.ss.android.article.news:pushservice').readlines()
        timestamp = time.ctime()
        return ('newsservicetwo', timestamp, temp)
class YoukuData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.youku.phone').readlines()
        timestamp = time.ctime()
        return ('youku', timestamp, temp)
class YoukuServiceData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.push.Youku_PushService').readlines()
        timestamp = time.ctime()
        return ('youkuservice', timestamp, temp)

class YoukuServiceDataTwo(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.youku.phone:channel').readlines()
        timestamp = time.ctime()
        return ('youkuservicetwo', timestamp, temp)


class AppData(object):
    def __call__(self):
        temp = os.popen('adb shell  dumpsys meminfo com.tencent.android.qqdownloader').readlines()
        timestamp = time.ctime()
        return ('app', timestamp, temp)
class AppServiceData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.tencent.android.qqdownloader').readlines()
        timestamp = time.ctime()
        return ('appservice', timestamp, temp)
class AppServiceDataTwo(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.tencent.android.qqdownloader:daemon').readlines()
        timestamp = time.ctime()
        return ('appservicetwo', timestamp, temp)

class AppServiceDataThree(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.tencent.android.qqdownloader:tools').readlines()
        timestamp = time.ctime()
        return ('appservicethree', timestamp, temp)

class AppServiceDataFour(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.tencent.android.qqdownloader:connect').readlines()
        timestamp = time.ctime()
        return ('appservicefour', timestamp, temp)
class BaiduMapData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.baidu.BaiduMap').readlines()
        timestamp = time.ctime()
        return ('map', timestamp, temp)

class BaiduMapServiceData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.baidu.BaiduMap:MapCoreService').readlines()
        timestamp = time.ctime()
        return ('mapservice', timestamp, temp)
class BaiduMapServiceDataTwo(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.baidu.BaiduMap:bdservice_v1').readlines()
        timestamp = time.ctime()
        return ('mapservicetwo', timestamp, temp)
class BaiduMapServiceDataThree(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.baidu.BaiduMap:MapCoreService').readlines()
        timestamp = time.ctime()
        return ('mapservicethree', timestamp, temp)

class InputMethodData(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.android.inputmethod.latin').readlines()
        timestamp = time.ctime()
        return ('inputMethod', timestamp, temp)
class Task(mp.Process):
    def __init__(self, tq, st):
        mp.Process.__init__(self)
        self.tq = tq
        self.st = st
    def run(self):
        while True:
            time.sleep(self.st)
            self.tq.put(CameraData())
            self.tq.put(CalculatorData())
	    self.tq.put(CalendarData())
            self.tq.put(DeskClockData())
     	    self.tq.put(MusicData())
            self.tq.put(MusicServiceData())
	    self.tq.put(WechatData())
	    self.tq.put(WechatServiceData())
	    self.tq.put(WechatServiceDataTwo())
	    self.tq.put(WechatServiceDataThree())
            self.tq.put(NewsData())
	    self.tq.put(NewsServiceData())
	    self.tq.put(NewsServiceDataTwo())
	    self.tq.put(YoukuData())
	    self.tq.put(YoukuServiceData())
	    self.tq.put(YoukuServiceDataTwo())
	    self.tq.put(AppData())
	    self.tq.put(AppServiceData())
	    self.tq.put(AppServiceDataTwo())
	    self.tq.put(AppServiceDataThree())
	    self.tq.put(AppServiceDataFour())
	    self.tq.put(BaiduMapData())
	    self.tq.put(BaiduMapServiceData())
	    self.tq.put(BaiduMapServiceDataTwo())
	    self.tq.put(BaiduMapServiceDataThree())
	    self.tq.put(InputMethodData())


class DataStore(mp.Process):
    def __init__(self, rq, f):
        mp.Process.__init__(self)
        self.rq = rq
        self.f = f
    def run(self):
        while True:
            flags, timestamp, data = self.rq.get()
            fname = self.f[flags]
            fname.write('==>' + timestamp + '\n')
            fname.writelines(data)
            fname.flush()

if __name__ == '__main__':
    tasksQueue = mp.JoinableQueue()
    resultsQueue = mp.Queue()

    f1 = open('getAppData//camera', 'w')
    f2 = open('getAppData//calculator', 'w')
    f3 = open('getAppData//calendar', 'w')
    f4 = open('getAppData//deskclock', 'w')
    f5 = open('getAppData//music', 'w')
    f6 = open('getAppData//mediaservice', 'w')
    f7 = open('getAppData//wechat','w')
    f8 = open('getAppData//wechatservice','w')
    f9 = open('getAppData//wechatservicetwo','w')
    f10 = open('getAppData//wechatservicethree','w')
    f11 = open('getAppData//news','w')
    f12 = open('getAppData//newsservice','w')
    f13 = open('getAppData//newsservicetwo','w')
    f14 = open('getAppData//youku','w')
    f15 = open('getAppData//youkuservice','w')
    f16 = open('getAppData//youkuservicetwo','w')
    f17 = open('getAppData//app','w')
    f18 = open('getAppData//appservice','w')
    f19 = open('getAppData//appservicetwo','w')
    f20 = open('getAppData//appservicethree','w')
    f21 = open('getAppData//appservicefour','w')
    f22 = open('getAppData//map','w')
    f23 = open('getAppData//mapservice','w')
    f24 = open('getAppData//mapservicetwo','w')
    f25 = open('getAppData//mapservicethree','w')
    f26 = open('getAppData//inputmethod','w')




    f = {'camera':f1, 'calculator':f2, 'calendar':f3, 'deskclock':f4, 'music':f5, 'mediaservice':f6,'wechat':f7,'wechatservice':f8,'wechatservicetwo':f9,'wechatservicethree':f10,'news':f11, 'newsservice':f12, 'newsservicetwo':f13, 'youku':f14, 'youkuservice':f15, 'youkuservicetwo':f16,'app':f17,'appservice':f18,'appservicetwo':f19,'appservicethree':f20,'appservicefour':f21,'map':f22,'mapservice':f23,'mapservicetwo':f24,'mapservicethree':f25,'inputMethod':f26}

    num_daemons = mp.cpu_count()
    daemons = [Daemon(tasksQueue, resultsQueue) for i in range(num_daemons)]
    for w in daemons:
        w.start()

    #Change time. st='sleep time', rt='run time'
    st = 10; rt = 10000000
    taskproc = Task(tasksQueue, st)
    taskproc.start()
    datastore = DataStore(resultsQueue, f)
    datastore.start()

    time.sleep(rt)
    taskproc.terminate()
    taskproc.join()
    for i in range(num_daemons):
        tasksQueue.put(None)
    tasksQueue.join()
    datastore.terminate()
    datastore.join()
    for k in f:
        f[k].close()
