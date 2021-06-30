/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.persistencia;

import controlefinanceiro.negocio.Despesa;
import controlefinanceiro.suporte.ConexaoBD;
import controlefinanceiro.suporte.IDAOT;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author vitor
 */
public class DespesaDAO implements IDAOT<Despesa> {

    ResultSet resultadoQ = null;

    @Override
    public boolean salvar(Despesa o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "";

            sql = "INSERT INTO despesa VALUES ("
                    + "(SELECT COALESCE(MAX(id), 0) + 1 FROM despesa), "
                    + "" + o.getPessoaId() + ", "
                    + "" + o.getIntegranteId() + ", "
                    + "" + o.getTipoDespesaId() + ", "
                    + "" + o.getValor() + ", "
                    + "'" + o.getDescricao() + "', "
                    + "(SELECT current_timestamp))";

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM despesa "
                    + "WHERE id = " + id;

            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean atualizar(Despesa o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Despesa> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Despesa consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Despesa consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer consultarUltimoId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
