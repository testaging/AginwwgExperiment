import threading ,time
import Queue
import subprocess
from time import sleep, ctime
def now() :
    return str( time.strftime( '%Y-%m-%d %H:%M:%S' , time.localtime() ) )
class AsynchronousFileReader(threading.Thread):
    '''
    Helper class to implement asynchronous reading of a file
    in a separate thread. Pushes read lines on a queue to
    be consumed in another thread.
    '''

    def __init__(self, fd, queue):
        assert isinstance(queue, Queue.Queue)
        assert callable(fd.readline)
        threading.Thread.__init__(self)
        self._fd = fd
        self._queue = queue

    def run(self):
        '''The body of the tread: read lines and put them on the queue.'''
        for line in iter(self._fd.readline, ''):
            self._queue.put(line)

    def eof(self):
        '''Check whether there is no more content to expect.'''
        return not self.is_alive() and self._queue.empty()


class myThread (threading.Thread) :
      """docstring for myThread"""
      def __init__(self, nloop, nsec) :
          super(myThread, self).__init__()
          self.nloop = nloop
          self.nsec = nsec
      def run(self):
          print 'start loop', self.nloop, 'at:', ctime()
          sleep(self.nsec)
          print 'loop', self.nloop, 'done at:', ctime()
class bugreport(threading.Thread) :
	def run(self):	
		process = subprocess.Popen(['adb','bugreport'], 		stdout=subprocess.PIPE)

# Launch the asynchronous readers of the process' stdout.
		stdout_queue = Queue.Queue()
		stdout_reader = AsynchronousFileReader(process.stdout, stdout_queue)
		stdout_reader.start()

# Check the queues if we received some output (until there is nothing more to get).
		while not stdout_reader.eof():
	    		while not stdout_queue.empty():
				f = open('bugreport//bugreport.txt','a')
	        		line = stdout_queue.get()
	     			f.write(line)
	    			f.close()
class eventlog(threading.Thread) :
	def run(self):	
		process = subprocess.Popen(['adb','logcat','-v','threadtime','-b','events'], 		stdout=subprocess.PIPE)

# Launch the asynchronous readers of the process' stdout.
		stdout_queue = Queue.Queue()
		stdout_reader = AsynchronousFileReader(process.stdout, stdout_queue)
		stdout_reader.start()

# Check the queues if we received some output (until there is nothing more to get).
		while not stdout_reader.eof():
	    		while not stdout_queue.empty():
				f = open('logcat//logcat-eventlog.txt','a')
	        		line = stdout_queue.get()
	     			f.write(line)
	    			f.close()
class systemlog(threading.Thread) :
	def run(self):	
		process = subprocess.Popen(['adb','logcat','-v','threadtime','-b','system'], 		stdout=subprocess.PIPE)

# Launch the asynchronous readers of the process' stdout.
		stdout_queue = Queue.Queue()
		stdout_reader = AsynchronousFileReader(process.stdout, stdout_queue)
		stdout_reader.start()

# Check the queues if we received some output (until there is nothing more to get).
		while not stdout_reader.eof():
	    		while not stdout_queue.empty():
				f = open('logcat//logcat-systemlog.txt','a')
	        		line = stdout_queue.get()
	     			f.write(line)
	    			f.close()
class mainlog(threading.Thread) :
	def run(self):	
		process = subprocess.Popen(['adb','logcat','-v','threadtime','-b','main'], 		stdout=subprocess.PIPE)

# Launch the asynchronous readers of the process' stdout.
		stdout_queue = Queue.Queue()
		stdout_reader = AsynchronousFileReader(process.stdout, stdout_queue)
		stdout_reader.start()

# Check the queues if we received some output (until there is nothing more to get).
		while not stdout_reader.eof():
	    		while not stdout_queue.empty():
				f = open('logcat//logcat-mainlog.txt','a')
	        		line = stdout_queue.get()
	     			f.write(line)
	    			f.close()
class radiolog(threading.Thread) :
	def run(self):	
		process = subprocess.Popen(['adb','logcat','-v','threadtime','-b','radio'], 		stdout=subprocess.PIPE)

# Launch the asynchronous readers of the process' stdout.
		stdout_queue = Queue.Queue()
		stdout_reader = AsynchronousFileReader(process.stdout, stdout_queue)
		stdout_reader.start()

# Check the queues if we received some output (until there is nothing more to get).
		while not stdout_reader.eof():
	    		while not stdout_queue.empty():
				f = open('logcat//logcat-radiolog.txt','a')
	        		line = stdout_queue.get()
	     			f.write(line)
	    			f.close()
class kernellog(threading.Thread) :
	def run(self):	
		process = subprocess.Popen(['adb','shell','cat','/proc/kmsg'], 		stdout=subprocess.PIPE)

# Launch the asynchronous readers of the process' stdout.
		stdout_queue = Queue.Queue()
		stdout_reader = AsynchronousFileReader(process.stdout, stdout_queue)
		stdout_reader.start()

# Check the queues if we received some output (until there is nothing more to get).
		while not stdout_reader.eof():
	    		while not stdout_queue.empty():
				f = open('kernellog//kmsg.txt','a')
	        		line = stdout_queue.get()
	     			f.write(line)
	    			f.close()
def main():
     thpool=[bugreport(),eventlog(),systemlog(),mainlog(),radiolog(),kernellog()]
     print 'starting at:',now()
     for th in thpool:
         th.start()
     for th in thpool:
         th.join()
     print 'all Done at:', now()
if __name__ == '__main__':
        main() 

