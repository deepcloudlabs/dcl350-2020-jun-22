package com.example.crm.events;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class CustomerBaseEvent {
	@Id
	private String eventId;
	private long conversationId;
	private long sequenceId;
	private String sourceId;
	private String identity;

	public CustomerBaseEvent() {
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public long getConversationId() {
		return conversationId;
	}

	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}

	public long getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(long sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "CustomerBaseEvent [eventId=" + eventId + ", conversationId=" + conversationId + ", sequenceId="
				+ sequenceId + ", sourceId=" + sourceId + ", identity=" + identity + "]";
	}

}
