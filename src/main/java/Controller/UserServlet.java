package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.User;
import Model.BO.UserBO;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
			case "/login": {
				String destinate = "/Login.jsp";
				RequestDispatcher rs = request.getRequestDispatcher(destinate);
				rs.forward(request, response);
				break;
			}
			case "/register": {
				String destinate="/Register.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(destinate);
				rd.forward(request, response);
				break;
			}
			case "/logout": {
				String destinate = "/index.jsp";
				request.getSession().setAttribute("User", null);
				RequestDispatcher rs = request.getRequestDispatcher(destinate);
				rs.forward(request, response);
				break;
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
			case "/login":{
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				User user = new UserBO().FindUser(username, password);
				if (user != null) {
					String destinate = "/LoadFileServlet";
					request.getSession().setAttribute("User", user);
					RequestDispatcher rs = request.getRequestDispatcher(destinate);
					rs.forward(request, response);
				} else {
					String destinate = "/Login.jsp";
					RequestDispatcher rs = request.getRequestDispatcher(destinate);
					rs.forward(request, response);
				}
				break;
			}
			case "/register":{
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				User user = new User(username,password);
				new UserBO().InsertUser(user);
				String destinate="/Login.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(destinate);
				rd.forward(request, response);
				break;
			}
		}
	}
}
