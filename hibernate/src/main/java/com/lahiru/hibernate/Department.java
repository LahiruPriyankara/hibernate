package com.lahiru.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Department  implements Serializable {
	
	public Department() {}	
	
	public Department(String name, String departmentLocatiion, String department_head) {
		super();
		this.name = name;
		this.departmentLocatiion = departmentLocatiion;
		this.department_head = department_head;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment field
	private int id;
	private String name;
	private String departmentLocatiion;
	private DepAddress address;	
	@Transient
	private String department_head;		
	
	//cascade = CascadeType.ALL - what we do for parent, it will affect to all children.
	//If Department is deleting , Updating all children also delete or update
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL) 
	private List<Employee> employee = new ArrayList<Employee>();

	

	public int getid() {
		return id;
	}



	public void setid(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDepartmentLocatiion() {
		return departmentLocatiion;
	}



	public void setDepartmentLocatiion(String departmentLocatiion) {
		this.departmentLocatiion = departmentLocatiion;
	}



	public DepAddress getAddress() {
		return address;
	}



	public void setAddress(DepAddress address) {
		this.address = address;
	}



	public String getDepartment_head() {
		return department_head;
	}



	public void setDepartment_head(String department_head) {
		this.department_head = department_head;
	}



	public List<Employee> getEmployee() {
		return employee;
	}



	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}



	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", departmentLocatiion=" + departmentLocatiion
				+ ", department_head=" + department_head + ", employee=" + employee.size() + ", address=" + address + "]";
	}
	

	
}
