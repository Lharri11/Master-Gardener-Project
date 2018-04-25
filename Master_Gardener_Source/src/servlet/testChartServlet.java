package servlet;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        import com.google.gson.Gson;
        import model.Student;

@WebServlet("/StudentJsonDataServlet")
public class testChartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public testChartServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        List<Student> listOfStudent = getStudentData();

        Gson gson = new Gson();

        String jsonString = gson.toJson(listOfStudent);

        response.setContentType("application/json");

        //response.getWriter().write(jsonString);

    }

    private List<Student> getStudentData() {

        List<Student> listOfStudent = new ArrayList<Student>();
        Student s1 = new Student();
        s1.setName("Sandeep");
        s1.setComputer_mark(75);
        s1.setMathematics_mark(26);
        s1.setGeography_mark(91);
        s1.setHistory_mark(55);
        s1.setLiterature_mark(36);
        listOfStudent.add(s1);

        return listOfStudent;
    }
}