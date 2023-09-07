package model;

import java.sql.Date;
import java.util.List;

import dao.ToDoDAO;

public class PostToDoLogic{
	
	private ToDoDAO tdd;
	
	//DAOを使ってToDoリストで返却。POSTで使用
	public void execute(ToDo td) {
		tdd = new ToDoDAO();
		tdd.create(td);
	}
	
	public List<ToDo> makeList() {
		tdd = new ToDoDAO();
		List<ToDo> tdl = tdd.findAll();
		return tdl;
	}

	public void delete(int gotId) {
		tdd = new ToDoDAO();
		tdd.delete(gotId);
	}
	
	public ToDo find(int pkNum) {
		tdd = new ToDoDAO();
		ToDo td = tdd.find(pkNum);
		return td;
	}
	
	public void update(int id, String task, String exp, Date d) {
		tdd = new ToDoDAO();
		tdd.update(id, task, exp, d);
	}
}
