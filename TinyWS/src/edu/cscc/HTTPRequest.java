package edu.cscc;

/**
 * HTTPRequest - parse HTTP Requests
 * (actually parse a small part of a GET Request: GET [filepath])
 * @author Rexford Priest, Reid Schrein
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
     * splits string by space,tab,newline, or question mark, if paremeters are met in if block
     * this returns the boolean of true.
     */
    private boolean parse(String r) {
    	String[] arrayOfString = r.split("[ \t\n?]");
    	if(arrayOfString.length >= 2 && arrayOfString[0].equals("GET") && arrayOfString[1] 
    			!= null && !arrayOfString[1].isEmpty()) {
    	    request = arrayOfString[0];
    	    path = arrayOfString[1];
    	    return true;
        }
    	return false;
    }
}