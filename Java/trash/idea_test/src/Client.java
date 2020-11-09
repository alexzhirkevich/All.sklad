import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

class A{
	public int a = 0;
}

public class Client {

	public static void main(String[] args)throws Exception {
		ArrayList<A> a = new ArrayList<>();
		a.add(new A());
		for (A el: a) {
			el.a = 1;
		}

		System.out.println(a.get(0).a);
	}

}
