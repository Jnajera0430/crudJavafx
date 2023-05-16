/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package senaproject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author auxsistemas3
 */
public class Conexionpg {

    private Connection conn;
    private final String url = "jdbc:postgresql://localhost:5432/sena?user=postgres&password=syd123";

    public Connection getConn() {
        return conn;
    }

    public Conexionpg() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexionpg.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dbClose() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexionpg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
