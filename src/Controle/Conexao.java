/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Naira Lohani
 */
public class Conexao {
    public static Connection getConexao(){
        Connection conn = null;
        try{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_lanchonete","root","");
            System.out.println("Conectado com sucesso!");
        }catch(SQLException e ){
            System.err.println("Erro ao conectar!");
        }
        return conn;
       
                
    }
     public static void main(String [] args){
            Conexao.getConexao();
        }
}
