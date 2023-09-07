package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostToDoLogic;

@WebServlet("/Updated")
public class Updated extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Updated() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id;
		if (request.getParameter("idset") != null) {
			id = Integer.parseInt(request.getParameter("idset"));
		} else {
			id = 0;
		}
		
		String task;
		if (request.getParameter("taskEdited").length() > 0) {
			task = request.getParameter("taskEdited");
		} else {
			task = null;
		}
		
		String exp;
		if (request.getParameter("expEdited").length() > 0) {
			exp = request.getParameter("expEdited");
		} else {
			exp = null;
		}
		
		Date d;
		if (request.getParameter("dateEdited").length() > 0) {
			d = Date.valueOf(request.getParameter("dateEdited"));
		} else {
			d = null;
		}
		
		PostToDoLogic ptl = new PostToDoLogic();
		ptl.update(id, task, exp, d);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/updated.jsp");
		rd.forward(request, response);
	}

}
