package co.edu.uco.nose.crosscuting.exception;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public final class NoseException extends RuntimeException {
		
	private static final long serialVersionUID= 512335343454L;
	private Throwable rootException;
	private static String userMessage;
	private String technicalMessage;
	
	private NoseException(final Throwable rootException, final String suerMessage, String userMessage2) {
		setRootException(rootException);
		setUserMessage(suerMessage);
		setTechnicalMessage(suerMessage);
	
	}
	
	public static NoseException create(final String userMessage) {
		return new NoseException(new Exception(), userMessage, userMessage);
	}
	
	public static NoseException create(final String userMessage, final String technicalMessage) {
		return new NoseException(new Exception(), userMessage, technicalMessage);
	}
	
	public static NoseException create(final Throwable rootException, final String userMessage, final String technicalMessage) {
		return new NoseException(rootException, userMessage, technicalMessage);
	}
	
	private Throwable getRootException() {
		return rootException;
	}
	private void setRootException(Throwable rootException) {
		this.rootException = ObjectHelper.getDefault(rootException, new Exception());
	}
	public String getUserMessage() {
		return userMessage;
	}
	private void setUserMessage(final String userMessage) {
		this.userMessage = TextHelper.getDefaultWithTrim(userMessage);
	}
	public String getTechnicalMessage() {
		return technicalMessage;
	}
	private void setTechnicalMessage(final String technicalMessage) {
		this.technicalMessage = TextHelper.getDefaultWithTrim(technicalMessage);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}