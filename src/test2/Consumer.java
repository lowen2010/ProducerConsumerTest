package test2;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	private BlockingQueue<String> queue;
	
	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			String prodName = queue.take();
			System.out.println("消费产品："+prodName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
