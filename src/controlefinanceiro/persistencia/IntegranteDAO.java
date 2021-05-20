/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.persistencia;

import controlefinanceiro.negocio.Integrante;
import controlefinanceiro.suporte.ConexaoBD;
import controlefinanceiro.suporte.IDAOT;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author vitor
 */
public class IntegranteDAO implements IDAOT<Integrante> {

    ResultSet resultadoQ = null;

    @Override
    public boolean salvar(Integrante o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "";

            sql = "INSERT INTO integrante VALUES ("
                    + "(SELECT COALESCE(MAX(id), 0) + 1 FROM integrante), "
                    + "" + o.getPessoaId() + ","
                    + "'" + o.getNome() + "')";

            System.out.println("SQL: " + sql + "\n");

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar pessoa: " + e + "\n");
            return false;
        }
    }

    @Override
    public Integrante consultar(String criterio) {
        Integrante integrante = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM integrante "
                    + "WHERE nome = '" + criterio + "'";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                integrante = new Integrante();

                integrante.setId(resultadoQ.getInt("id"));
                integrante.setPessoaId(resultadoQ.getInt("pessoa_id"));
                integrante.setNome(resultadoQ.getString("nome"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar pessoa: " + e + "\n");
        }
        return integrante;
    }

    public boolean verificar(String criterio) {
        Integrante integrante = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT EXISTS("
                    + "SELECT * "
                    + "FROM integrante "
                    + "WHERE nome = '" + criterio + "')";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            return resultadoQ.getBoolean("exists");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizar(Integrante o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integrante> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integrante consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer consultarUltimoId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
