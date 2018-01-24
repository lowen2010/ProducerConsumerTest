package test3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用CountDownLatch 辅助类计数器判断子线程的执行情况，达到主线程等待子线程任务结束继续执行的目的
 * @author lowen
 *
 */
public class CyclicBarrierTest {
	public static class Task1 implements Callable<List<String>>{
		private CyclicBarrier cyclicBarrier;
		public Task1(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
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
			//告诉主线程到达围栏，也就是代表当前线程任务完成
			cyclicBarrier.await();
			return list;
		}
		
	}
	
	public static class Task2 implements Callable<List<String>>{
		private CyclicBarrier cyclicBarrier;
		public Task2(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
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
			//告诉主线程到达围栏，也就是代表当前线程任务完成
			cyclicBarrier.await();
			return list;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException{
		Long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newCachedThreadPool();
		//需要执行3个线程，2个子线程和1个主线程，所以为3
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		executorService.submit(new Task1(cyclicBarrier));
		executorService.submit(new Task2(cyclicBarrier));
		//判断到达围栏的线程个数是否为3，如果是继续执行，否则等待
		cyclicBarrier.await();
		System.out.println("task ending...");
		long time = System.currentTimeMillis() - start;
		System.out.println("执行任务所花的时间：" + time + "s");
		executorService.shutdown();
	}
}
