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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

            if (o.getId() == 0) {
                sql = "INSERT INTO integrante VALUES ("
                        + "(SELECT COALESCE(MAX(id), 0) + 1 FROM integrante), "
                        + "" + o.getPessoaId() + ","
                        + "'" + o.getNome() + "')";
            } else {
                sql = "UPDATE integrante SET "
                        + "nome = '" + o.getNome() + "' "
                        + "WHERE id = " + o.getId();
            }

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
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

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                integrante = new Integrante();

                integrante.setId(resultadoQ.getInt("id"));
                integrante.setPessoaId(resultadoQ.getInt("pessoa_id"));
                integrante.setNome(resultadoQ.getString("nome"));
            }
        } catch (Exception e) {
        }
        return integrante;
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM integrante "
                    + "WHERE id = " + id;

            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verificar(String criterio) {
        Integrante integrante = null;

        if (System.getProperty("Login") != null) {
            criterio += "' AND pessoa_id = " + System.getProperty("Login") + ")";
        } else {
            criterio += "')";
        }

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT EXISTS("
                    + "SELECT * "
                    + "FROM integrante "
                    + "WHERE nome = '" + criterio;

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            return resultadoQ.getBoolean("exists");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT COUNT(*) "
                    + "FROM( "
                    + "SELECT i.id, i.nome "
                    + "FROM integrante i "
                    + "WHERE i.pessoa_id = " + System.getProperty("Login") + " "
                    + "UNION "
                    + "SELECT p.id, p.nome "
                    + "FROM pessoa p "
                    + "WHERE p.id = " + System.getProperty("Login") + " "
                    + ") as resultado "
                    + "WHERE resultado.nome ILIKE '%" + criterio + "%' ";

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][2];

        } catch (Exception e) {
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM( "
                    + "SELECT i.id, i.nome "
                    + "FROM integrante i "
                    + "WHERE i.pessoa_id = " + System.getProperty("Login") + " "
                    + "UNION "
                    + "SELECT p.id, p.nome "
                    + "FROM pessoa p "
                    + "WHERE p.id = " + System.getProperty("Login") + " "
                    + ") as resultado "
                    + "WHERE resultado.nome ILIKE '%" + criterio + "%' "
                    + "ORDER BY resultado.id";

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getString("id");
                if (System.getProperty("Login").equals(resultadoQ.getString("id"))
                        && new PessoaDAO().consultar(Integer.parseInt(System.getProperty("Login"))).getNome().equals(resultadoQ.getString("nome"))) {
                    dadosTabela[lin][1] = resultadoQ.getString("nome") + " (Admin)";
                } else {
                    dadosTabela[lin][1] = resultadoQ.getString("nome");
                }
                lin++;
            }
        } catch (Exception e) {
        }

        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tabela.setSelectionMode(0);
    }

    @Override
    public boolean atualizar(Integrante o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integrante> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integrante consultar(int id) {
        Integrante integrante = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM integrante "
                    + "WHERE id = " + id;

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                integrante = new Integrante();

                integrante.setId(resultadoQ.getInt("id"));
                integrante.setPessoaId(resultadoQ.getInt("pessoa_id"));
                integrante.setNome(resultadoQ.getString("nome"));
            }
        } catch (Exception e) {
        }
        return integrante;
    }

    @Override
    public Integer consultarUltimoId() {
        Integrante integrante = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT MAX(id) AS id "
                    + "FROM integrante ";

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            return Integer.parseInt(resultadoQ.getString("id"));
        } catch (Exception e) {
            return -1;
        }
    }
}
