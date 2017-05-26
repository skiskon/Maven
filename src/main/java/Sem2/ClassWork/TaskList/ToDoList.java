package Sem2.ClassWork.TaskList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ToDoList {

    void add(String task) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:h2:~/test")) {
            try (PreparedStatement ps = c.prepareStatement("insert into todo(text) values (?)")) {
                ps.setString(1, task);
                ps.executeUpdate();
            }
        }
    }

    void delete(int id) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:h2:~/test")) {
            try (PreparedStatement ps = c.prepareStatement("delete from todo where id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }


    List<Item> view() throws SQLException {
        List<Item> list = new ArrayList<>();
        try (Connection c = DriverManager.getConnection("jdbc:h2:~/test")) {
            try (PreparedStatement ps = c.prepareStatement("select id, text from todo order by id")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String text = rs.getString(2);
                        list.add(new Item(id, text));
                    }
                }
            }
        }
        return list;

    }

}
