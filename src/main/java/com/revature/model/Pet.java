package com.revature.model;

public abstract class Pet{
	
	protected String group;
	protected String family;
	protected String breed;
	protected boolean isMale;
	protected int age;
	protected String nature;
	protected float cost;
	
	
	public Pet (String group) {
		this.group=group;
	}
	
	public Pet(String group, String family, String breed, boolean isMale, int age, String nature, float cost) {
		super();
		this.group=group;
		this.isMale=isMale;
		this.breed = breed;
		this.age = age;
		this.nature = nature;
		this.family = family;
		this.cost = cost;
		
	}
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	public boolean getIsMale() {
		return this.isMale;
	}
	
	public void setIsMale(boolean isMale) {
		this.isMale = isMale;
	}
	
	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
	
	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}
	
	public float getCost() {
		return this.cost;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "I am a pet";
	}
}
