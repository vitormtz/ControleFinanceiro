/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import controlefinanceiro.negocio.Integrante;
import controlefinanceiro.negocio.Pessoa;
import controlefinanceiro.persistencia.IntegranteDAO;
import controlefinanceiro.persistencia.PessoaDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author vitor
 */
public class IntegranteDAOTest {

    private IntegranteDAO intDAO = new IntegranteDAO();
    private static Integrante integrante = new Integrante();
    private static Pessoa pessoa = new Pessoa();

    @BeforeClass
    public static void setUpClass() {
        pessoa.setId(0);
        pessoa.setNome("ana");
        pessoa.setEmail("ana.ana@hotmail.com");
        pessoa.setSenha("caca");

        new PessoaDAO().salvar(pessoa);

        pessoa.setId(new PessoaDAO().consultarUltimoId());

        integrante.setId(0);
        integrante.setPessoaId(pessoa.getId());
        integrante.setNome("Cleber");
    }

    @AfterClass
    public static void tearDownClass() {
        new PessoaDAO().excluir(pessoa.getId());
    }

    @Before
    public void setUp() {
        intDAO.salvar(integrante);
        integrante.setId(intDAO.consultarUltimoId());
    }

    @After
    public void tearDown() {
        intDAO.excluir(integrante.getId());
        integrante.setId(0);
    }

    @Test
    public void validarInsercaoNaTabelaIntegrante() {
        assertEquals("(Inserção na tabela Integrante foi mal sucedida) ", integrante.getNome(), intDAO.consultar(integrante.getId()).getNome());
    }

    @Test
    public void validarAlteracaoNaTabelaIntegrante() {
        integrante.setNome("elizangela da costa");

        intDAO.salvar(integrante);

        assertEquals("Alteração na tabela Integrante foi mal sucedida", integrante.getNome(), intDAO.consultar(integrante.getId()).getNome());
    }

    @Test
    public void validarExclusaoNaTabelaIntegrante() {
        intDAO.excluir(integrante.getId());

        assertNull("Exclusão na tabela Integrante foi mal sucedida", intDAO.consultar(integrante.getId()));
    }

    @Test
    public void validarVerificacaoNaTabelaIntegrante() {
        assertTrue("Verificação na tabela Integrante foi mal sucedida", intDAO.verificar(integrante.getNome()));
    }
}
