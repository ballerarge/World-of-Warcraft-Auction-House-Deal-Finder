package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    private final int NUMITEMS = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "";
        String urlFront = "http://www.wowhead.com/item=128476";
        String urlEnd = "&xml";

        url = urlFront + urlEnd;
        new Utils.XMLTask().execute(url);

//        for (int i = 100000; i < 200000; i++) {
//            url = urlFront + Integer.toString(i) + urlEnd;
//            new Utils.XMLTask().execute(url);
//        }
//        new Connect().execute();


    }

//    public Connection getConnection() throws SQLException {
//
//        Connection conn = null;
//        Properties connectionProps = new Properties();
//        //connectionProps.put("user", this.userName);
//        //connectionProps.put("password", this.password);
//        conn = DriverManager.getConnection(
//                "jdbc:" + "mysql" + "://" +
//                        "newswire.theunderminejournal.com:3306/" +
////                        ":" + "3306" + "/",
//                        connectionProps);
//
//        System.out.println("Connected to database");
//        return conn;
//    }
//
//    public static void viewTable(Connection con, String dbName)
//            throws SQLException {
//
//        Statement stmt = null;
//        String query = "select AVG(a.priceavg) AS Memes " +
//                "from (select priceavg from tblItemHistoryDaily " +
//                "where house = 8 AND item = 124442 " +
//                "order by tblItemHistoryDaily.when DESC " +
//                "limit 14) a";
//        try {
//            stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                String coffeeName = rs.getString("Memes");
//                System.out.println(coffeeName);
//            }
//        } catch (SQLException e ) {
//            e.printStackTrace();
//        } finally {
//            if (stmt != null) { stmt.close(); }
//        }
//    }

//    private class Connect extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//            String response = "";
//
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection con = DriverManager.getConnection(url, user, pass);
//                // System.out.println("Database connection success");
//
//                String result = "Database connection success\n";
//                Statement st = con.createStatement();
//                ResultSet rs = st.executeQuery("select AVG(a.priceavg) AS Memes " +
//                        "from (select priceavg from tblItemHistoryDaily" +
//                        "where house = 8 AND item = 124442" +
//                        "order by tblItemHistoryDaily.when DESC" +
//                        "limit 14) a");
//                ResultSetMetaData rsmd = rs.getMetaData();
//
//                while(rs.next()) {
//                    String coffeeName = rs.getString("Memes");
//                    System.out.println(coffeeName);
//                }
//            }
//            catch(Exception e) {
//                e.printStackTrace();
//            }
//            return response;
//
//
//        }
//
//
//}
//
//    protected void onPostExecute(Connection con) {
//        Statement stmt = null;
//        String query = "select AVG(a.priceavg) AS Memes " +
//                "from (select priceavg from tblItemHistoryDaily " +
//                "where house = 8 AND item = 124442 " +
//                "order by tblItemHistoryDaily.when DESC " +
//                "limit 14) a";
//        try {
//            stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                String coffeeName = rs.getString("Memes");
//                System.out.println(coffeeName);
//            }
//        } catch (SQLException e ) {
//            e.printStackTrace();
//        } finally {
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
