package courses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class client extends DATABASE1 {

    String name_client;
    int age_client;
    String gender_client;
    String course_client;
    int phonenumber_client;
    String gmail_client;
   String date;
 // function"insert_toCourse" : insert to database values of name,age,course,phone , id and the gender of client .
    public static void insert_toCours(String name, String age, String gender, String course, String phone, String id,String date) throws ClassNotFoundException, SQLException {

        Connection conn = DATABASE1.getconnection();
        Statement stat = conn.createStatement();
       // search if id is found more than one  , dont crash the system . 
        ResultSet resultSet = stat.executeQuery("select *  from  ADDCOURSES  where ID = '" + id + "'");

        if (resultSet.next()) {
            System.out.println("error");

        } else {
           
    // we enter the values by user . by using preparedstatement.
            PreparedStatement ss;
            ss = conn.prepareStatement("INSERT INTO ADDCOURSES(NAME, AGE, courseandprice, ID, gender, phonenumber,date) VALUES(?,?,?,?,?,?,?) ");
            ss.setString(1, name);
            ss.setString(2, age);
            ss.setString(3, course);
            ss.setString(4, id);
            ss.setString(5, gender);
            ss.setString(6, phone);
            ss.setString(7, date);
            // close connection after insert.
            ss.execute();
           
        }

    }
    
    // search by id and delete all information about the client .
     
    public static String drop(String id) throws ClassNotFoundException, SQLException {
        Connection conn = DATABASE1.getconnection();
        Statement stat = conn.createStatement();
        
        
        String n;
             // select all information by enter id .
        ResultSet resultSet = stat.executeQuery("select *  from  ADDCOURSES  where ID = '" + id + "'");

        if (resultSet.next()) {
            PreparedStatement ss;
         //  ResultSet m = null;
            ss = conn.prepareStatement("delete  from  ADDCOURSES  where ID = ?");
            ss.setString(1, id);
            n="Succesfull";
          // update the table.
            ss.executeUpdate();
        } else {
            // when enter id not exist.
          n=("THE id is already not here ");
        }
return  n;
    }
     // select all information about the client  by the id .
    public static String search(String id) throws ClassNotFoundException, SQLException {

        Connection conn = DATABASE1.getconnection();
        Statement stat = conn.createStatement();
        String m ;
        ResultSet resultSet = stat.executeQuery("select *  from  ADDCOURSES  where ID = '" + id + "'");

        if (resultSet.next()) {
            // print  the information in label.  
            m = resultSet.getString(1) + "                    " + resultSet.getString(2) + "                " +           resultSet.getString(3) + "                  " +      resultSet.getString(5)     + "                      " + resultSet.getString(6) + "                     " +  resultSet.getString(4);

        } else {
            m = " id not exist in database";

        }

        return m;

    }
     // edite name about the client.
     public static String edite_name(String name,String id) throws ClassNotFoundException, SQLException {
         
           Connection conn = DATABASE1.getconnection();
        Statement stat = conn.createStatement();
String Done;
        ResultSet resultSet = stat.executeQuery("select *  from  ADDCOURSES  where ID = '" + id + "'");
           // if id not exist print error.
        if (!resultSet.next()) {
            
            System.out.println("error");
Done="NOT FOUND";
        } else {
        
                PreparedStatement ss;
                    // set  new name by known the id .  
                ss = conn.prepareStatement("UPDATE `ADDCOURSES` SET `NAME`=? WHERE ID='" + id + "' ");


                ss.setString(1, name);
                
                 ss.execute();
                 Done="DONE";
         
     }
        return Done;
}
            // edite course for the client.
   public static String edite_course(String course,String id) throws ClassNotFoundException, SQLException {
             
           Connection conn = DATABASE1.getconnection();
        Statement stat = conn.createStatement();
        String Done;
               // enter id by user.
        ResultSet resultSet = stat.executeQuery("select *  from  ADDCOURSES  where ID = '" + id + "'");
           // if id not exist print error 
        if (!resultSet.next()) {
            System.out.println("error");
            Done="NOT FOUND";
        } else {
        
                PreparedStatement ss;

                ss = conn.prepareStatement("UPDATE `ADDCOURSES` SET `courseandprice`=? WHERE ID='" + id + "' ");
                 
                ss.setString(1, course);
                 Done="DONE";
         ss.execute();
         
     }
         return Done;
}
             // edite phone number by id 
        public static String edite_phoneNumber(String phoneNumber,String id) throws ClassNotFoundException, SQLException {
         
           Connection conn = DATABASE1.getconnection();
        Statement stat = conn.createStatement();
        String Done;
          // enter id by user.
        ResultSet resultSet = stat.executeQuery("select *  from  ADDCOURSES  where ID = '" + id + "'");
           // if id not exist print error.
        if (!resultSet.next()) {
            System.out.println("error");
Done="NOT FOUND";
        } else {
        
                PreparedStatement ss;
                 // update phone number by id .
                ss = conn.prepareStatement("UPDATE `ADDCOURSES` SET `phonenumber`=? WHERE ID='" + id + "' ");


                ss.setString(1, phoneNumber);
                  Done="DONE";
              // update the table .
                 ss.execute(); 
        
         
     }
         return Done;
}










}
