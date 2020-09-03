package com.lahiru.hibernate;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Employee  implements Serializable {
	
	public Employee() {}	
	
	public Employee(String name, int age, String empAddress) {
		super();
		this.name = name;
		this.age = age;
		this.empAddress = empAddress;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment field
	private int Id;
	private String name;
	private int age;
	private String empAddress;
	
	@ManyToOne
	//@JoinColumn//(name="id")
	private Department department;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	@Override
	public String toString() {
		return "Employee [Id=" + Id + ", name=" + name + ", age=" + age + ", empAddress=" + empAddress + ", department="
				+ department + "]";
	}
	
	
}
