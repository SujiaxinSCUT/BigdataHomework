import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFromSql {

    private static final String URL = "jdbc:mysql://bigdata28.depts.bingosoft.net:23307/user10_db?characterEncoding=UTF-8&useSSL=false";

    private static final String USERNAME = "user10";

    private static final String PASSWORD = "pass@bingo10";

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static String readFromSql(String sql){
        StringBuilder sb = new StringBuilder();
        List<String> cols = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection c = DriverManager.getConnection(
                    URL,USERNAME, PASSWORD);
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();
            for (int i = 0; i < colCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                cols.add("\""+columnName+"\"");
            }
            while(rs.next()) {
                sb.append("{");
                for (int i = 0; i < colCount; i++) {
                    String row_i = "\""+rs.getString(i+1)+"\"";
                    sb.append(cols.get(i)+":"+row_i);
                    if(i!=colCount-1)
                        sb.append(",");
                }
                sb.append("}\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(readFromSql("select * from t_rk_jbxx_result"));
    }
}
