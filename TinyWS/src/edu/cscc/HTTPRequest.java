package edu.cscc;

import java.util.Arrays;

/**
 * HTTPRequest - parse HTTP Requests
 * (actually parse a small part of a GET Request: GET [filepath])
 * @author student name
 */
public class HTTPRequest {
    private String request;         // request string
    private String path;            // path to file
    private boolean validRequest;   // is request valid?

    /**
     * Constructor
     * @param r - HTTP request string to be parsed
     */
    public HTTPRequest(String r) {
        validRequest = parse(r);
        System.out.println("HTTPREQUEST" + validRequest);

      
    }

    /**
     * Is the request valid
     */
    public boolean isValidRequest() {
    	
        return (validRequest);
    }

    /**
     * Return file path for request
     */
    public String getPath() {
       
        return (path);
    }

    /**
     * Parse an HTTP request
     */
    private boolean parse(String r) {
 
       String[] arrayOfString =  r.split(" ");
       System.out.println(arrayOfString[0]);
       String test = arrayOfString[0];
        if(test.equals("GET")) {
        	path = arrayOfString[1];
        	return true;
        }
    
    	return false;
    }
}