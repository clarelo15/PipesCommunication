import java.io.*;

public class Connection {
	private static PipedOutputStream posAtoC;
	private static PipedInputStream pisAtoC;
	private static PipedOutputStream posBtoA;
	private static PipedInputStream pisBtoA;
	private static PipedOutputStream posBtoC;
	private static PipedInputStream pisBtoC;
	private static PipedOutputStream posCtoB;
	private static PipedInputStream pisCtoB;
	public static void main(String[] args) {
		//Pipe setup
		posAtoC = new PipedOutputStream();     //TA sends primitive data to TC
		posBtoA = new PipedOutputStream();     //TB will send objects to TA
		posBtoC = new PipedOutputStream();     //TB will send primitive data to TC
		posCtoB = new PipedOutputStream();     //TC will send objects data to TB
		try {
			pisAtoC = new PipedInputStream(posAtoC);
			pisBtoA = new PipedInputStream(posBtoA);
			pisBtoC = new PipedInputStream(posBtoC);
			pisCtoB = new PipedInputStream(posCtoB);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Object creation
		TA a = new TA(posAtoC, pisBtoA);
		TB b = new TB(posBtoA, posBtoC, pisCtoB);
		TC c = new TC(pisAtoC, pisBtoC, posCtoB);
		
		//Thread execution
		a.start();
		b.start();
		c.start();

	}

}
