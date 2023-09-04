package model;

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
}
