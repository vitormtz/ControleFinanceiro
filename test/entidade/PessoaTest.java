package entidade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controlefinanceiro.negocio.Pessoa;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vitor
 */
public class PessoaTest {

    Pessoa pessoa = new Pessoa();

    @Test
    public void validarNomeNull() {
        pessoa.setNome(null);

        assertNotNull("(Nome = null) ", pessoa.getNome());
    }

    @Test
    public void validarNomeComNumero() {

        pessoa.setNome("0123456789");

        if (pessoa.getNome().matches(".+\\d.+")) {
            assertFalse("(Nome contém número) ", true);
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void validarNomeMaiorQue50() {

        for (int i = 0; i < 51; i++) {
            pessoa.setNome(pessoa.getNome() + (char) 97);
        }
        assertEquals("(Nome > 50) ", 0, pessoa.getNome().length(), 50);
    }
}
