package test3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试给定资源下多线程的排队现象
 * @author lowen
 *
 */
public class SemaphoreTest {
	private Semaphore semaphore = new Semaphore(3);
	private Random random = new Random();
	public class Task implements Runnable{
		private String id;
		public Task(String id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				semaphore.acquire();
				System.out.println("Thread " + this.id + " working...");
				Thread.sleep(random.nextInt(1000*3));
				semaphore.release();
				System.out.println("Thread " + this.id + " stoped...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		SemaphoreTest semaphoreTest = new SemaphoreTest();
		ExecutorService se = Executors.newCachedThreadPool();
		se.submit(semaphoreTest.new Task("A"));
		se.submit(semaphoreTest.new Task("B"));
		se.submit(semaphoreTest.new Task("C"));
		se.submit(semaphoreTest.new Task("D"));
		se.submit(semaphoreTest.new Task("E"));
		se.submit(semaphoreTest.new Task("F"));
		se.shutdown();
	}

}
