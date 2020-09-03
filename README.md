# hibernate
Example - Relationship(OneToOne,OneToMany,ManyToOne,ManyToMany),1st level cache and 2nd level cache 

This is a hibernate project which is expelling about relations and cache.
This is developed in eclipse mars2.

Steps to run.
01. Download or clone this project.
02.1st time when you run this app,
 Crete a mysql database with Name company.
 Go to the hibernate.cfg.xml file and do database configuration and set <property name="hbm2ddl.auto">create</property>
 Go to App.clss (package com.lahiru.hibernate) , 
    then go to main method,
    run only app.basicHibernate();
    Others should be commented.    
    Database table should be automatically created.
    
    
    //When run this method..Go to hibernate.cfq.xml and set <property name="hbm2ddl.auto">create</property>
		app.basicHibernate();
		
		//When run bellow methods..Go to hibernate.cfq.xml and set <property name="hbm2ddl.auto">update</property>
		//app.HQLHibernate();
	  //app.SQLHibernate();
		//app.HibernateFirstLevelCache();
		//app.HibernateSecondLevelCache()

    
03. After 1st time, Go to the hibernate.cfg.xml file and set <property name="hbm2ddl.auto">update</property>
04. In the main method, run method by method and see the result.
    
