/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.suporte;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author pretto
 */
public class CombosDAO {

    ResultSet resultadoQ = null;

    // construtor 1
    public void popularCombo(String tabela, int coluna, JComboBox combo, String complementoSQL, String excecao) {

        combo.removeAllItems();

        ComboItem item = new ComboItem();
        item.setCodigo(0);
        item.setDescricao("Selecione");
        combo.addItem(item);

        if (!excecao.isEmpty()) {
            item = new ComboItem();
            item.setDescricao(excecao.substring(0, 1).toUpperCase().concat(excecao.substring(1)));
            combo.addItem(item);
        }

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM " + tabela + " "
                    + complementoSQL;

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.isBeforeFirst()) {
                while (resultadoQ.next()) {
                    item = new ComboItem();
                    item.setCodigo(resultadoQ.getInt(1));
                    item.setDescricao(resultadoQ.getString(coluna).substring(0, 1).toUpperCase().concat(resultadoQ.getString(coluna).substring(1)));
                    combo.addItem(item);
                }
            }
        } catch (Exception e) {
        }
    }

    // construtor 2
    public void popularCombo(String tabela, String campo1, String campo2, JComboBox combo, String complementoSQL) {

        combo.removeAllItems();

        ComboItem item = new ComboItem();
        item.setCodigo(0);
        item.setDescricao("Selecione");
        combo.addItem(item);

        try {
            resultadoQ = new ConexaoBD().getConnection().createStatement().executeQuery("select * from " + tabela + " " + complementoSQL);

            if (resultadoQ.isBeforeFirst()) {
                while (resultadoQ.next()) {
                    item = new ComboItem();
                    item.setCodigo(resultadoQ.getInt(campo1));
                    item.setDescricao(resultadoQ.getString(campo2));

                    combo.addItem(item);
                }
            }
        } catch (Exception e) {
        }
    }

    public void definirItemCombo(JComboBox combo, ComboItem item) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (((ComboItem) combo.getItemAt(i)).getCodigo() == (item.getCodigo())) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }
}
