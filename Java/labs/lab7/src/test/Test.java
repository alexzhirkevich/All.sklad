package test;

import buffer.Buffer;
import parking.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Test {

	private static final String fileName = "parking.dat";
	private static final SimpleDateFormat sdf = new SimpleDateFormat();
	private static final Scanner sc = new Scanner( System.in );

	public static void main(String[] args){
		try {
			if ( args.length >= 1 ) {
				if ( args[0].compareTo( "-a" )== 0 ) {
					append_file();
				}
				else if ( args[0].compareTo( "-p" )== 0 ) {
					print_file();
				}
				else if ( args[0].compareTo( "-d" )== 0 ) {
					delete_file();
				}
				else {
					System.err.println( "Option is not realised: " + args[0] );
					System.exit(1);
				}
			}
			else {
				System.err.println( "Notes: Nothing to do!" );
			}
		}
		catch ( Exception e ) {
			System.err.println( "Run/time error: " + e );
			System.exit(1);
		}
		System.out.println( "Notes finished..." );
		System.exit(0);
	}

	public static Note readNote(Scanner sc) throws ParseException {
		try {
			Note n = new Note();
			System.out.print("Car number: ");
			if (!sc.hasNextLine())
				return null;
			n.setCarNumber(sc.nextLine());
			System.out.print("Car owner: ");
			if (!sc.hasNextLine())
				return null;
			n.setCarOwner(sc.nextLine());
			System.out.print("Parking start: ");
			if (!sc.hasNextLine())
				return null;
			n.setStart(sdf.parse(sc.nextLine()));
			System.out.print("Parking end: ");
			if (!sc.hasNextLine())
				return null;
			n.setEnd(sdf.parse(sc.nextLine()));
			System.out.print("Parking price: ");
			if (!sc.hasNextLine())
				return null;
			n.setPrice(Double.parseDouble(sc.nextLine()));
			return n;
		}
		catch (ParseException e){
			throw new ParseException("Invalid date format. Example: 01.01.2020, 12:00",e.getErrorOffset());
		}
	}

	public static void append_file() throws IOException, ParseException {

		try(RandomAccessFile raf = new RandomAccessFile(fileName,"rw")) {
			Note note;
			while ((note = readNote(sc))!=null) {
				Buffer.writeObject(raf,note);
			}
		}

	}
	static void print_file()
			throws IOException, ClassNotFoundException {
		try ( RandomAccessFile raf = new RandomAccessFile( fileName, "rw" )) {
			long pos;
			while (( pos = raf.getFilePointer()) < raf.length() ) {
				Note note = (Note) Buffer.readObject( raf, pos );
				System.out.println( pos + ": " + note );
			}
		}
	}

	static void delete_file() {
		File f = new File(fileName);
		f.delete();
	}
}
