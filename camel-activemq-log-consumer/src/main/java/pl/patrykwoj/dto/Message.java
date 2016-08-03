package pl.patrykwoj.dto;

import java.io.Serializable;

import pl.patrykwoj.util.MessageTypeEnum;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MessageTypeEnum type;
	
	private String text;

	public Message() {
	}

	public Message(MessageTypeEnum type, String text) {
		this.type = type;
		this.text = text;
	}

	public MessageTypeEnum getType() {
		return type;
	}

	public void setType(MessageTypeEnum type) {
		this.type = type;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [" + (type != null ? "type=" + type + ", " : "") + (text != null ? "text=" + text : "") + "]";
	}
	
	
}
