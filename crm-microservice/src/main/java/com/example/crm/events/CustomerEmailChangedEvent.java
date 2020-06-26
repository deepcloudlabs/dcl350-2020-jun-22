package com.example.crm.events;

public class CustomerEmailChangedEvent extends CustomerBaseEvent {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomerEmailChangedEvent [email=" + email + "]";
	}

}
