/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.persistencia;

import controlefinanceiro.negocio.Pessoa;
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
public class PessoaDAO implements IDAOT<Pessoa> {

    ResultSet resultadoQ = null;

    @Override
    public Pessoa consultar(String email) {
        Pessoa pessoa = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM pessoa "
                    + "WHERE email = '" + email + "'";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                pessoa = new Pessoa();

                pessoa.setId(resultadoQ.getInt("id"));
                pessoa.setNome(resultadoQ.getString("nome"));
                pessoa.setEmail(resultadoQ.getString("email"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar pessoa: " + e + "\n");
        }
        return pessoa;
    }

    @Override
    public Pessoa consultar(int id) {
        Pessoa pessoa = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM pessoa "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                pessoa = new Pessoa();

                pessoa.setId(resultadoQ.getInt("id"));
                pessoa.setNome(resultadoQ.getString("nome"));
                pessoa.setEmail(resultadoQ.getString("email"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar pessoa: " + e + "\n");
        }
        return pessoa;
    }

    public boolean autenticarSenha(String email, String senha) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM pessoa "
                    + "WHERE email = '" + email + "' "
                    + "AND senha = md5('" + senha + "')";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            return resultadoQ.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[8];
        cabecalho[0] = "Tipo (Subtipo)";
        cabecalho[1] = "Local Aplicado";
        cabecalho[2] = "Nome";
        cabecalho[3] = "Valor";

        String parametro = "resultado.nome ILIKE '%" + criterio + "%'";
        if (criterio.toLowerCase().equals("receita")) {
            parametro = "resultado.tipo = ''";
        } else if (criterio.toLowerCase().equals("despesa")) {
            parametro = "resultado.tipo != ''";
        }
        parametro += " AND (resultado.pessoa_id = " + System.getProperty("Login") + " OR resultado.integrante_id = " + System.getProperty("Login") + ")";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT COUNT(*) "
                    + "FROM ((SELECT NULL AS pessoa_id, i.pessoa_id AS integrante_id, d.id AS transacao_id, td.descricao AS tipo, d.descricao AS \"local_aplicado\", i.nome, d.valor, d.data_acao "
                    + "FROM despesa d INNER JOIN integrante i ON d.integrante_id = i.id "
                    + "INNER JOIN tipo_despesa td ON d.tipo_despesa_id = td.id "
                    + "UNION ALL "
                    + "SELECT p.id, NULL AS integrante_id, d.id, td.descricao, d.descricao, p.nome, d.valor, d.data_acao "
                    + "FROM despesa d INNER JOIN pessoa p ON d.pessoa_id = p.id "
                    + "INNER JOIN tipo_despesa td ON d.tipo_despesa_id = td.id) "
                    + "UNION ALL "
                    + "(SELECT NULL AS pessoa_id, i.pessoa_id, r.id, '' AS descricao, r.descricao, i.nome, r.valor, r.data_acao "
                    + "FROM receita r INNER JOIN integrante i ON r.integrante_id = i.id "
                    + "UNION ALL "
                    + "SELECT p.id, NULL AS integrante_id, r.id, '' AS descricao, r.descricao, p.nome, r.valor, r.data_acao "
                    + "FROM receita r INNER JOIN pessoa p ON r.pessoa_id = p.id)) AS resultado "
                    + "WHERE " + parametro;

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar a tabela: " + e + "\n");
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM ((SELECT NULL AS pessoa_id, i.pessoa_id AS integrante_id, d.id AS transacao_id, td.descricao AS tipo, d.descricao AS \"local_aplicado\", i.nome, d.valor, d.data_acao "
                    + "FROM despesa d INNER JOIN integrante i ON d.integrante_id = i.id "
                    + "INNER JOIN tipo_despesa td ON d.tipo_despesa_id = td.id "
                    + "UNION ALL "
                    + "SELECT p.id, NULL AS integrante_id, d.id, td.descricao, d.descricao, p.nome, d.valor, d.data_acao "
                    + "FROM despesa d INNER JOIN pessoa p ON d.pessoa_id = p.id "
                    + "INNER JOIN tipo_despesa td ON d.tipo_despesa_id = td.id) "
                    + "UNION ALL "
                    + "(SELECT NULL AS pessoa_id, i.pessoa_id, r.id, '' AS descricao, r.descricao, i.nome, r.valor, r.data_acao "
                    + "FROM receita r INNER JOIN integrante i ON r.integrante_id = i.id "
                    + "UNION ALL "
                    + "SELECT p.id, NULL AS integrante_id, r.id, '' AS descricao, r.descricao, p.nome, r.valor, r.data_acao "
                    + "FROM receita r INNER JOIN pessoa p ON r.pessoa_id = p.id)) AS resultado "
                    + "WHERE " + parametro + " "
                    + "ORDER BY resultado.data_acao";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                if (resultadoQ.getString("tipo").isEmpty()) {
                    dadosTabela[lin][0] = "Receita";
                    dadosTabela[lin][3] = "R$ +" + resultadoQ.getString("valor").replace('.', ',');
                } else {
                    dadosTabela[lin][0] = "Despesa (" + resultadoQ.getString("tipo") + ")";
                    dadosTabela[lin][3] = "R$ -" + resultadoQ.getString("valor").replace('.', ',');
                }
                dadosTabela[lin][1] = resultadoQ.getString("local_aplicado");
                dadosTabela[lin][2] = resultadoQ.getString("nome");

                dadosTabela[lin][4] = resultadoQ.getString("pessoa_id");
                dadosTabela[lin][5] = resultadoQ.getString("integrante_id");
                dadosTabela[lin][6] = resultadoQ.getString("transacao_id");
                dadosTabela[lin][7] = resultadoQ.getString("data_acao");

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular a tabela ...\n");
        }

        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tabela.removeColumn(tabela.getColumnModel().getColumn(7));
        tabela.removeColumn(tabela.getColumnModel().getColumn(6));
        tabela.removeColumn(tabela.getColumnModel().getColumn(5));
        tabela.removeColumn(tabela.getColumnModel().getColumn(4));

        tabela.setSelectionMode(0);
    }

    @Override
    public boolean salvar(Pessoa o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizar(Pessoa o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Pessoa> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer consultarUltimoId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
