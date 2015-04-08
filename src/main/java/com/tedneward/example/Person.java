package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  
  public int compareTo(Person other) {
    return (int) (other.getSalary() - this.getSalary());
  }

  public static class AgeComparator implements Comparator<Person> {
      
    public int compare(Person b, Person a) {
       return b.getAge() - a.getAge();
    }

  }
  
  private int age;
  private String name;
  private double salary;
  private String ssn = "";
  private boolean propertyChangeFired = false;
  private int personCount = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    personCount++;
  }

  public int getPersonCount() {
    return personCount;
  }

  public void setAge(int a) {
    if (a < 0) {
      throw new IllegalArgumentException();
    } else {
      this.age = a;
    }
  }

  public int getAge() {
    return age;
  }

  public void setName(String n) {
    if (n == null) {
      throw new IllegalArgumentException();
    }
    this.name = n;
  }
  
  public String getName() {
    return name;
  }

  public void setSalary(double s) {
    this.salary = s;
  }

  public double getSalary() {
    return salary;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public String getSSN() {
    return ssn;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person other = (Person)other;
    }
    return (this.name.equals(other.name) && this.age == other.age);
  }

  public String toString() {
    return "[Person name:" + this.getName() + " age:" + this.getAge() + " salary:" + this.getSalary() + "]";
  }

  public int count() {
    return personCount;
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList newards = new ArrayList<Person>();
    newards.add(new Person("Ted", 41, 250000));
    newards.add(new Person("Charlotte", 43, 150000));
    newards.add(new Person("Michael", 22, 10000));
    newards.add(new Person("Matthew", 15, 0));
    return newards;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
