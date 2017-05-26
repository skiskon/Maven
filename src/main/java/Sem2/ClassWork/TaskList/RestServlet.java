package Sem2.ClassWork.TaskList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.impl.bootstrap.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 19.05.2017.
 */
public class RestServlet extends HttpServlet {
    ToDoList list = new ToDoList();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String uri = req.getRequestURI();
        try{
            if (uri.startsWith("/rest/view")){
                gson.toJson(list.view(),resp.getWriter());
            }
            else if (uri.startsWith("/rest/add")){
                String text = req.getParameter("text");
                list.add(text);
                gson.toJson(list.view(),resp.getWriter());
            }
            else if (uri.startsWith("/rest/delete")){
                String str = req.getParameter("id");
                int id = Integer.parseInt(str);
                list.delete(id);
                gson.toJson(list.view(),resp.getWriter());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
