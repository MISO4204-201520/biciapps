package models.form;

public class NotificationV {

	
	private String id;
	private String message;
	private String topic;
	private String userId;
	
	public NotificationV(){
		
	}

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
		
}
