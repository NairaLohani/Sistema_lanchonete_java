/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controle.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Naira Lohani
 */
public class ClienteDAO {
    public void InserirAtualizarDeletarCliente(char operation, Integer id, String nome, String email,String telefone,String endereco){
    Connection conn = Conexao.getConexao();
        PreparedStatement ps;
        // "i" para inserir
        if(operation=='i'){
        try { 
            ps= conn.prepareStatement("INSERT INTO cliente(nome_cliente, email_cliente, telefone_cliente, endereco_cliente) VALUES (?,?,?,?)");
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            ps.setString(4, endereco);
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Novo Cliente Adicionado!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        // u para update
         if(operation=='u'){
        try { 
            ps= conn.prepareStatement("UPDATE `cliente` SET `nome_cliente`=?, `email_cliente`=?, `telefone_cliente`=?, `endereco_cliente`=? WHERE `id_cliente`= ?");
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            ps.setString(4, endereco);
            ps.setInt(5, id);
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Dados de Cliente Atualizados!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
         // d para delete 
         if(operation=='d'){
        try { 
            ps= conn.prepareStatement("DELETE FROM `cliente` WHERE `id_cliente`= ?");
            ps.setInt(1, id);
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Cliente Deletado!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
         
    }
    
    public void peencherTabelaCliente(JTable tabela, String valorPPesquisar){
        Connection conn= Conexao.getConexao();
        PreparedStatement ps;
        try {   
            ps = conn.prepareStatement("SELECT * FROM `cliente` WHERE CONCAT(`nome_cliente`,`email_cliente`,`telefone_cliente`,`endereco_cliente`) LIKE ?");
            ps.setString(1,"%"+valorPPesquisar+"%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)tabela.getModel();
            
            Object[] row;
            
            while (rs.next()) {
               row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
