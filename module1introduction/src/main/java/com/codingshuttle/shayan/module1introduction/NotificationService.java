package com.codingshuttle.shayan.module1introduction;

//we cannot do @Component here are, as we cannot create object of an interface, that will be managed by springboot
public interface NotificationService  {

    void send(String message); //As in interface all the methods are bydefault public and abstract
}
