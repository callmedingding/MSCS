package com.example.ssc.teacher;
//没用
public class Teacher {
	private Integer id;
	private String  name;
	private String  sex;
	private String xueyuan;
	private String phonenumber;

	public Teacher(Integer id, String name, String sex, String xueyuan,
			String phonenumber) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.xueyuan = xueyuan;
		this.phonenumber = phonenumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getXueyuan() {
		return xueyuan;
	}

	public void setXueyuan(String xueyuan) {
		this.xueyuan = xueyuan;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
}
