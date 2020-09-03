package com.lahiru.hibernate;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App {
	public static void main(String[] args) {
		App app = new App();
		//When run this method..Go to hibernate.cfq.xml and set <property name="hbm2ddl.auto">create</property>
		//app.basicHibernate();
		
		//When run bellow methods..Go to hibernate.cfq.xml and set <property name="hbm2ddl.auto">update</property>
		app.HQLHibernate();
	    // app.SQLHibernate();
		//app.HibernateFirstLevelCache();
		//app.HibernateSecondLevelCache();

	}
	
	public Session getSession(){
		try{
			System.out.println("getSession");
			
			Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class);;
			ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
			SessionFactory sf = con.buildSessionFactory(reg);
			Session session = sf.openSession();
			
			System.out.println("Make Session Successfully");
			return session;
		}catch(Exception e){
			System.out.println("Exception : "+e);
			return null;
		}
	}

	public void basicHibernate(){
		
		Session session = getSession();
		Transaction tx = null;
		try{
		System.out.println("basicHibernate");
		if(session == null){
			throw new Exception("Session is null");
		}
		Department department_1 = new Department("IT","Colombo","department_1_Head");
		Department department_2 = new Department("IDS","Colombo","department_2_Head");
		
		DepAddress dep_addOne = new DepAddress("No:10(One)","Main Street(One)","Colombo 10(One)");		
		DepAddress dep_addTwo = new DepAddress("No:10(Two)","Main Street(Two)","Colombo 10(Two)");
		
		Employee emp1 = new Employee("employee_1_Name",25,"emp1_name Address");
		Employee emp2 = new Employee("employee_2_Name",35,"emp2_name Address");	
		emp1.setDepartment(department_1);
		emp2.setDepartment(department_1);
		
			
		Employee emp3 = new Employee("employee_3_Name",45,"emp3_name Address");
		Employee emp4 = new Employee("employee_4_Name",55,"emp4_name Address");
		emp3.setDepartment(department_2);	
		emp4.setDepartment(department_2);
		
		
		
		department_1.setAddress(dep_addOne);						
		department_1.getEmployee().add(emp1);
		department_1.getEmployee().add(emp2);
		
		department_2.setAddress(dep_addTwo);
		department_2.getEmployee().add(emp3);
		department_2.getEmployee().add(emp4);
	
		
		System.out.println("basicHibernate | Going to hit DataBase");
		
		//If we dont use 'cascade = CascadeType.ALL' , Then we have to save childrens  and parent objects separatly.
		/*session.save(emp1);
		session.save(emp2);
		session.save(emp3);
		session.save(emp4);*/
		
		tx = session.beginTransaction();
		System.out.println("basicHibernate | Going to Save 1st Object");
		session.save(department_1);		
		tx.commit();
		
		System.out.println("basicHibernate | Going to Save 2nd Object");
		
		tx = session.beginTransaction();
		session.save(department_2);
		tx.commit();
		
		
		System.out.println("basicHibernate | Going to Save 3nd Object");
		Department department = new Department();
		department.setName("Save Departmet");
		tx = session.beginTransaction();
		session.save(department);
		tx.commit();
		
		System.out.println("basicHibernate | Going to Update 3nd Object");  
		department.setName("Update Saved Departmet");
		tx = session.beginTransaction();
		session.update(department);
		session.delete(department);
		tx.commit();
		
		/*tx = session.beginTransaction();
		Department dep0;
		dep0 = (Department)session.get(Department.class, 2);
		System.out.println(dep0);
		System.out.println(".....................................................");
		tx.commit();*/
		
		}catch(Exception e){
			System.out.println("Error : "+e);
		}finally{
			session.close();
		}
		
	}

	public void HQLHibernate() {
		Session session = getSession();
		Transaction tx = null;
		try{
			System.out.println("HQLHibernate");	
		    tx = session.beginTransaction();			
	
			Department dep1ist1 = (Department) session.get(Department.class, 1);
			System.out.println("DEPARTMENT (ID = 1) WOQ : "+dep1ist1);
			System.out.println(".....................................................");
	
			Query query;
			query = session.createQuery("from Department where id = ?");
			query.setParameter(0, 1);
			// query = session.createQuery("from Department where d_code = :p");
			// query.setParameter("P", 1);
			Department dep1 = (Department) query.uniqueResult();
			System.out.println("DEPARTMENT (ID = 1) WQ  : "+dep1);
			System.out.println(".....................................................");
			
			/*List<Department> dep1ist2 = (List<Department>)session.get(Department.class);
			System.out.println("ALL DEPARTMENTS  WOQ : "+dep1ist2);
			System.out.println(".....................................................");*/
	
			query = session.createQuery("from Department");
			List<Department> depList = (List<Department>) query.list();
	
			for (Department dep2 : depList) {
				System.out.println("DEPARTMENT  WQ : "+dep2);
			}
	
			tx.commit();
		}catch(Exception e){
			System.out.println("Error : "+e);
		}finally{
			session.close();
		}

	}

	public void SQLHibernate() {
		Session session = getSession();
		Transaction tx;
		SQLQuery query;
		try{
			System.out.println("SQLHibernate");
			
			tx = session.beginTransaction();	
			// List<Department> dep1 =
			// (List<Department>)session.get(Department.class);
			// System.out.println(dep1);
			// System.out.println(".....................................................");
			
			query = session.createSQLQuery("select * from Department where id = ?"); 
			query.setParameter(0,1); 
			Object dep2 = query.uniqueResult(); 
			System.out.println(dep2);
			System.out.println(".....................................................");
			 
			query = session.createSQLQuery("select * from Department"); 
			List depList = query.list();
			 
			for(Object d: depList){ System.out.println(d); }
			 
	
			
			query = session.createSQLQuery("select * from Department where id = ?"); query.setParameter(0,1); 
		    query.addEntity(Department.class); 
			Department dep3 =(Department)query.uniqueResult(); 
			System.out.println(dep3);
			System.out.println(".....................................................");
			  
			query = session.createSQLQuery("select * from Department");
			query.addEntity(Department.class); 
			List<Department> depList2 = query.list();
			  
			for(Department d: depList2){ System.out.println(d); }
			 
	
			
			query = session.createSQLQuery("select name,departmentLocatiion from Department where id = ?");
			query.setParameter(0, 1);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP); // result can be taken as a map
			Object dep4 = query.uniqueResult();
			Map map1 = (Map) dep4;
			System.out.println(map1.get("name") + "  -  " + map1.get("departmentLocatiion"));
	
			System.out.println(".....................................................");
	
			query = session.createSQLQuery("select * from Department");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List depList3 = query.list();
	
			for (Object d : depList) {
				Map map2 = (Map) d;
				System.out.println(map2.get("name") + "  -  " + map2.get("departmentLocatiion"));
			}
	
			tx.commit();
		}catch(Exception e){
			System.out.println("Error : "+e);
		}finally{
			session.close();
		}

	}

	public void HibernateFirstLevelCache() {
		try{
			System.out.println("HibernateFirstLevelCache");
			
			Session session = null;
			Transaction tx = null;
			Employee emp = new Employee();
			Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class);
			ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
			SessionFactory sf = con.buildSessionFactory(reg);

			session = sf.openSession();
			tx = session.beginTransaction();
			System.out.println("----------------------Using 1 session------------------------------------");
			System.out.println("..................... 01 ...............................");
			emp = (Employee) session.get(Employee.class, 1);
			System.out.println(emp);

			System.out.println("..................... 02 ................................");

			emp = (Employee) session.get(Employee.class, 1);
			System.out.println(emp);

			tx.commit();
			session.close();
			
			System.out.println("----------------------Using 2 sessions------------------------------------");
			
			session = sf.openSession();
			tx = session.beginTransaction();
			
			System.out.println("..................... 01 ...............................");
			emp = (Employee) session.get(Employee.class, 1);
			System.out.println(emp);
			tx.commit();
			session.close();
			
			System.out.println("..................... 02 ................................");
		    session = sf.openSession();
			tx = session.beginTransaction();
			emp = (Employee) session.get(Employee.class, 1);
			System.out.println(emp);
			
			tx.commit();
			session.close();
			
		}catch(Exception e){
			System.out.println("Error "+e);
		}
	}

	public void HibernateSecondLevelCache() {
		try{	
			
			System.out.println("HibernateSecondLevelCache");
			Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class);
			ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
			SessionFactory sf = con.buildSessionFactory(reg);

			System.out.println("---------------------- 2nd Level Chache------------------------------------");
			
			Session session1 = sf.openSession();
			Transaction tx1 = session1.beginTransaction();

			System.out.println("..................... 01 ...............................");

			Employee dep1 = (Employee) session1.get(Employee.class, 1);
			System.out.println(dep1);
			tx1.commit();
			session1.close();

			System.out.println("..................... 02 ...............................");

			Session session2 = sf.openSession();
			Transaction tx2 = session2.beginTransaction();

			Employee dep2 = (Employee) session2.get(Employee.class, 1);
			System.out.println(dep2);

			tx2.commit();
			session2.close();
		}catch(Exception e){
			
		}
	}


}
/*........................................ ERRORS AND HOW TO SOLVE IT ........................................
 * 
 * @OneToMany relationship does not save the primary key of the parent in the children tables
https://stackoverflow.com/questions/52887653/onetomany-relationship-does-not-save-the-primary-key-of-the-parent-in-the-child



------------------------------------------------------------Error
Session session = getSession();
Transaction tx = session.beginTransaction();
session.save(department_1);	
session.save(department_2);
tx.commit();
session.close();

------------------------------------------------------------To fixed it use as bellow
Session session = getSession();

Transaction tx = session.beginTransaction();
session.save(department_1);		
tx.commit();


tx = session.beginTransaction();
session.save(department_2);
tx.commit();

Exception in thread "main" java.lang.StackOverflowError

change toString method from

@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", departmentLocatiion=" + departmentLocatiion
				+ ", department_head=" + department_head + ", employee=" + employee + ", address=" + address + "]";
	}

to

@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", departmentLocatiion=" + departmentLocatiion
				+ ", department_head=" + department_head + ", employee=" + employee.size() + ", address=" + address + "]";
	}

*/