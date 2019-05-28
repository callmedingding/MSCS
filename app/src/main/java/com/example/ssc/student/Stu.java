package com.example.ssc.student;

import com.google.gson.annotations.SerializedName;

public class Stu {

	@SerializedName("user_id")
	private String user_id;
	@SerializedName("name")
	private String name;
	@SerializedName("user_key")
	private String user_key;
	@SerializedName("type_id")
	private Integer type_id;
	@SerializedName("sex_id")
	private Integer sex_id;
	@SerializedName("tel")
	private Integer tel;
	@SerializedName("email")
	private String email;
	@SerializedName("class_id")
	private Integer class_id;
	@SerializedName("college_id")
	private Integer college_id;
	@SerializedName("status_id")
	private Integer status_id;
	@SerializedName("course_type_set")
	private String course_type_set;

	public Stu(String user_id, String name, String user_key, Integer type_id, Integer sex_id, Integer tel,
               String email, Integer class_id, Integer college_id, Integer status_id, String course_type_set) {
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

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Integer getSex_id() {
		return sex_id;
	}

	public void setSex_id(Integer sex_id) {
		this.sex_id = sex_id;
	}

	public Integer getTel() {
		return tel;
	}

	public void setTel(Integer tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getClass_id() {
		return class_id;
	}

	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}

	public Integer getCollege_id() {
		return college_id;
	}

	public void setCollege_id(Integer college_id) {
		this.college_id = college_id;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

	public String getCourse_type_set() {
		return course_type_set;
	}

	public void setCourse_type_set(String course_type_set) {
		this.course_type_set = course_type_set;
	}
}
