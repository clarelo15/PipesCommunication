import java.io.Serializable;

public class Message implements Serializable{
	int number;
	int id;
	public Message(int number, int id) {
		this.number = number;
		this.id = id;
	}
	public String toString() {
		return "number : " +number+" id : "+id;
	}

}
