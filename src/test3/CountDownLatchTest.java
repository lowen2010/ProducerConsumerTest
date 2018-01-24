package test3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用CountDownLatch 辅助类计数器判断子线程的执行情况，达到主线程等待子线程任务结束继续执行的目的
 * @author lowen
 *
 */
public class CountDownLatchTest {
	public static class Task1 implements Callable<List<String>>{
		private CountDownLatch countDownLatch;
		public Task1(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		private static void processTask1(List<String> list) throws InterruptedException {
			System.out.println("process task1 data...");
			if(list.size() > 0) {
				System.out.println("data="+list.get(0));
			}
			Thread.sleep(3000);
		}
		@Override
		public List<String> call() throws Exception {
			System.out.println("task1 starting...");
			List<String> list = new ArrayList<String>();
			list.add("task1");
			Thread.sleep(2000);
			processTask1(list);
			System.out.println("task1 ending...");
			//任务结束，计数器减一
			countDownLatch.countDown();
			return list;
		}
		
	}
	
	public static class Task2 implements Callable<List<String>>{
		private CountDownLatch countDownLatch;
		public Task2(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		private static void processTask2(List<String> list) throws InterruptedException {
			System.out.println("process task2 data...");
			if(list.size() > 0) {
				System.out.println("data="+list.get(0));
			}
			Thread.sleep(5000);
		}
		@Override
		public List<String> call() throws Exception {
			System.out.println("task2 starting...");
			List<String> list = new ArrayList<String>();
			list.add("task2");
			Thread.sleep(2000);
			processTask2(list);
			System.out.println("task2 ending...");
			//任务结束，计数器减一
			countDownLatch.countDown();
			return list;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newCachedThreadPool();
		//需要执行2个线程，初始值为2
		CountDownLatch countDownLatch = new CountDownLatch(2);
		executorService.submit(new Task1(countDownLatch));
		executorService.submit(new Task2(countDownLatch));
		//countDownLatch 计数器为0之前一直等待，直到为0，主线程才继续执行
		countDownLatch.await();
		System.out.println("task ending...");
		long time = System.currentTimeMillis() - start;
		System.out.println("执行任务所花的时间：" + time + "s");
		executorService.shutdown();
	}
}
