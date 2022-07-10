import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class JDBC {

    private static DataSource ds;

    static {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("assignment");
        dataSource.setUser("root");
        dataSource.setPassword("china2019zsj");

        ds = dataSource;
    }


//
//    static final String JDBCDriver = "com.mysql.cj.jdbc.Driver";
//    static final String DB_Url = "jdbc:mysql://localhost:3306/assignment";
//    static final String userName = "root";
//    static final String password = "china2019zsj";

    private static final String GET_ALL_INFO =
            "SELECT t.id as tid, t.name as t_name, salary, s.id as sid, s.name as s_name, teacher_id " +
                    "FROM teacher t inner join student s " +
                    "on t.student_id = s.id;";
    private static final String GET_COUNT_OF_STUDENT_PER_TEACHER = "SELECT COUNT(*) as stu_per_teacher, teacher_id FROM student GROUP BY teacher_id";
    private static final String GET_2ND_HIGHEST_SALARY = "with result as (\n" +
            "select salary, dense_rank() over (order by salary desc) as denserank\n" +
            "from teacher)\n" +
            "select salary \n" +
            "from result\n" +
            "where denserank = 2";

    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");

            conn = ds.getConnection();
            conn.setAutoCommit(false);
            System.out.println("Creating statement...");


            stmt = conn.prepareStatement(GET_2ND_HIGHEST_SALARY);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                /*
                get all info from teacher and student
                */
//                int id = rs.getInt("tid");
//                String teacherName = rs.getString("t_name");
//                int salary = rs.getInt("salary");
//                int studentId = rs.getInt("sid");
//                String studentName = rs.getString("s_name");
//                int teacherId = rs.getInt("teacher_id");
//                System.out.println(id+ " "+teacherName+" "+salary+" "+studentId+" "+studentName+" "+teacherId);

                /*
                get number of student per teacher
                */
//                int numOfStuPerTeacher = rs.getInt("stu_per_teacher");
//                int tid = rs.getInt("teacher_id");
//                System.out.println(numOfStuPerTeacher+" "+tid);

                /*
                get 2nd highest salary with dense_rank()
                */
                int salary = rs.getInt("salary");
                System.out.println(salary);
            }
            conn.commit();
            rs.close();
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(stmt!=null){
                    stmt.close();
                }
            }catch (SQLException e){

            }
            try {
                if(conn!=null){
                    conn.close();
                    System.out.println("shut down connection");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
