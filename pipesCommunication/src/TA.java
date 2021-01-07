import java.io.*;

public class TA extends Thread{
	private PipedInputStream isBtoA;
	private PipedOutputStream osAtoC;
	private DataOutputStream os;
	private ObjectInputStream is;
	public TA(PipedOutputStream osAtoC, PipedInputStream isBtoA) {
		this.isBtoA = isBtoA;
		this.osAtoC = osAtoC;
	}
	public void run() {
		sendPrimitiveToC();
		receiveObjectFromB();
	}
	public void sendPrimitiveToC() {
		os = new DataOutputStream(osAtoC);      //let primitive data be transmitted across the pipe
		int data = (int)(Math.random()*100);
		try {
			os.write(data);
			System.out.println("TA sends primitive data to TC, and the primitive data is "+ data);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void receiveObjectFromB() {
		try {
			is = new ObjectInputStream(isBtoA);
			Object oj = is.readObject();
			System.out.println("TA receives objects data from TB, and the object data is "+ oj);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
