package com.revature.model;

public class PetFactory {
	public static Pet getPet(int petGroup) {
		switch(petGroup) {
		case 1:
			return new Animal("Mammal");
		case 2:
			return new Animal("Reptile");
		case 3:
		 return new Animal("Fish");
		case 4:
			return new Animal("Amphibian");
		case 5:
			return new Animal("Bird");
		default:
			return null;
		}
	}
}
