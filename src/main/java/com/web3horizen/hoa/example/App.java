package com.web3horizen.hoa.example;

import com.web3horizen.hoa.framework.WebApplication;

public class App extends WebApplication {
    public App() {
        //createDatabaseConnection();
    }

//    public void createDatabaseConnection() {
//        try {
//            String url = "jdbc:mysql://root:123@localhost:3306/neverlands";
//            Connection connection = DriverManager.getConnection(url);
//
//            Statement st = connection.createStatement();
//            ResultSet result = st.executeQuery("select * from user limit 1;");
//            ResultSetMetaData resultMeta = result.getMetaData();
//            while (result.next()) {
//                for(int i = 1; i < resultMeta.getColumnCount(); i++) {
//                    System.out.println(resultMeta.getColumnName(i));
//                    System.out.println(result.getString(i));
//                }
//            }
//
//            st.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
