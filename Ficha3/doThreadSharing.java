class SharedObj {
	private int number;
	private String name;

	public String getName() {return name;}
	public int getNumber() {return number;}

	public void setName(String n) {name=n;}
	public void setNumber(int x) {number=x;}
}

class Run extends Thread{
	public SharedObj share;	

	public Run (SharedObj s) {share=s;}

    public void run() {
		String myname=Thread.currentThread().getName();
		try{
			while(true){
				if (Thread.interrupted()) return;
				Thread.sleep(1000);
				System.out.println("["+myname+"] Number:"+share.getNumber()+"("+share.getName()+")");
				} 	
			} catch (InterruptedException e) {} 
		}
		
    public void setShare(SharedObj s){share=s;}
}

class Run2 extends Thread {
	public SharedObj share;
	public int n;	
	public Run2 (SharedObj s, int i) {share=s;n=i;}
    public void run() {
		String myname=Thread.currentThread().getName();
		try{
		share.setNumber(n);
		Thread.sleep(n*1000);
		//share.setNumber(n);
		System.out.println("["+myname+"] Number:"+share.getNumber());
		share.setName("share.name definido por: "+myname);
		} catch (InterruptedException e) {}
    }
    public void setShare(SharedObj s){share=s;}
}

public class doThreadSharing {
		
    public static void main(String args[]) {
		SharedObj share = new SharedObj();
		Thread tarefa = new Thread(new Run(share),"Printer_thread");
		tarefa.start();		
		for(int i=0; i<10; i++){
			Thread tarefa2 = new Thread(new Run2(share,i));
			tarefa2.setName("Numbered_thread_"+i);
			tarefa2.start();
			}
		try{
		Thread.sleep(10000);
		tarefa.interrupt();
		} catch (InterruptedException e) {}
	}
}
