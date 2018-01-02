package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DocumentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		File[] files = new File("C:\\Users\\Logan_53e\\Documents\\York\\Fall 2017\\CS481\\Eclipse\\Master-Gardener-Pollinator-Monitoring\\Master_Gardener_Source\\war\\documents").listFiles();
		//File[] files = new File("C:\\Users\\Nick\\Desktop\\Nick\\Master_Gardener_Source\\war\\documents").listFiles();
		System.out.println(files.toString());
		req.setAttribute("files", files);
		req.getRequestDispatcher("/_view/documents.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getRequestDispatcher("/_view/documents.jsp").forward(req, resp);
	}
}