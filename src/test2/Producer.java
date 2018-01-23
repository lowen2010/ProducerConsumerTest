package test2;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

	private BlockingQueue<String> queue;
	
	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			String prodName = Thread.currentThread().getName();
			queue.put(prodName);
			System.out.println("生产产品："+prodName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
