package com.nea.jdbc.example;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class DataDateType {

	public static void main(String[] args) {
		Date javaDate = new Date();
		System.out.println("The Java Date is:" + javaDate.toString());

		long javaTime = javaDate.getTime();
		System.out.println("The Java Time is:" + javaTime);
		
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		System.out.println("The SQL Date is:" + javaDate.toString());
		
		Time sqlTime = new Time(javaTime);
		System.out.println("The SQL Time is:" + javaDate.toString());

		Timestamp timestamp =new Timestamp(javaTime);
		System.out.println("The SQL Timestamp is:" + javaDate.toString());
				
	}
}
