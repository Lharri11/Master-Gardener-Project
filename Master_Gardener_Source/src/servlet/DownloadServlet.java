package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String filename = request.getParameter("filename");
		String filepath = "C:\\Users\\Logan_53e\\Documents\\York\\Fall 2017\\CS481\\Eclipse\\Master-Gardener-Pollinator-Monitoring\\Master_Gardener_Source\\war\\documents\\";

		if(filename.contains(".docx")) {
			String abs_filepath = filepath + filename;
			File file = new File(abs_filepath);

			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			response.setContentLength((int) file.length());
		}
		else if(filename.contains(".pdf")){
			response.setContentType("application/pdf");
		}
		else if(filename.contains(".jpg")){
			response.setContentType("image/jpeg");
		}
		else if(filename.contains(".png")){
			response.setContentType("image/png");
		}
		else{
			response.setContentType("APPLICATION/OCTET-STREAM");
		}

		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ filename + "\"");

		// use inline if you want to view the content in browser, helpful for
		// pdf file
		// response.setHeader("Content-Disposition","inline; filename=\"" +
		// filename + "\"");
		FileInputStream fileInputStream = new FileInputStream(filepath + filename);

		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}
}