package connector;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import airport.*;

public class Connector {

	private final String fileName;

	public Connector(String fileName) {
		this.fileName = fileName;
	}

	public void write(Airport[] airports) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeInt(airports.length);
			for (int i = 0; i < airports.length; i++) {
				oos.writeObject(airports[i]);
			}
			oos.flush();
		}
	}

	public Airport[] read() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(fileName);
		try (ObjectInputStream oin = new ObjectInputStream(fis)) {
			int length = oin.readInt();
			Airport[] airports = new Airport[length];
			for (int i = 0; i < length; i++) {
				airports[i] = (Airport) oin.readObject();
			}
			return airports;
		}
	}
}
