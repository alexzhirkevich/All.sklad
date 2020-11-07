import java.io.Serializable;

public class MyObj implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int val;
	public MyObj(int val){
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
