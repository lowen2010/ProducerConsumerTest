package test1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁存取数据
 * @author lowen
 *
 */
public class ReetrantData implements LockData{
	private int index=0;
	private int prodIndex=0;
	private String[] dataArray = new String[5];
	private ReentrantLock lock = new ReentrantLock();
	private Condition condtion = lock.newCondition();
	
	public void push() {
		lock.lock();
		try {
			if(index < dataArray.length) {
				String prodName="产品"+prodIndex;
				dataArray[index]=prodName;
				System.out.println("【"+Thread.currentThread().getName()+"】"+"生产："+prodName);
				index++;
				prodIndex++;
				condtion.signalAll();;
			}else {
				System.out.println("【"+Thread.currentThread().getName()+"】"+"产品已经饱和,暂停生产");
				condtion.await();;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void take() {
		lock.lock();
		try {
			if(index > 0) {
				index--;
				String dataStr = dataArray[index];
				dataArray[index]="";
				System.out.println("【"+Thread.currentThread().getName()+"】"+"消耗："+dataStr);
				condtion.signalAll();
			}else {
				System.out.println("【"+Thread.currentThread().getName()+"】"+"产品消耗完毕");
				condtion.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
