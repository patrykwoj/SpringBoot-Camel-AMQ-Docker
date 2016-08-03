package pl.patrykwoj.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pl.patrykwoj.util.MessageTypeEnum;

@Entity
public class MessageType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(unique = true)
	@Enumerated(EnumType.STRING)
	private MessageTypeEnum name;
	
	public MessageType() {
		super();
	}
	
	public MessageType(MessageTypeEnum name) {
		super();
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public MessageTypeEnum getName() {
		return name;
	}
	
	public void setName(MessageTypeEnum name) {
		this.name = name;
	}
}
