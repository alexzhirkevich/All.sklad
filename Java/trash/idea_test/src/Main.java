import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	static String sHex = "0123456789ABCDEF";
	static int linenum = 1;
	static String fInt = "integer10.txt";
	static String fHex = "integer16.txt";
	static String fDouble = "double.txt";
	static String fE = "doubleE.txt";
	static String fText = "text.txt";

	static HashMap<Integer,Double> doubles = new HashMap<>();
	static HashMap<Integer,Double> exp = new HashMap<>();
	static HashMap<Integer,Integer> ints = new HashMap<>();
	static HashMap<Integer,Integer> hex = new HashMap<>();
	static HashMap<Integer,String> strings = new HashMap<>();

	public static void solve(String[] args){
		Scanner sc = new Scanner(System.in);
		int line = 0;
		while (sc.hasNextLine()) {
			String s = "{}";
			line++;
			String[] tokens = sc.nextLine().split("\t\n ");
			for (String word : tokens) {
				try {
					Double value = Double.parseDouble(word);
					if (!word.contains(".") && !word.contains("e") && !word.contains("E"))
						throw new NumberFormatException();
					if (word.contains("e") || word.contains("E"))
						exp.put(line, value);
					else
						doubles.put(line, value);
				} catch (NumberFormatException e) {
					try {
						Integer value = Integer.parseInt(word);
						ints.put(line, value);
					} catch (NumberFormatException ee) {
						try {
							if (word.length() > 2) {
								Integer value = Integer.parseInt(word.substring(2), 16);
								hex.put(line, Integer.parseInt(word.substring(2), 16));
							} else
								throw new NumberFormatException();
						} catch (NumberFormatException eee) {
							strings.put(line, word);
						}
					}
				}
			}
			System.out.print("1: ");
			for (Integer d : ints.values())
				System.out.print(d + " ");
			System.out.println();
			System.out.print("2: ");
			for (Integer d : hex.values())
				System.out.printf("%X ", d);
			System.out.println();

			System.out.print("3: ");
			for (Double d : doubles.values())
				System.out.print(d + " ");
			System.out.println();

			System.out.print("4: ");
			for (Double d : exp.values())
				System.out.printf("%E ", d);
			System.out.println();
			System.out.print("5: ");
			for (String d : strings.values())
				System.out.print(d + " ");
			System.out.println();
		}
	}

	public static String convert(String value, String fromEncoding, String toEncoding) throws UnsupportedEncodingException {
		return new String(value.getBytes(fromEncoding), toEncoding);
	}

	public static String charset(String value, String charsets[]) throws UnsupportedEncodingException {
		String probe = StandardCharsets.UTF_8.name();
		for(String c : charsets) {
			Charset charset = Charset.forName(c);
			if(charset != null) {
				if(value.equals(convert(convert(value, charset.name(), probe), probe, charset.name()))) {
					return c;
				}
			}
		}
		return StandardCharsets.UTF_8.name();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String[] cs = {
				"UTF-8", "windows-1251", "Cp866"
		};
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		System.out.print(charset(sc.nextLine(),cs));
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
