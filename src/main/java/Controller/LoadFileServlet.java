package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.User;
import Model.BO.FileBO;

@WebServlet("/LoadFileServlet")
public class LoadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoadFileServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("User");
		request.setAttribute("UserFiles", new FileBO().GetFiles(user));
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/HomePage.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
