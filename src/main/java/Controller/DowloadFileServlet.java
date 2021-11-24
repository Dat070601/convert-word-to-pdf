package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.File;
import Model.BO.FileBO;
import Model.DAO.FileDAO;
import org.o7planning.servletexamples.model.Attachment;


/**
 * Servlet implementation class DowloadFileServlet
 */
@WebServlet("/DowloadFileServlet")
public class DowloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DowloadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
	       try {  
	           conn = new FileBO().GetConnection();
	           int id = 0;
	           try {
	               id = Integer.parseInt(request.getParameter("id"));
	           } catch (Exception e) {

	           }
	           File file = new FileBO().GetFilePDFFromDB(conn, id);
	           
	           if (file == null) {
	               response.getWriter().write("Không tìm thấy data!");
	               return;
	           }
	           
	           if(file.getStatus() == 0)
	           {
	        	   response.getWriter().write("File "+ file.getFileName() + " chưa xử lý!");
	               return;
	           }

	           String fileName = file.getFileName();
	           //System.out.println("File Name: " + fileName);
	           String contentType = this.getServletContext().getMimeType(fileName);
	           //System.out.println("Content Type: " + contentType);
	           response.setHeader("Content-Type", contentType);
	           response.setHeader("Content-Length", String.valueOf(file.getData().length()));
	           response.setHeader("Content-Disposition", "inline; filename=\"" + file.getFileName() + "\"");
	           Blob fileData = file.getData();
	           InputStream is = fileData.getBinaryStream();
	           byte[] bytes = new byte[1024];
	           int bytesRead;
	           while ((bytesRead = is.read(bytes)) != -1) {
	               response.getOutputStream().write(bytes, 0, bytesRead);
	           }
	           is.close();

	       } catch (Exception e) {
	           throw new ServletException(e);
	       } 
	       finally
	       {
	    	   this.closeQuietly(conn);
	       }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 private void closeQuietly(Connection conn) {
	       try {
	           if (conn != null) {
	               conn.close();
	           }
	       } catch (Exception e) {
	       }
	   }

}