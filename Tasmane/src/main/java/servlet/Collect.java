package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostToDoLogic;
import model.ToDo;

@WebServlet("/Collect")
public class Collect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int getId;
		if (request.getParameter("edt") != null) {
			getId = Integer.parseInt(request.getParameter("edt"));
		} else {
			getId = 0;
		}
		ToDo td = new ToDo();
		PostToDoLogic ptl = new PostToDoLogic();
		td = ptl.find(getId);
		
		request.setAttribute("id", td.getTask_id());
		request.setAttribute("task", td.getTask());
		request.setAttribute("exp", td.getExp());
		request.setAttribute("date", td.getRegistered_date());
		request.setAttribute("mark", td.getMark());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/collect.jsp");
		dispatcher.forward(request, response);
	}
}
