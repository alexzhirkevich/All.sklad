//package parking.test;
//
//import parking.buffer.ParkingException;
//import parking.buffer.ParkingIO;
//import parking.index.KeyComp;
//import parking.index.KeyCompReverse;
//
//public class Test {
//
//	public static final String helpString = "Syntax:\n" +
//			"\t-a  [file [encoding]] 		- append data\n" +
//			"\t-az [file [encoding]] 		- append data, compress every record\n" +
//			"\t-d                    		- clear all data\n" +
//			"\t-dk  {number|name|date} key	- clear data by key\n" +
//			"\t-p                    		- print data unsorted\n" +
//			"\t-ps  {number|name|date}      - print data sorted by isbn/author/name\n" +
//			"\t-psr {number|name|date}      - print data reverse sorted by isbn/author/name\n" +
//			"\t-f   {number|name|date} key	- find record by key\n"+
//			"\t-fr  {number|name|date} key  - find records > key\n"+
//			"\t-fl  {number|name|date} key  - find records < key\n"+
//			"\t-help                		- command line syntax\n";
//
//	public static void main(String[] args){
//		try {
//			if (args.length >= 1) {
//				switch (args[0]) {
//					case "-help": {
//						System.out.println(helpString);
//						System.exit(0);
//						break;
//					}
//					case "-a": {
//						ParkingIO.appendFile(args, false);
//						break;
//					}
//					case "-az": {
//						ParkingIO.appendFile(args, true);
//						break;
//					}
//					case "-p": {
//						ParkingIO.printFile(System.out);
//						break;
//					}
//					case "-ps": {
//						ParkingIO.ParkingResult res;
//						if ((res = ParkingIO.printFile(System.out, args, false)) != ParkingIO.ParkingResult.OK) {
//							throw new ParkingException(res);
//						}
//						break;
//					}
//					case "-psr": {
//						ParkingIO.ParkingResult res;
//						if ((res = ParkingIO.printFile(System.out, args, true)) != ParkingIO.ParkingResult.OK) {
//							throw new ParkingException(res);
//						}
//						break;
//					}
//					case "-d": {
//						if (args.length != 1) {
//							throw new ParkingException(ParkingIO.ParkingResult.InvalidNumberOfArguments, Integer.toString(args.length));
//						}
//						if (!ParkingIO.deleteFile()){
//							System.out.println("Not found");
//						}
//						else {
//							System.out.println("Deleted");
//						}
//						System.exit(0);
//					}
//					case "-dk": {
//						ParkingIO.ParkingResult res;
//						if ((res = ParkingIO.deleteFile(args))!= ParkingIO.ParkingResult.OK) {
//							throw new ParkingException(res);
//						}
//						System.out.println("Deleted");
//						System.exit(0);
//						break;
//					}
//					case "-f": {
//						ParkingIO.ParkingResult res;
//						if ((res = ParkingIO.findByKey(System.out,args))!= ParkingIO.ParkingResult.OK) {
//							throw new ParkingException(res);
//						}
//						break;
//					}
//					case "-fr": {
//						ParkingIO.ParkingResult res;
//						if ((res =ParkingIO.findByKey(System.out,args, new KeyCompReverse())) != ParkingIO.ParkingResult.OK) {
//							throw new ParkingException(res);
//						}
//						break;
//					}
//					case "-fl": {
//						ParkingIO.ParkingResult res;
//						if ((res = ParkingIO.findByKey(System.out,args, new KeyComp())) != ParkingIO.ParkingResult.OK) {
//							throw new ParkingException(res);
//						}
//						break;
//					}
//					default: {
//						System.err.println(helpString);
//						System.exit(1);
//					}
//				}
//			}
//		}
//		catch (ParkingException pe){
//			System.err.println(pe.getMessage());
//			System.exit(1);
//		}
//		catch (Exception e) {
//			System.err.println("Run/time error: " + e.getMessage());
//			e.printStackTrace();
//			System.exit(1);
//		}
//		System.out.println( "Notes finished..." );
//		System.exit(0);
//	}
//}
