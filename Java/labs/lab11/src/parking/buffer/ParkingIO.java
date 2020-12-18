package parking.buffer;

import parking.index.*;
import parking.lang.*;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.text.ParseException;

public class ParkingIO {

	public enum ParkingResult {OK, KeyNotFound, KeyNotUnique, InvalidNumberOfArguments, InvalidIndexSpecified}

	private static String fileName = "parking.dat";
	static String fileNameBak = "parking.~dat";
	static String idxName = "parking.idx";
	static String idxNameBak = "parking.~idx";

	private static String encoding = "Cp866";

	public static void appendFile(BufferObject bo) throws IOException, ClassNotFoundException,
			ParkingException {
		try (Index idx = Index.load(idxName);
			 RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
			if (!new File(fileName).exists())
				new File(fileName).createNewFile();
			idx.test((Note)bo.getObject());
			long pos = Buffer.writeObject(raf, bo);
			idx.put((Note) bo.getObject(), pos);
			idx.saveAs(idxName);
		}
	}

	public static void printFile(JTextArea ps) throws IOException, ClassNotFoundException {

		long pos;
		try ( RandomAccessFile raf = new RandomAccessFile( fileName, "rw" )) {
			while (( pos = raf.getFilePointer()) < raf.length() ) {
				printRecord(ps,raf,pos);
			}
		}
	}

	private static void printRecord(JTextArea ps,RandomAccessFile raf, long pos) throws
			ClassNotFoundException, IOException{
		BufferObject bo = Buffer.readObject(raf, pos);
		ps.setText( ps.getText() + bo.getObject() + "\n" );
	}

	public static void printRecord(JTextArea ps, RandomAccessFile raf, String key, IndexBase pidx ) throws
			ClassNotFoundException, IOException {
		Vector<Long> poss = pidx.get(key);
		for ( Long pos : poss ) {

			printRecord(ps, raf, pos );
		}
	}

	public static ParkingResult printFile(JTextArea ps,String key, boolean reverse)
			throws ClassNotFoundException, IOException {

		try (Index idx = Index.load(idxName);
			 RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
			IndexBase pidx = indexByArg(key, idx);
			if (pidx == null) {
				return ParkingResult.InvalidIndexSpecified;
			}
			TreeSet<String> keys = pidx.getKeys(reverse ? new KeyCompReverse() : new KeyComp());
			for (String k : keys) {
				printRecord(ps, raf, k, pidx);
			}
		}
		return ParkingResult.OK;
	}

	public static ParkingResult findByKey(JTextArea ps, String[] args )
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

	public static ParkingResult findByKey(JTextArea ps, String[] args, Comparator<String> comp)
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
			TreeSet<String> keys = pidx.getKeys( comp );
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

	public static ParkingResult deleteFile( String key,String value )
			throws ClassNotFoundException, IOException, ParkingException {

		Vector<Long> poss;
		try ( Index idx = Index.load(idxName)) {
			IndexBase pidx = indexByArg( key, idx );
			if (pidx == null) {
				return ParkingResult.InvalidIndexSpecified;
			}
			if (!pidx.contains(value)) {
				return ParkingResult.KeyNotFound;
			}
			poss = pidx.get(value);
		}
		backup();
		Collections.sort(poss);
		try ( Index idx = Index.load(idxName);
			  RandomAccessFile fileBak= new RandomAccessFile(fileNameBak, "rw");
			  RandomAccessFile file = new RandomAccessFile( fileName, "rw")) {
			long pos;
			while (( pos = fileBak.getFilePointer()) < fileBak.length() ) {
				BufferObject bo = Buffer.readObject(fileBak, pos);
				if (!poss.contains(pos)) {
					Long ptr = Buffer.writeObject(file, bo);
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

	public static void setPath(String path){
		String folder = path.substring(0,path.lastIndexOf(File.separatorChar)+1);
		fileName = path;
		idxName = folder + "cache" + File.separator + "index.idx";
		fileNameBak = folder + "cache" + File.separator + " ~bak.dat";
		idxNameBak = folder + "cache" + File.separator + "~index.idx";
		new File(folder + "cache").mkdirs();
	}

}
