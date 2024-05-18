package model;

import java.io.Serializable;
import java.util.Arrays;

public class PersonBag implements Serializable{

	private Person[] array;
	private Person[] personArray;
	private int nElems;
	private int count;
	
	public PersonBag(int max) {
		array = new Person[max];
		personArray = new Person[max];
		nElems = 0;
		count = 0;
	}
	
	public void insert(Person s) {
		array[nElems++] = s;
	}
	public void insertFaculty(Person s) {
		personArray[count++] = s;
	}
	
	public Person searchById(String id) {
		for (int i = 0; i < nElems; i++) {
//			String a = String.format("%08d", i);
			if (array[i].getId().contentEquals(id)) {
				return array[i];
			}
		}
		return null;
	}
	
	public String getPersonBagArray() {
	Person[] arr = Arrays.copyOf(array, nElems);
	String str = "";
	for(int i = 0; i < nElems; i++) {
		str += arr[i] + "\n";
	}
	return str;
	}
	
	
	public String getFacultyBagArray() {
		Person[] arr = Arrays.copyOf(personArray, count);
		String str = "";
		for(int i = 0; i < count; i++) {
			str += arr[i] + "\n";
		}
		return str;
		}
	public Person removeStudentById(String id) {
		int i;
		for (i = 0; i < nElems; i++) {
//			String a = String.format("%08d", i+1);
			if (array[i].getId().contentEquals(id)) {
				break;
			}
		}
		Person temp = array[i];
		if (i == nElems) {
			return null;
		} else {
			for (int j = i; j < nElems - 1; j++) {
				array[j] = array[j + 1];
			}
		}
		nElems--;
		return temp;
	}
	
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(array[i]);
		}
	}
}
