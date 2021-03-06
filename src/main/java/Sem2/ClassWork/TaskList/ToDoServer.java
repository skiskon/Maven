package Sem2.ClassWork.TaskList;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class ToDoServer {
    public static void main(String[] args) throws Exception {


        Server server = new Server(80);
        ServletContextHandler h = new ServletContextHandler();
        h.addServlet(ToDoServlet.class, "");
        h.addServlet(ToDoServlet.class, "/delete");
        h.addServlet(RestServlet.class,"/rest/*");
        h.addServlet(DefaultServlet.class,"/");
        h.setResourceBase("web");
        server.setHandler(h);
        server.start();

    }
}