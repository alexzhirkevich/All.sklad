/**
 * test
 */
public class test {

    public static void main(String[] args) {
        String str = "qwe";
        CharsetDetector detector = new CharsetDetector();
        detector.setText(str.getBytes());
        detector.detect();
    }
}