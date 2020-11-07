import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	static String hex = "0123456789ABCDEF";
	static int linenum = 1;
	static String fInt = "integer10.txt";
	static String fHex = "integer16.txt";
	static String fDouble = "double.txt";
	static String fE = "doubleE.txt";
	static String fText = "text.txt";


	public static void main(String[] args)throws Exception {
		ServerSocket serv = new ServerSocket(8888);

		MyObj obj = null;
		System.out.println("Server started");
		Socket client = serv.accept();
		System.out.println("Connected");
		client.setSoTimeout(500);
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

		while(true) {
			try {
				obj = (MyObj) ois.readObject();
			} catch (Exception e) { }
			if (obj!= null)
				System.out.println(obj.getVal());
			obj = null;
		}
	}

	public static void solve() throws IOException {
		try (Scanner sc = new Scanner(System.in)) {
			while (sc.hasNextLine()){
				String[] words = sc.nextLine().split(" \n\r");
				for (String word : words){
					try {
						Double d = Double.parseDouble(word);
						PrintWriter pw;
						if (word.indexOf('e')!=-1)
							pw = new PrintWriter(new FileWriter(fE));
						else
							pw= new PrintWriter(fDouble);
						pw.write(linenum);
						pw.write(": "+ d);
						continue;
					}
					catch (NumberFormatException e){ }
					try {
						Integer i = Integer.parseInt(word);
						PrintWriter pw = new PrintWriter(new FileWriter(fDouble));
						pw.write(linenum);
						pw.write(": "+ i);
						continue;
					}
					catch (NumberFormatException e){ }
				}
			}
		}
	}
}
