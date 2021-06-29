/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.persistencia;

import controlefinanceiro.negocio.Receita;
import controlefinanceiro.suporte.ConexaoBD;
import controlefinanceiro.suporte.IDAOT;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author vitor
 */
public class ReceitaDAO implements IDAOT<Receita> {

    @Override
    public boolean salvar(Receita o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "";

            sql = "INSERT INTO receita VALUES ("
                    + "(SELECT COALESCE(MAX(id), 0) + 1 FROM receita), "
                    + "" + o.getPessoaId() + ", "
                    + "" + o.getIntegranteId() + ", "
                    + "" + o.getValor() + ", "
                    + "'" + o.getDescricao() + "', "
                    + "(SELECT current_timestamp))";

            System.out.println("SQL: " + sql + "\n");

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar pessoa: " + e + "\n");
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM receita "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql + "\n");

            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean atualizar(Receita o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Receita> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Receita consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Receita consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer consultarUltimoId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
