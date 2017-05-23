package org.ack.auth.authenticate.annotation;

public class MyClass {
	
	@AckPermission("user:list1")
    public void myMethod(){
    	System.out.println(1);
    }
}
