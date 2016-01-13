package com.app.booksexchange.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

@XmlRootElement
public class ErrorMessage {

	private int errorCode;
	private String errorMessage;
	private String documentation;

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.documentation = documentation;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	@Override
	public String toString() {

		JSONObject obj = new JSONObject();
		obj.put("errorCode", errorCode);
		obj.put("errorMessage", errorMessage);
		obj.put("documentation", documentation);
		return obj.toString();

	}

}
