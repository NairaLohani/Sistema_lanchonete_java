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
public class GerenteDAO {
    public void InserirAtualizarDeletarGerente(char operation, Integer id, String nome, String email,String senha,String telefone,String cpf){
    Connection conn = Conexao.getConexao();
        PreparedStatement ps;
        // "i" para inserir
        if(operation=='i'){
        try { 
            ps= conn.prepareStatement("INSERT INTO `gerente`(`nome_gerente`, `email_gerente`, `senha_gerente`, `telefone_gerente`, `cpf_gerente`) VALUES (?,?,?,?,?)");
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, senha);
            ps.setString(4, telefone);
            ps.setString(5, cpf);
            
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Novo Gerente Adicionado!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        // u para update
         if(operation=='u'){
        try { 
            ps= conn.prepareStatement("UPDATE `gerente` SET ,`nome_gerente`=?,`email_gerente`=?,`senha_gerente`=?,`telefone_gerente`=?,`cpf_gerente`=? WHERE `id_gerente`=?");
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, senha);
            ps.setString(4, telefone);
            ps.setString(5, cpf);
            ps.setInt(6, id);
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Dados de Funcionário Atualizados!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
         // d para delete 
         if(operation=='d'){
        try { 
            ps= conn.prepareStatement("DELETE FROM `gerente` WHERE `id_gerente`= ?");
            ps.setInt(1, id);
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Gerente Deletado!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
         
    }
    
    public void peencherTabelaGerente(JTable tabela, String valorPPesquisar){
        Connection conn= Conexao.getConexao();
        PreparedStatement ps;
        try {   
            ps = conn.prepareStatement("SELECT * FROM `gerente` WHERE CONCAT(`nome_gerente`,`email_gerente`,`senha_gerente`,`telefone_gerente`,`cpf_gerente`) LIKE ?");
            ps.setString(1,"%"+valorPPesquisar+"%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)tabela.getModel();
            
            Object[] row;
            
            while (rs.next()) {
               row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
