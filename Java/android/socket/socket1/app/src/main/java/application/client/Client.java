package application.client;
import android.net.InetAddresses;
import android.os.StrictMode;
import application.message.*;
import application.message.connection.*;
import application.protocol.Config;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Enumeration;

public class Client extends Thread {

	public static final String sLostConnection = "Потеряно соединение с сервером";
	public static final String sUnknownCommand = "Неизвестная команда";
	public static final String sResponseError = "Не удалось получить ответ от сервера";
	public static final String sConnectionFailed = "Не удалось подключиться к серверу";
	public static final String sMenu = "Меню:";
	public static final String sOrderNumber = "Номер вашего заказа: ";
	public static final String sInputAddress = "Введите адресс доставки:";
	public static final String sInputOrder = "Выберите блюда из списка:";
	public static final String sHaveANiceDay = "Удачного дня";
	public static final String sExit = "<Press ENTER to exit>";
	public static final String sHelp =
					"_____________________\n" +
					"| 1. Меню и цены    |\n" +
					"| 2. Сделать заказ  |\n" +
					"| 3. Выйти          |\n" +
					"|___________________|";

	private final Socket clientSocket;
	private final ObjectInputStream ois;
	private final ObjectOutputStream oos;
	private boolean connected = false;

	public Client(String host, int port) throws IOException {
		setThreadPolicy();
		clientSocket = new Socket();

		clientSocket.connect(new InetSocketAddress(host, port), 5000);
		if (clientSocket == null)
			throw new SocketTimeoutException("Host is unavailable");
		clientSocket.setSoTimeout(5000);
		oos = new ObjectOutputStream(this.clientSocket.getOutputStream());
		ois = new ObjectInputStream(this.clientSocket.getInputStream());
	}

	public MessageConnectResult connect() throws MessageException, IOException, ClassNotFoundException{
		if (connected)
			return null;
		MessageConnectResult mcr = (MessageConnectResult)sendMessage(new MessageConnect());
		if (!mcr.checkError())
			connected = true;
		return mcr;
	}

	public boolean isConnected() {
		return connected;
	}

	private void setThreadPolicy() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	public MessageResult sendMessage(Message msg) throws IOException,ClassNotFoundException {
		setThreadPolicy();
		oos.writeObject(msg);
		return (MessageResult) ois.readObject();
	}

	private void disconnect() throws IOException {
		connected = false;
		ois.close();
		oos.close();
		clientSocket.close();
	}

	@Override
	public void interrupt() {
		try {
			disconnect();
		}
		catch (Throwable e){ }
	}

	@Override
	protected void finalize() throws Throwable {
		disconnect();
	}
}
