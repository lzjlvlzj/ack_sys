package org.ack.auth.authenticate.annotation;

public class MyClass {
	
	@AckPermission("user:list or user:find")
    public void myMethod(){
    	System.out.println(1);
    }
}
