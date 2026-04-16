import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import  java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/";
        String username="root";
        String password="8695";
        try{
            Connection con= DriverManager.getConnection(url,username,password);
            Statement st= con.createStatement();
//            st.executeUpdate("create database CollegeDatabase");
//            System.out.println("database created");
//            ResultSet rs=st.executeQuery("show databases");
//            while (rs.next()){
//                System.out.println(rs.getString(1));
//            }
            st.execute("use CollegeDatabase");
           String query= """
                   create table if not exists Student(
                   id int auto_increment primary key,
                   name varchar(30) not null,
                   course varchar(30),
                   marks double);
                   """;
//           st.executeUpdate(query);
//            System.out.println("table created successfully");
            String query2="insert into student(name,course,marks) values(?,?,?);";
            PreparedStatement ps=con.prepareStatement(query2);
//            ps.setString(1,"radwa");
//            ps.setString(2,"mca");
//            ps.setDouble(3,99.8);
//            ps.executeUpdate();
//            ps.setString(1,"khushi");
//            ps.setString(2,"mba");
//            ps.setDouble(3,99);
//            ps.executeUpdate();
//            System.out.println("values inserted successfully");
            String update="update student set marks=? where name=?;";
            PreparedStatement pst=con.prepareStatement(update);
            pst.setDouble(1,30);
            pst.setString(2,"khushi");
            pst.executeUpdate();

            ResultSet rs=ps.executeQuery("select * from student");
            while(rs.next()){
          int id=rs.getInt("id");
          String name=rs.getString("name");
          String course=rs.getString("course");
          double marks=rs.getDouble("marks");
              System.out.println(String.format("%d\t%s\t%s\t%f",id,name,course,marks));
            }
            ps.close();
            con.close();

            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
