import java.io.*;

public class TB extends Thread{
	private PipedOutputStream osBtoA;
	private PipedOutputStream osBtoC;
	private PipedInputStream isCtoB;
	private ObjectOutputStream os;
	private DataOutputStream os2;
	private ObjectInputStream is;
	private int number = 0;
	private int id = 0;
	
	public TB(PipedOutputStream osBtoA, PipedOutputStream osBtoC, PipedInputStream isCtoB) {
		this.osBtoA = osBtoA;
		this.osBtoC = osBtoC;
		this.isCtoB = isCtoB;
	}
	public void run() {
		sendObjectToA();
		sendPrimitiveToC();
		receiveObjectFromC();
	}
	public void sendObjectToA() {
		try {
			os = new ObjectOutputStream(osBtoA);    //let object be transmitted across the pipe
			Message m = new Message(number, id);
			os.writeObject(m);
			System.out.println("TB sends objects data to TA, and the objects data is "+ m);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendPrimitiveToC() {
		os2 = new DataOutputStream(osBtoC);        //let primitive data be transmitted across the pipe
		int data = (int)(Math.random()*100);
		try {
			os2.write(data);
			System.out.println("TB sends primitive data to TC, and the primitive data is "+data);
			os2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void receiveObjectFromC() {
		try {
			is = new ObjectInputStream(isCtoB);
			Object oj = is.readObject();
			System.out.println("TB receives objects data from TC, and the object data is "+oj);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
