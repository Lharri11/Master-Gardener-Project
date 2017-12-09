package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.EditUserPortraitController;
import controller.UploadController;
import controller.UserController;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "documents";
    private static final String PORTRAIT_DIRECTORY = "portraits";
    String root_directory = "C:\\Users\\Logan_53e\\Documents\\York\\Fall 2017\\CS481\\Eclipse\\Master-Gardener-Pollinator-Monitoring\\Master_Gardener_Source";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/_view/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Acquire username & garden id
        String username = (String) req.getSession().getAttribute("username");
        String gardenname = req.getParameter("gardenname");
        int garden_id = 0;
        UserController controller = new UserController();

        if(gardenname != null){
            System.out.println("Garden Name != NULL");
            try {
                garden_id = controller.getGardenIDbyGardenname(gardenname);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getSession().setAttribute("GardenID", garden_id);
        }

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);

        if (!isMultipart) {
            return;
        }

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // constructs the folder where uploaded file will be stored
        String uploadFolder = getServletContext().getRealPath("")
                + File.separator + DATA_DIRECTORY;

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        try {
            // Parse the request
            List items = upload.parseRequest(req);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();

                    if (fileName.contains(".jpg") || fileName.contains(".jpeg") || fileName.contains(".png")) {
                        // Specify portraits directory folder
                        uploadFolder = root_directory + File.separator + PORTRAIT_DIRECTORY;

                        // Acquire file path & extension
                        String fileExtension = FilenameUtils.getExtension(fileName);
                        String filePath = uploadFolder + File.separator + username + "." + fileExtension;
                        File uploadedFile = new File(filePath);

                        System.out.println("\nFile successfully uploaded to path:");
                        System.out.println(filePath);
                        // Saves the image to "portraits" directory w/ username
                        item.write(uploadedFile);
                        item.delete();

                        EditUserPortraitController portraitController = new EditUserPortraitController();

                        // Update if User has photo, Insert if not
                        //if (portraitController.verifyUserHasPortrait(username)) {
                            portraitController.updateUserPortraitbyUsername(username, filePath);
                        //} else {
                        //    portraitController.insertUserPortraitbyUsername(username, filePath);
                        //}
                        resp.sendRedirect(req.getContextPath() + "/user");
                    } else {

                        garden_id = (int) req.getSession().getAttribute("GardenID");

                        if(garden_id != 0){
                            String filePath = uploadFolder + File.separator + fileName;
                            File uploadedFile = new File(filePath);
                            System.out.println("\nFile successfully uploaded to path:");
                            System.out.println(filePath);
                            // Save the file to garden "documents" directory w/ original filename
                            item.write(uploadedFile);

                            UploadController uploadController = new UploadController();

                            // Insert file path in database
                            uploadController.insertDocumentByGardenID("garden", garden_id, filePath);
                            resp.sendRedirect(req.getContextPath() + "/garden");
                        }
                        else {
                            String filePath = uploadFolder + File.separator + fileName;
                            File uploadedFile = new File(filePath);
                            System.out.println("\nFile successfully uploaded to path:");
                            System.out.println(filePath);
                            // Save the file to "documents" directory w/ original filename
                            item.write(uploadedFile);
                            item.delete();

                            // Displays upload success page in general case after upload finished
                            req.getRequestDispatcher("/_view/message.jsp").forward(req, resp);
                        }
                    }
                }
            }
        } catch(FileUploadException ex){
            throw new ServletException(ex);
        } catch(Exception ex){
            throw new ServletException(ex);
        }
        finally {
            System.gc();
        }
    }
}
