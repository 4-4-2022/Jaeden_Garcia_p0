package com.revature.model;

public class Animal extends Pet implements Handleable {
	
	private String name;
	
	public Animal(String group) {
		super(group);
	}
	
	public Animal(String group,String family, String breed, boolean isMale,  int age, String nature, float cost) {
		super(group, family, breed, isMale,  age, nature, cost);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "I am a "+((this.isMale) ? "Male" : "female")+" "+breed+". \n My name is "+
				name+" and my nature is "+nature +"I am a "+family+" which makes me a/an " +group+". "+
				"You can purchase me for $"+cost;
	}
	
}
