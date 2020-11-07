package parking.index;

import parking.buffer.Buffer;
import parking.buffer.ParkingException;
import parking.buffer.ParkingIO;
import parking.lang.Note;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Index implements Serializable, Closeable {

	private static final long serialVersionUID = 1L;

	private UniqueIndex numbers;
	private MultiIndex names;
	private MultiIndex dates;

	public UniqueIndex getNumbers() {
		return new UniqueIndex(numbers);
	}

	public MultiIndex getNames() {
		return new MultiIndex(names);
	}

	public MultiIndex getDates() {
		return new MultiIndex(dates);
	}

	public Index()  {
		numbers   = new UniqueIndex();
		dates = new MultiIndex();
		names 	= new MultiIndex();
	}

	public static long[] InsertValue( long[] arr, long value ) throws  NullPointerException{
		if (arr == null)
			throw new NullPointerException("Index.InsertValue : arr is not initialized");
		long [] result = new long[arr.length + 1];
		System.arraycopy(arr, 0, result, 0, arr.length);
		result[arr.length] = value;
		return result;
	}

	public void test( Note note ) throws ParkingException {
		assert( note != null );
		if ( numbers.contains(note.getCarNumber())) {
			throw new ParkingException(ParkingIO.ParkingResult.KeyNotUnique, note.getCarNumber() );
		}
	}

	public void put( Note note, long value ) throws ParkingException {
		test( note );
		numbers.put( note.getCarNumber(), value );
		names.put( note.getCarOwner(), value);
		dates.put( note.getStart(), value);
	}

	public static Index load(String name )
			throws IOException, ClassNotFoundException {
		Index obj;
		try {
			FileInputStream file = new FileInputStream( name );
			try( ZipInputStream zis = new ZipInputStream( file )) {
				ZipEntry zen = zis.getNextEntry();
				if (zen == null || !zen.getName().equals(Buffer.getZipEntryName())) {
					throw new IOException("Invalid block format");
				}
				try ( ObjectInputStream ois = new ObjectInputStream( zis )) {
					obj = (Index) ois.readObject();
				}
			}
		} catch ( FileNotFoundException e ) {
			obj = new Index();
		}
		if ( obj != null ) {
			obj.save( name );
		}
		return obj;
	}

	private transient String filename = null;

	public void save( String name ) {
		filename = name;
	}

	public void saveAs( String name ) throws IOException {
		FileOutputStream file = new FileOutputStream( name );
		try ( ZipOutputStream zos = new ZipOutputStream( file )) {
			zos.putNextEntry( new ZipEntry( Buffer.getZipEntryName() ));
			zos.setLevel( ZipOutputStream.DEFLATED );
			try ( ObjectOutputStream oos = new ObjectOutputStream( zos )) {
				oos.writeObject( this );
				oos.flush();
				zos.closeEntry();
				zos.flush();
			}
		}
	}

	public void close() throws IOException {
		saveAs( filename );
	}
}
