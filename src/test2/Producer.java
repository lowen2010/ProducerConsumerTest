package test2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{

	private BlockingQueue<String> queue;
	
	private AtomicInteger count = new AtomicInteger(0);
	
	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			String prodName = Thread.currentThread().getName() + ",count=" +count.incrementAndGet();
			queue.put(prodName);
			System.out.println("生产产品："+prodName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
