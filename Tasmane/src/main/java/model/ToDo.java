package model;

import java.io.Serializable;
import java.sql.Date;

public class ToDo implements Serializable{

	private int task_id;
	private String task;
	private int mark;
	private String exp;
	private Date registered_date;

	public ToDo() {}
	
	public ToDo(String task, String exp) {
		this.task = task;
		this.exp = exp;
	}
	
	public ToDo(int task_id, String task, int mark, String exp, Date registered_date) {
		this.task_id = task_id;
		this.task = task;
		this.mark = mark;
		this.exp = exp;
		this.registered_date = registered_date;
	}
	
	public int getTask_id() {
		return this.task_id;
	}

	public String getTask() {
		return this.task;
	}

	public int getMark() {
		return this.mark;
	}

	public String getExp() {
		return this.exp;
	}

	public Date getRegistered_date() {
		return this.registered_date;
	}
	
}
