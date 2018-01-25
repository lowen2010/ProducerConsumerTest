package test3;

/**
 * 利用Thread join 方法实现线程的等待
 * @author lowen
 *
 */
public class JoinTest {
	private static class Task1 extends Thread{
		@Override
		public void run() {
			System.out.println("Task1 starting...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task1 eding...");
		}
		
	}
	
	private static class Task2 extends Thread{
		@Override
		public void run() {
			System.out.println("Task2 starting...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task2 eding...");
		}
	}
	
	/**
	 * 主线程等待task1、task2执行完才继续执行
	 * @param args
	 */
	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		Task1 task1 = new Task1();
		Task2 task2 = new Task2();
		task1.start();
		task2.start();
		try {
			task1.join();
			task2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("执行任务所花的时间：" + time + "s");
	}
}
