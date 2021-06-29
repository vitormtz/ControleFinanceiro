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
public final class JtextFieldSomenteNumeros extends JTextField {

    private int maximoCaracteres = -1;
    private boolean decimal;
    private String caracteres = "0987654321";

    public JtextFieldSomenteNumeros() {
        super();
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldKeyTyped(evt);
            }
        });
    }

    public JtextFieldSomenteNumeros(int maximo, boolean decimal) {
        super();
        setMaximoCaracteres(maximo);

        this.decimal = decimal;
        if (this.decimal) {
            caracteres += ",";
        }
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldKeyTyped(evt);
            }
        });
    }

    private void jTextFieldKeyTyped(KeyEvent evt) {
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
        if (decimal) {
            if (evt.getKeyChar() == ','
                    && (getCaretPosition() == 0
                    || getText().contains(",")
                    || (getText().length() - getCaretPosition()) > 2)) {
                evt.consume();
            }
            if (getText().contains(",")
                    && (getText().length() - getText().indexOf(',')) == 3
                    && getText().indexOf(',') < getCaretPosition()) {
                evt.consume();
            }

        }

        if ((getText().length() >= getMaximoCaracteres()) && (getMaximoCaracteres() != -1)) {
            evt.consume();
            setText(getText().substring(0, getMaximoCaracteres()));
        }

        if (evt.getKeyChar() == ','
                && getText().length() + 1 == getMaximoCaracteres()) {
            evt.consume();         
        }
    }

    public int getMaximoCaracteres() {
        return maximoCaracteres;
    }

    public void setMaximoCaracteres(int maximoCaracteres) {
        this.maximoCaracteres = maximoCaracteres;
    }
}
