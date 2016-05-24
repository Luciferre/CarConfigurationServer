package server;

import java.awt.CardLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import adapter.BuildAuto;
import adapter.ICreateAuto;

//Main server driver
public class Server implements SocketClientConstants {

	private ServerSocket serverSocket;

	// Initialize server socket
	public Server() {
		try {
			serverSocket = new ServerSocket(iPORT);
		} catch (IOException socketError) {
			if (DEBUG)
				System.err.println("Unable to connect to " + serverSocket.getInetAddress());
		}
	}

	// run server to wait for client
	public void runServer() {
		DefaultSocketClient defaultSocketClient;
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				defaultSocketClient = new DefaultSocketClient(clientSocket);
				defaultSocketClient.start();
			}
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

	}

	public static void main(String args[]) {
		ICreateAuto createAuto = new BuildAuto();
		createAuto.buildAuto("Car1.txt");
		createAuto.buildAuto("Car2.txt");
		Server server = new Server();
		server.runServer();
	}
}
