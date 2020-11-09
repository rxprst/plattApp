package edu.cscc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * TinyWS a simplistic Tiny Web Server
 * @author student name
 */
public class TinyWS {

    private static int port;
    private static String defaultFolder;
    private static String defaultPage;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private RequestHandler requestHandler = new RequestHandler(clientSocket);

    /**
     * Main routine - instantiate tiny web server, start listening for browser requests
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        TinyWS tiny = new TinyWS();
        tiny.listen();

    }

    /**
     * Constructor - read and set configuration
     * @throws IOException 
     */
    public TinyWS() throws IOException {
        Config config = new Config();
       // TODO code here
        config.dumpProperties();
    }

    /**
     * Listen on server socket
     */
    public void listen() {
    	InetAddress ip = null;
		try {
			ip = InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	try {
			serverSocket = new ServerSocket(80, 10);
			   System.out.println("Server is listening on port " + 80);
			   
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("New Client Connected");
				System.out.println(ip.getCanonicalHostName());
				requestHandler.processRequest();
				
				InputStream input= socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				OutputStream output = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(output, true);
				
				String text;
				
				writer.println("HTTP/1.0 200 OK");
				writer.println("Content-Type: text/html");
				writer.println("Server: Bot");
				
				writer.println("");
				
				writer.println("<H1> Welcomne to the server</H2>");
				writer.flush();
				socket.close();
				
			}
			} catch (IOException ex) {
				fatalError(ex);
			}
    	}

    /**
     * Log web server requests
     * @param s - message to log
     */
    public static void log(String s) {
        System.out.println(s);
    }

    /**
     * Handle fatal error - print info and die
     */
    public static void fatalError(String s) {
        handleError(s, null, true);
    }

    /**
     * Handle fatal error - print info and die
     */
    public static void fatalError(Exception e) {
        handleError(null, e, true);
    }

    /**
     * Handle fatal / non-fatal errors
     */
    public static void handleError(String s, Exception e, boolean isFatal) {
        if (s != null) {
            System.out.println(s);
        }
        if (e != null) {
            e.printStackTrace();
        }
        if (isFatal) System.exit(-1);
    }

    /**
     * Get port configuration value
     * @return port number
     */
    public static int getPort() {
        return port;
    }

    /**
     * Get default HTML folder
     * @return folder
     */
    public static String getDefaultFolder() {
        return defaultFolder;
    }

    /**
     * Get name of default web page
     * @return default page
     */
    public static String getDefaultPage() {
        return defaultPage;
    }
}