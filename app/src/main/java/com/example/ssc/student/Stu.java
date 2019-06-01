package com.example.ssc.student;

public class Stu {

	private String user_id;
	private String name;
	private String user_key;
	private String type_id;
	private String sex_id;
	private String tel;
	private String email;
	private String class_id;
	private String college_id;
	private String status_id;
	private String course_type_set;

	public Stu(String user_id, String name, String user_key, String type_id, String sex_id, String tel,
               String email, String class_id, String college_id, String status_id, String course_type_set) {
		this.user_id=user_id;
		this.name=name;
		this.user_key=user_key;
		this.type_id=type_id;
		this.sex_id=sex_id;
		this.tel=tel;
		this.email=email;
		this.class_id=class_id;
		this.college_id=college_id;
		this.status_id=status_id;
		this.course_type_set=course_type_set;

	}


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_key() {
		return user_key;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getSex_id() {
		return sex_id;
	}

	public void setSex_id(String sex_id) {
		this.sex_id = sex_id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getCollege_id() {
		return college_id;
	}

	public void setCollege_id(String college_id) {
		this.college_id = college_id;
	}

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}

	public String getCourse_type_set() {
		return course_type_set;
	}

	public void setCourse_type_set(String course_type_set) {
		this.course_type_set = course_type_set;
	}
}
