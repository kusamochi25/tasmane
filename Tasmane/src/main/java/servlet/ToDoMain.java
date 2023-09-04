package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GetToDoLogic;
import model.PostToDoLogic;
import model.ToDo;

@WebServlet("/ToDoMain")
public class ToDoMain extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private List<ToDo> todoList;
	private List<String> taskL;
	private List<String> expL;
	private List<Date> dateL;
	private List<Boolean> markL;
	private List<Integer> idL; 
	
    public ToDoMain() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		GetToDoLogic gtl = new GetToDoLogic();
		if (todoList== null) {
		todoList = gtl.execute();
		} else {
			todoList.clear();
			todoList = gtl.execute();
		}
		idL = new ArrayList<>();
		taskL = new ArrayList<>();
		expL = new ArrayList<>();
		dateL = new ArrayList<>();
		markL = new ArrayList<>();
		
		if (todoList != null) {
			for (int i = 0; i<todoList.size(); i++) {
				ToDo d = todoList.get(i);
				idL.add(d.getTask_id());
				taskL.add(d.getTask());
				expL.add(d.getExp());
				dateL.add(d.getRegistered_date());
				if (d.getMark() == 0) {
					markL.add(false);
				} else {
					markL.add(true);
				}
			}
		}
		
		request.setAttribute("idL", idL);
		request.setAttribute("taskL", taskL);
		request.setAttribute("expL", expL);
		request.setAttribute("dateL", dateL);
		request.setAttribute("markL", markL);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/toDoMain.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String task = request.getParameter("task");
		String exp = request.getParameter("exp");
		int gotId;
		if (request.getParameter("getid") != null) {
		gotId = Integer.parseInt(request.getParameter("getid"));
		} else {
			gotId = 0;
		}
		PostToDoLogic ptl = new PostToDoLogic();
		
		if (task != null && exp != null) {
		
		//DBからロジック経由→リスト取得
		ToDo td = new ToDo(task, exp);
		ptl.execute(td);
		
		} else if(gotId != 0){
			ptl.delete(gotId);
		} else {
			request.setAttribute("errorMsg", "タスクに記述してください。");
		}
		
		idL = new ArrayList<>();
		
		if (todoList != null)
			todoList.clear();
		
		if (taskL != null)
			taskL.clear();
		
		if (expL != null)
			expL.clear();
		
		if (dateL != null)
			dateL.clear();
		
		if(markL != null)
			markL.clear();
		
		todoList = ptl.makeList();
		if (todoList != null) {
			for (int i = 0; i<todoList.size(); i++) {
				ToDo d = todoList.get(i);
				idL.add(d.getTask_id());
				taskL.add(d.getTask());
				expL.add(d.getExp());
				dateL.add(d.getRegistered_date());
				if (d.getMark() == 0) {
					markL.add(false);
				} else {
					markL.add(true);
				}
			}
		}
		
		request.setAttribute("idL", idL);
		request.setAttribute("taskL", taskL);
		request.setAttribute("expL", expL);
		request.setAttribute("dateL", dateL);
		request.setAttribute("markL", markL);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/toDoMain.jsp");
		dispatcher.forward(request, response);
	}
}
