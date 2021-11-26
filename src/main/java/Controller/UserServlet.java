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

/**
 * Servlet implementation class CheckLoginServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBO userBo;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		switch (action) {
		case "/checkLogin":{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new UserBO().findOne(username, password);
			if (user != null) {
				String destinate = "/HomePage.jsp";
				request.getSession().setAttribute("User", user);
				RequestDispatcher rs = request.getRequestDispatcher(destinate);
				rs.forward(request, response);
			} else
			{
				String destinate = "/Login.jsp";
				//String mes="failed";
				RequestDispatcher rs = request.getRequestDispatcher(destinate);
				rs.forward(request, response);
			}
			break;
		}
		case "/Register":{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new User(username,password);
			new UserBO().ChenTK(user);
			String destinate="/Login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(destinate);
			rd.forward(request, response);
			break;
		}
		//doGet(request, response);
	}
	}
}
