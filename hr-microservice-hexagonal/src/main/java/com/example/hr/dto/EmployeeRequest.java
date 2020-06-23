package com.example.hr.dto;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.MoneyCurrency;

public class EmployeeRequest {
	private String identity;
	private String fullname;
	private double salary;
	private String iban;
	private boolean fulltime;
	private int birthYear;
	private byte[] photo;
	private Department department;

	public EmployeeRequest() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public boolean isFulltime() {
		return fulltime;
	}

	public void setFulltime(boolean fulltime) {
		this.fulltime = fulltime;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeeRequest [identity=" + identity + ", fullname=" + fullname + ", salary=" + salary + ", iban="
				+ iban + ", fulltime=" + fulltime + ", birthYear=" + birthYear + ", department=" + department + "]";
	}

	public Employee toEmployee() {
		String[] tokens = fullname.split("\\w+");
		return new Employee.Builder(identity).fullname(tokens[0], tokens[1]).iban(iban).salary(salary, MoneyCurrency.TL)
				.birthYear(birthYear).fulltime(fulltime).department(department).photo(photo).build();
	}

}
