package test2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainTest {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(5);
		Consumer consumer =  new Consumer(queue);
		Producer producer =  new Producer(queue);
		for (int i = 0; i < 15; i++) {
			new Thread(consumer).start();
			new Thread(producer).start();
		}

	}

}
