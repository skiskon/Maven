package Sem2.ClassWork.TaskList;

import freemarker.cache.FileTemplateLoader;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ToDoServlet extends HttpServlet {

    Configuration cfg = new Configuration (Configuration.VERSION_2_3_26);
    {
        try {
            cfg.setTemplateLoader(new FileTemplateLoader(new File(".")));
            cfg.setOutputFormat(HTMLOutputFormat.INSTANCE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if ("/delete".equals(uri)){
            String str = req.getParameter("id");
            int id = Integer.parseInt(str);
            list.delete(id);
        }
        String what = req.getParameter("task");
        try {
            list.add(what);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        try {
            Template t= cfg.getTemplate("HTML.html");
            Map<String, Object> map= new HashMap<>();
            map.put("tasks", list.view());
            t.process(map, resp.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
        }
    }
    private ToDoList list=new ToDoList();

}