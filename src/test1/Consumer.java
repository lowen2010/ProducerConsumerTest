package test1;

public class Consumer implements Runnable {
	
	private LockData lockData;
	
	public Consumer(LockData lockData) {
		this.lockData = lockData;
	}

	@Override
	public void run() {
		lockData.take();
	}

}
