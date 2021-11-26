package Controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.BEAN.File;
import Model.BEAN.User;
import Model.BO.FileBO;

@WebServlet("/UploadFileServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 50) 
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UploadFileServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Part filePart = request.getPart("file");
			// Get user from session
			User user = new User();
			user.setId(69);
			user.setUsername("admin");
			user.setPassword("admin");
			// Get user from session
			if (filePart != null) {
				byte[] data = filePart.getInputStream().readAllBytes();
				File file = new File();
				file.setDate(new java.sql.Date(new java.util.Date().getTime()));
				String[] nameParts = filePart.getSubmittedFileName().split("\\.");
				file.setFileName(String.join("", Arrays.copyOfRange(nameParts, 0, nameParts.length - 1)));
				file.setStatus(false);
				file.setUserID(user.getId());
				if (new FileBO().UploadFile(data, file)) {
					// send upload complete message
					response.getWriter().write("Upload successful !");
				} else {
					response.getWriter().write("Upload failure !");
					// send upload failure message
				}
			}
		} catch (IllegalStateException e) {
			System.out.println("File size lager than max file size: " + e.toString());
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
