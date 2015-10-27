package models.form;

public class NotificationV {

	private String message;
	private String topic;
	private String toUserId;
	
	public NotificationV(){
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String mensaje) {
		this.message = mensaje;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	
	
	
	
}
