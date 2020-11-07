package parking.buffer;

public class ParkingException extends Exception {
	public ParkingException(String msg){
		super(msg);
	}

	public ParkingException(ParkingIO.ParkingResult pr){
		super(pr.toString());
	}
	public ParkingException(ParkingIO.ParkingResult pr, String msg){
		super(pr.toString() + ((msg!=null)?" : "+ msg:""));
	}
}
