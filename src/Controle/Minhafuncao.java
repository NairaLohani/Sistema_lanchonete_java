
package Controle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Minhafuncao {
    public static int contarDados(String tableName){
        int total=0;
        Connection conn = Conexao.getConexao();
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS 'total' FROM "+tableName);
            while(rs.next()){
                total=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Minhafuncao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
}
