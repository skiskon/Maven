package Sem2.ClassWork.TaskList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 21.04.2017.
 */
public class ToDoServlet extends HttpServlet {
    private ToDoList list = new ToDoList();

    {
        list.add("Привет");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        List<Item> items = list.view();
        for (Item i : items) {
            sb.append("<li>" + i.text + "</li>\n");
        }

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Список задач</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"post\">\n" +
                "    Задача: <input name=\"task\">\n" +
                "    <input type=\"submit\" value=\"добавить\">\n" +
                "</form>\n" +
                "<ol>\n" +
               sb +
                "</ol>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String instring = req.getParameter("task");
        list.add(instring);
        resp.sendRedirect("/");


    }
}
