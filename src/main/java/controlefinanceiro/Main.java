/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro;

import controlefinanceiro.apresentacao.FrmLogin;
import controlefinanceiro.persistencia.PessoaDAO;

/**
 *
 * @author vitor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Esta é a aplicação do Vitor");
        System.out.println(new PessoaDAO().consultar(1).getEmail());

        //new FrmLogin().setVisible(true);
    }
}
