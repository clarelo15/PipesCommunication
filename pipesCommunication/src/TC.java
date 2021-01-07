import java.io.*;

public class TC extends Thread{
	private PipedInputStream isAtoC;
	private PipedInputStream isBtoC;
	private PipedOutputStream osCtoB;
	private DataInputStream is;
	private DataInputStream is2;
	private ObjectOutputStream os;
	int number = 1;
	int id = 1;
	public TC(PipedInputStream isAtoC, PipedInputStream isBtoC, PipedOutputStream osCtoB) {
		this.isAtoC = isAtoC;
		this.isBtoC = isBtoC;
		this.osCtoB = osCtoB;
	}
	public void run() {
		receivePrimitiveFromA();
		receivePrimitiveFromB();
		sendObjectToB();
	}
	public void receivePrimitiveFromA() {
		is = new DataInputStream(isAtoC);
		try {
			int data = is.read();
			System.out.println("TC receives primitive data from TA, and the data is "+ data);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void receivePrimitiveFromB() {
		is2 = new DataInputStream(isBtoC);
		try {
			int data = is2.read();
			System.out.println("TC receives primitive data from TB, and the data is "+data);
			is2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendObjectToB() {
		try {
			os = new ObjectOutputStream(osCtoB);     //let object be transmitted across the pipe
			Message m = new Message(number,id);
			os.writeObject(m);
			System.out.println("TC sends objects data to TB, and the objects data is "+m);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
