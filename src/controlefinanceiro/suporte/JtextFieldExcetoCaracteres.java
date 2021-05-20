/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.suporte;

import javax.swing.JTextField;
import java.awt.event.KeyEvent;

/**
 *
 * @author Vitor Martinez
 */
public class JtextFieldExcetoCaracteres extends JTextField {

    private int maximoCaracteres = -1;
    private String caracteres = "";

    public JtextFieldExcetoCaracteres(int maximo, String caracteres) {
        super();
        setMaximoCaracteres(maximo);

        if (!caracteres.isEmpty()) {
            this.caracteres = caracteres;
        }

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldKeyTyped(evt);
            }
        });
    }

    private void jTextFieldKeyTyped(KeyEvent evt) {

        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
        if ((getText().length() >= getMaximoCaracteres()) && (getMaximoCaracteres() != -1)) {

            evt.consume();
            setText(getText().substring(0, getMaximoCaracteres()));
        }
    }

    public int getMaximoCaracteres() {
        return maximoCaracteres;
    }

    public void setMaximoCaracteres(int maximoCaracteres) {
        this.maximoCaracteres = maximoCaracteres;
    }
}
