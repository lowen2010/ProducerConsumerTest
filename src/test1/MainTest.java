package test1;

public class MainTest {
	public static void main(String[] args) {
//		LockData data = new SyncData();
		LockData data = new ReetrantData();
		Consumer consumer = new Consumer(data);  
    	Producer producer = new Producer(data);
    	
//		for(int i=0;i<15;i++) {
//	    	new Thread(producer).start();
//	    	new Thread(consumer).start();
//		}
    	
		new Thread(producer).start();
    	new Thread(consumer).start();
    	new Thread(consumer).start();
    	new Thread(consumer).start();
    	new Thread(producer).start();
    	new Thread(producer).start();
    	new Thread(producer).start();
    	new Thread(producer).start();
    	new Thread(producer).start();
    	new Thread(producer).start();
    	new Thread(producer).start();
    	new Thread(producer).start();
    	new Thread(producer).start();
    	new Thread(consumer).start();
    	new Thread(consumer).start();
    	new Thread(consumer).start();
    	new Thread(consumer).start();
    	new Thread(consumer).start();
    	new Thread(consumer).start();
    	new Thread(consumer).start();
	}
}
