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

class MemTest(object):
    def __call__(self):
        temp = os.popen('adb shell cat /proc/meminfo').readlines()
        timestamp = time.ctime()
        return ('mem', timestamp, temp)

class SpaceTest(object):
    def __call__(self):
        temp = os.popen('adb shell df').readlines()
        timestamp = time.ctime()
        return ('dfspace', timestamp, temp)

class CpuTest(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys cpuinfo').readlines()
        timestamp = time.ctime()
        return ('cpu', timestamp, temp)

class SystemServerTest(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo system_server').readlines()
        timestamp = time.ctime()
        return ('systemServer', timestamp, temp)

class SystemUiTest(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.android.systemui').readlines()
        timestamp = time.ctime()
        return ('systemUi', timestamp, temp)

class TrebuchetTest(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.cyanogenmod.trebuchet').readlines()
        timestamp = time.ctime()
        return ('trebuchet', timestamp, temp)
class ProcrankTest(object):
    def __call__(self):
        temp = os.popen('adb shell procrank').readlines()
        timestamp = time.ctime()
        return ('procrank', timestamp, temp)
class CellBroadcastReceiverTest(object):
    def __call__(self):
        temp = os.popen('adb shell dumpsys meminfo com.android.cellbroadcastreceiver').readlines()
        timestamp = time.ctime()
        return ('cellbroadcastreceiver', timestamp, temp)
class DumpsysMeminfoTest(object):
    def __call__(self):
        temp = os.popen('adb shell  dumpsys meminfo').readlines()
        timestamp = time.ctime()
        return ('dumpSysMeminfo', timestamp, temp)
class SchedTest(object):
    def __call__(self):
        temp = os.popen('adb shell cat /proc/schedstat').readlines()
        timestamp = time.ctime()
        return ('schedTemp', timestamp, temp)
class CpuTest(object):
    def __call__(self):
        temp = os.popen('adb shell cat /proc/stat').readlines()
        timestamp = time.ctime()
        return ('cpu', timestamp, temp)
class Task(mp.Process):
    def __init__(self, tq, st):
        mp.Process.__init__(self)
        self.tq = tq
        self.st = st
    def run(self):
        while True:
            time.sleep(self.st)
            self.tq.put(MemTest())
            self.tq.put(CpuTest())
	    self.tq.put(SystemServerTest())
            self.tq.put(SystemUiTest())
     	    self.tq.put(TrebuchetTest())
            self.tq.put(CellBroadcastReceiverTest())
	    self.tq.put(ProcrankTest())
	    self.tq.put(DumpsysMeminfoTest())
	    self.tq.put(SchedTest())
            self.tq.put(CpuTest())
	    self.tq.put(SpaceTest())


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

    f1 = open('getData//memstate', 'w')
    f2 = open('getData//cpustate', 'w')
    f3 = open('getData//systemServerstate', 'w')
    f4 = open('getData//systemUistate', 'w')
    f5 = open('getData//trebuchetstate', 'w')
    f6 = open('getData//cellBroadcastreceiverstate', 'w')
    f7 = open('getData//procrankstate','w')
    f8 = open('getData//dumpSysMeminfostate','w')
    f9 = open('getData//schedstate','w')
    f10 = open('getData//cpu','w')
    f11 = open('getData//dfspace','w')

    f = {'mem':f1, 'cpu':f2, 'systemServer':f3, 'systemUi':f4, 'trebuchet':f5, 'cellbroadcastreceiver':f6,'procrank':f7,'dumpSysMeminfo':f8,'schedTemp':f9,'cpu':f10,'dfspace':f11}

    num_daemons = mp.cpu_count()
    daemons = [Daemon(tasksQueue, resultsQueue) for i in range(num_daemons)]
    for w in daemons:
        w.start()

    #Change time. st='sleep time', rt='run time'
    st = 10; rt = 1000000
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
