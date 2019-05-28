package com.example.ssc.course;


public class Course {
	private String course_id;
	private String course_name;
	private Integer course_type_id;
	private Integer course_status_id;
	private Integer class_hour;
	private Integer credit;
	private Integer maxnum;
	private String course_time_id;
	private String classroom_id;
	private String time_and_classroom;
	//添加任课教师。。。设计数据库失误。。。
	public Course(String course_id, String course_name, Integer course_type_id,
				  Integer course_status_id, Integer class_hour, Integer credit,
				  Integer maxnum, String course_time_id, String classroom_id,
				  String time_and_classroom){
		this.course_id=course_id;
		this.course_name=course_name;
		this.course_type_id=course_type_id;
		this.course_status_id=course_status_id;
		this.class_hour=class_hour;
		this.credit=credit;
		this.maxnum=maxnum;
		this.course_time_id=course_time_id;
		this.classroom_id=classroom_id;
		this.time_and_classroom=time_and_classroom;
		
	}


	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public Integer getCourse_type_id() {
		return course_type_id;
	}

	public void setCourse_type_id(Integer course_type_id) {
		this.course_type_id = course_type_id;
	}

	public Integer getCourse_status_id() {
		return course_status_id;
	}

	public void setCourse_status_id(Integer course_status_id) {
		this.course_status_id = course_status_id;
	}

	public Integer getClass_hour() {
		return class_hour;
	}

	public void setClass_hour(Integer class_hour) {
		this.class_hour = class_hour;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer getMaxnum() {
		return maxnum;
	}

	public void setMaxnum(Integer maxnum) {
		this.maxnum = maxnum;
	}

	public String getCourse_time_id() {
		return course_time_id;
	}

	public void setCourse_time_id(String course_time_id) {
		this.course_time_id = course_time_id;
	}

	public String getClassroom_id() {
		return classroom_id;
	}

	public void setClassroom_id(String classroom_id) {
		this.classroom_id = classroom_id;
	}

	public String getTime_and_classroom() {
		return time_and_classroom;
	}

	public void setTime_and_classroom(String time_and_classroom) {
		this.time_and_classroom = time_and_classroom;
	}
}
