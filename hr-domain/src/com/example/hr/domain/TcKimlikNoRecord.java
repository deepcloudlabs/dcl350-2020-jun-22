package com.example.hr.domain;

//Value Object (DDD) --> Immutable
// Java 14: (preview feature)
public record TcKimlikNoRecord(String value) { 
	//POJO: JavaBeans Naming Convention -> getValue()/isValue()
	// getter: value()
}