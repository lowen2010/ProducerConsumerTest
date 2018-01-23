package test1;

public class Producer implements Runnable {
	
	private LockData lockData;
	
	public Producer(LockData lockData) {
		this.lockData = lockData;
	}

	@Override
	public void run() {
		lockData.push();
	}

}
