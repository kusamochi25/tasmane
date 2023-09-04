package model;

import java.util.ArrayList;
import java.util.List;

import dao.ToDoDAO;

public class GetToDoLogic {
	
	//tasmaneテーブルの全レコード取得、それを返すためのクラス、GETで使用
	public List<ToDo> execute(){
		 ToDoDAO tdd = new ToDoDAO();
		List<ToDo> tdl = new ArrayList<>();
		tdl = tdd.findAll();
		return tdl;
	}
	
}
