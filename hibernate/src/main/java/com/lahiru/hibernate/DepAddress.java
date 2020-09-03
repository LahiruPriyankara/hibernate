package com.lahiru.hibernate;

import javax.persistence.Cacheable;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//Embedding with its parent object and adding DepAddress fields in parent class SQL table
@Embeddable
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class DepAddress {
	
	public DepAddress() {}	
	
	public DepAddress(String frist_line, String second_line, String third_line) {
		super();
		this.frist_line = frist_line;
		this.second_line = second_line;
		this.third_line = third_line;
	}

	String frist_line;
	String second_line;
	String third_line;
	

	public String getFrist_line() {
		return frist_line;
	}

	public void setFrist_line(String frist_line) {
		this.frist_line = frist_line;
	}

	public String getSecond_line() {
		return second_line;
	}

	public void setSecond_line(String second_line) {
		this.second_line = second_line;
	}

	public String getThird_line() {
		return third_line;
	}

	public void setThird_line(String third_line) {
		this.third_line = third_line;
	}

	@Override
	public String toString() {
		return "DepAddress [frist_line=" + frist_line + ", second_line=" + second_line + ", third_line=" + third_line
				+ "]";
	}

}
