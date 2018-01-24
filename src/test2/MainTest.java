package test2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainTest {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(5);
		Consumer consumer =  new Consumer(queue);
		Producer producer =  new Producer(queue);
//		for (int i = 0; i < 15; i++) {
//			new Thread(consumer).start();
//			new Thread(producer).start();
//		}
		ExecutorService threadPool = Executors.newCachedThreadPool();
//		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 15; i++) {
			threadPool.execute(consumer);
			threadPool.execute(producer);
		}
		threadPool.shutdown();
		
		ScheduledExecutorService schThreadPool = Executors.newScheduledThreadPool(5);
		schThreadPool.schedule(producer, 1, TimeUnit.SECONDS);
		schThreadPool.schedule(consumer, 1, TimeUnit.SECONDS);
		schThreadPool.shutdown();
		
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		singleThreadPool.execute(producer);
		singleThreadPool.execute(consumer);
		singleThreadPool.shutdown();

	}

}
