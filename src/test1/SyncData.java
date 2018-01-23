package test1;

/**
 * synchronize存取数据
 * @author lowen
 *
 */
public class SyncData implements LockData{
	private int index=0;
	private int prodIndex=0;
	private String[] dataArray = new String[5];
	
	public void push() {
		synchronized(this) {
			if(index < dataArray.length) {
				String prodName="产品"+prodIndex;
				dataArray[index]=prodName;
				System.out.println("【"+Thread.currentThread().getName()+"】"+"生产："+prodName);
				index++;
				prodIndex++;
				notifyAll();
			}else
				try {
					System.out.println("【"+Thread.currentThread().getName()+"】"+"产品已经饱和,暂停生产");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	public void take() {
		synchronized(this) {
			if(index > 0) {
				index--;
				String dataStr = dataArray[index];
				dataArray[index]="";
				System.out.println("【"+Thread.currentThread().getName()+"】"+"消耗："+dataStr);
				notifyAll();
			}else {
				try {
					System.out.println("【"+Thread.currentThread().getName()+"】"+"产品消耗完毕");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
