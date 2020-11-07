
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server extends Thread {

	private int PORT = 8888;
	private static final String POST = "POST";
	private static final String GET = "GET";


	public Server(int PORT){
		this.PORT = PORT;
	}

	@Override
	public void run() {
		try (ServerSocket server = new ServerSocket(PORT))  {
			System.out.println(InetAddress.getLocalHost());
			while (true) {
					Socket client = server.accept();
					new Thread( () -> {
						try {
							PrintStream out = new PrintStream(new Buffe(client.getOutputStream());
							DataInputStream in = new DataInputStream(client.getInputStream());

							String method = in.readUTF();
							
							if (method.equals(POST)){
								System.out.println(client.getInetAddress().getHostAddress() + " : " + in.readUTF());
							}
							else if (method.equals(GET)){
								
							}
							else
								System.err.println("Unknown method type:" + method);

							
							out.flush();
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}).start();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitForTerminate() {
		System.out.println("Server started\nCtrl+c to terminate");
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()){
			sc.next();
        }
        sc.close();
	}

	public static void main(String[] args) {
		new Server(8888).start();
		System.out.println("Server started\nCtrl+c to terminate");
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()){
			sc.next();
        }
        sc.close();
	}
}