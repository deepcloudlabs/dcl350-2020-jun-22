package com.example.hr.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.hr.domain.Department;
import com.example.validation.Iban;
import com.example.validation.TcKimlik;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class EmployeeRequest {
	@TcKimlik
	private String identity;
	@Size(min = 6)
	private String fullname;
	@Min(3_000)
	private double salary;
	@Iban
	private String iban;
	private boolean fulltime;
	@Max(2002)
	private int birthYear;
	@NotNull
	private byte[] photo;
	@NotNull
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

}
