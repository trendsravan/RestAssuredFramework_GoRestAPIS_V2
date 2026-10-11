package com.qa.gorest.constants;

public enum APIHttpStatus {

	OK			(200, "OK"),
	CREATED		(201, "Created"),
	ACCEPTED		(202, "Accepted"), 
	NO_CONTENT	(204, "No Content"),
	
	BAD_REQUEST			(400, "Bad Request"),
	UNAUTHORIZED			(401, "Unauthorized"),
	FORBIDDEN			(403, "Forbidden"),
	NOT_FOUND			(404, "Not Found"),
	METHOD_NOT_ALLOWED	(405, "Method Not Allowed"), 
	REQUEST_TIMEOUT		(408, "Request Timeout"),
	CONFLICT				(409, "Conflict"), 
	
	INTERNAL_SERVER_ERROR		(500, "Internal Server Error"),
	NOT_IMPLEMENTED				(501, "Not Implemented"), 
	BAD_GATEWAY					(502, "Bad Gateway"),
	SERVICE_UNAVAILABLE			(503, "Service Unavailable"), 
	GATEWAY_TIMEOUT				(504, "Gateway Timeout"),
	HTTP_VERSION_NOT_SUPPORTED	(505, "HTTP Version Not Supported");

	private final int code;
	private final String msg;

	private APIHttpStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
	public String toString() {
		return code + " : "+ msg;
	}
	

}
