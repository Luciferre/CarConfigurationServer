package server;

import java.awt.SecondaryLoop;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Automobile;

public class DefaultSocketClient extends Thread implements SocketClientConstants, SocketClientInterface {

	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	private Socket socket;
	private BuildCarModelOptions buildCarModelOptions;

	public DefaultSocketClient(Socket socket) {
		this.socket = socket;
		this.buildCarModelOptions = new BuildCarModelOptions();
	}// constructor

	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}// run

	// open connection
	public boolean openConnection() {

		try {
			// writer should be initialized before reader
			writer = new ObjectOutputStream(socket.getOutputStream());
			writer.flush();
			reader = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			if (DEBUG)
				System.err.println("Unable to obtain stream to/from " + socket.getInetAddress());
			return false;
		}
		return true;
	}

	// handle interaction between client
	public void handleSession() {
		Object object;
		if (DEBUG)
			System.out.println("Handling session with " + socket.getInetAddress() + ":" + socket.getPort());
		try {
			while ((object = reader.readObject()) != null) {
				if (!handleInput(object))
					break;
			}
		} catch (IOException | ClassNotFoundException e) {
			if (DEBUG)
				System.out.println("Handling session with " + socket.getInetAddress() + ":" + socket.getPort());
		}
	}

	// handle input from client
	public boolean handleInput(Object object) {
		String command = (String) object;
		switch (command) {
		case "1":// receive uploaded properties file from client
			handleUpload(false);
			break;

		// case "2":// upload text file
		// handleUpload(true);
		// break;

		case "2":// send auto list to client
			sendOutput((Object) buildCarModelOptions.getAutoList());
			break;

		case "3":// send selected automobile object to client
			handleGetAuto();
			break;

		case "5":// close session
			return false;

		default:
			break;
		}
		return true;
	}

	// receive uploaded properties file from client
	public void handleUpload(boolean fileType) {
		String reply;
		Object object = getInput();
		boolean ifUpload = buildCarModelOptions.buildAutoFromFiles(object, false);
		if (ifUpload) {
			reply = "Uploading succeeded!";
			sendOutput((Object) reply);
		} else {
			reply = "Uploading failed!";
			sendOutput((Object) reply);
		}

	}

	// send selected automobile object to client
	public void handleGetAuto() {
		String autoName = (String) getInput();
		Automobile automobile = buildCarModelOptions.getSelectedAuto(autoName);
		sendOutput(automobile);

	}

	// get object from socket inputstream
	public Object getInput() {
		try {
			return reader.readObject();
		} catch (IOException | ClassNotFoundException e) {
			if (DEBUG)
				System.out.println("Error writing to " + socket.getInetAddress());
		}
		return null;
	}

	// send object to socket ouputstream
	public void sendOutput(Object object) {
		try {
			writer.writeObject(object);
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Error writing to " + socket.getInetAddress());
		}
	}

	// close session
	public void closeSession() {
		try {
			writer = null;
			reader = null;
			socket.close();
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Error closing socket to " + socket.getInetAddress());
		}
	}

}
