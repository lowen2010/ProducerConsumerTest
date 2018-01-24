package test3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 利用future 测试主线程等待子线程执行任务
 * 每个子线程可以处理不同的业务逻辑，也可以连接不同数据库
 * @author lowen
 *
 */
public class FutureTest {
	public static class Task1 implements Callable<List<Object>>{
		@Override
		public List<Object> call() throws Exception {
			System.out.println("task1 starting...");
			List<Object> list = new ArrayList<Object>();
			list.add("task1");
			Thread.sleep(2000);
			System.out.println("task1 ending...");
			return list;
		}
	}
	public static class Task2 implements Callable<List<Object>>{
		@Override
		public List<Object> call() throws Exception {
			System.out.println("task2 starting...");
			List<Object> list = new ArrayList<Object>();
			list.add("task2");
			Thread.sleep(2000);
			System.out.println("task2 ending...");
			return list;
		}
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<List<Object>> future1 = executorService.submit(new Task1());
		Future<List<Object>> future2 = executorService.submit(new Task2());
		List<Object> list1 = future1.get();
		List<Object> list2 = future2.get();
		processTask1(list1);
		processTask2(list2);
		Long time = System.currentTimeMillis() - start;
		System.out.println("执行任务所花的时间：" + time + "s");
		executorService.shutdown();
	}
	
	private static void processTask1(List<Object> list) throws InterruptedException {
		System.out.println("process task1 data...");
		if(list.size() > 0) {
			System.out.println("data="+list.get(0));
		}
		Thread.sleep(1000);
	}
	
	private static void processTask2(List<Object> list) throws InterruptedException {
		System.out.println("process task2 data...");
		if(list.size() > 0) {
			System.out.println("data="+list.get(0));
		}
		Thread.sleep(1000);
	}
}
