import java.io.*;
import java.net.Socket;

public class Client {

	public static void main(String[] args)throws Exception {
		Socket socket = new Socket("localhost",8888);
		socket.setSoTimeout(500);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		for (int i =0;i<10;i++){
			oos.writeObject(new MyObj(i));
			Thread.sleep(1000);
		}
	}

}
