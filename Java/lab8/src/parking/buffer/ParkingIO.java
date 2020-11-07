package parking.buffer;

import parking.index.*;
import parking.lang.*;
import java.io.*;
import java.util.*;
import java.text.ParseException;

public class ParkingIO {

	public enum ParkingResult{ OK, KeyNotFound,KeyNotUnique, InvalidNumberOfArguments,InvalidIndexSpecified}

	static final String fileName = "parking.dat";
	static final String fileNameBak = "parking.~dat";
	static final String idxName = "parking.idx";
	static final String idxNameBak = "parking.~idx";

	private static String encoding = "Cp866";
	public static void appendFile(String[] args, boolean zipped) throws  IOException, ClassNotFoundException,
			ParkingException, ParseException {
		if (args.length >= 2) {
			FileInputStream stdin = new FileInputStream( args[1] );
			System.setIn( stdin );
			if (args.length == 3) {
				encoding = args[2];
			}
		}

		Scanner fin = new Scanner(System.in, encoding);

		try(Index idx = Index.load(idxName);
			RandomAccessFile raf = new RandomAccessFile(fileName,"rw")) {
			Note note;
			while ((note = Note.readNote(fin))!=null) {
				idx.test(note);
				long pos = Buffer.writeObject(raf,new BufferObject(note,zipped));
				idx.put(note,pos);
				idx.saveAs(idxName);
			}
		}
	}

	public static void printFile(PrintStream ps) throws IOException, ClassNotFoundException {

		long pos;
		int rec=0;

		try ( RandomAccessFile raf = new RandomAccessFile( fileName, "rw" )) {
			while (( pos = raf.getFilePointer()) < raf.length() ) {
				ps.print("#" + (++rec));
				printRecord(ps,raf,pos);
			}
			ps.flush();
		}
	}

	private static void printRecord(PrintStream ps,RandomAccessFile raf, long pos) throws
			ClassNotFoundException, IOException{
		BufferObject bo = Buffer.readObject(raf, pos);
		if (bo.isZipped()){
			ps.print(" compressed");
		}
		ps.println( " record at position "+ pos + ": \n" + bo.getObject() );
	}

	public static void printRecord(PrintStream ps, RandomAccessFile raf, String key, IndexBase pidx ) throws
			ClassNotFoundException, IOException {
		long[] poss = pidx.get(key);
		for ( long pos : poss ) {
			ps.print( "*** Key: " +  key + " points to" );
			printRecord(ps, raf, pos );
		}
	}

	public static ParkingResult printFile(PrintStream ps,String[] args, boolean reverse)
			throws ClassNotFoundException, IOException {
		if ( args.length != 2 ) {
			return ParkingResult.InvalidNumberOfArguments;
		}
		try (Index idx = Index.load(idxName);
			 RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
			IndexBase pidx = indexByArg(args[1], idx);
			if (pidx == null) {
				return ParkingResult.InvalidIndexSpecified;
			}
			String[] keys = pidx.getKeys(reverse ? new KeyCompReverse() : new KeyComp());
			for (String key : keys) {
				printRecord(ps, raf, key, pidx);
			}
		}
		return ParkingResult.OK;
	}

	public static ParkingResult findByKey(PrintStream ps, String[] args )
			throws ClassNotFoundException, IOException {
		if ( args.length != 3 ) {
			return ParkingResult.InvalidNumberOfArguments;
		}
		try (Index idx = Index.load(idxName);
			 RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
			IndexBase pidx = indexByArg(args[1], idx);
			if (pidx == null){
				return ParkingResult.InvalidIndexSpecified;
			}
			if (!pidx.contains(args[2])) {
				return ParkingResult.KeyNotFound;
			}
			printRecord(ps, raf, args[2], pidx );
		}
		return ParkingResult.OK;
	}

	public static ParkingResult findByKey(PrintStream ps, String[] args, Comparator<String> comp)
			throws ClassNotFoundException, IOException {
		if (args.length != 3) {
			return ParkingResult.InvalidNumberOfArguments;
		}
		try (Index idx = Index.load(idxName);
			 RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
			IndexBase pidx = indexByArg(args[1], idx);
			if (pidx == null){
				return ParkingResult.InvalidIndexSpecified;
			}
			if (!pidx.contains(args[2])) {
				return ParkingResult.KeyNotFound;
			}
			String key;
			String[] keys = pidx.getKeys( comp );
			for (String k : keys) {
				if ((key = k).equals(args[2])) {
					break;
				}
				printRecord(ps,raf, key, pidx);
			}
		}
		return ParkingResult.OK;
	}

	private static IndexBase indexByArg(String arg, Index idx)  {

		IndexBase pidx;

		switch (arg) {
			case "number":
				pidx = idx.getNumbers();
				break;
			case "name":
				pidx = idx.getNames();
				break;
			case "date":
				pidx = idx.getDates();
				break;
			default:
				pidx=null;
				break;
		}
		return pidx;
	}

	public static boolean deleteFile() {
		deleteBackup();
		return (new File(fileName).delete() & new File(idxName).delete());
	}

	public static ParkingResult deleteFile( String[] args )
			throws ClassNotFoundException, IOException, ParkingException {

		if ( args.length != 3 ) {
			return ParkingResult.InvalidNumberOfArguments;
		}
		long[] poss;
		try ( Index idx = Index.load(idxName)) {
			IndexBase pidx = indexByArg( args[1], idx );
			if (pidx == null) {
				return ParkingResult.InvalidIndexSpecified;
			}
			if (!pidx.contains(args[2])) {
				return ParkingResult.KeyNotFound;
			}
			poss = pidx.get(args[2]);
		}
		backup();
		Arrays.sort(poss);
		try ( Index idx = Index.load(idxName);
			  RandomAccessFile fileBak= new RandomAccessFile(fileNameBak, "rw");
			  RandomAccessFile file = new RandomAccessFile( fileName, "rw")) {
			long pos;
			while (( pos = fileBak.getFilePointer()) < fileBak.length() ) {
				BufferObject bo = Buffer.readObject(fileBak, pos);
				if (Arrays.binarySearch(poss, pos) < 0 ) {
					long ptr = Buffer.writeObject(file, bo);
					idx.put((Note)bo.getObject(), ptr);
				}
			}
		}
		return ParkingResult.OK;
	}

	private static boolean deleteBackup() {
		return(new File(fileNameBak).delete() & new File(idxNameBak).delete());
	}

	private static boolean backup() {
		return (deleteBackup() &
				new File(fileName).renameTo(new File(fileNameBak)) &
				new File(idxName).renameTo(new File(idxNameBak)));
	}

}
