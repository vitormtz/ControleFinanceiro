package entidade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controlefinanceiro.negocio.Pessoa;
import controlefinanceiro.persistencia.PessoaDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author vitor
 */
public class PessoaDAOTest {

    private PessoaDAO pesDAO = new PessoaDAO();
    private static Pessoa pessoa = new Pessoa();

    @BeforeClass
    public static void setUpClass() {
        pessoa.setId(0);
        pessoa.setNome("pedro pereira");
        pessoa.setEmail("pedro.pereira@hotmail.com");
        pessoa.setSenha("tete");
    }

    @Before
    public void setUp() {
        pesDAO.salvar(pessoa);
        pessoa.setId(pesDAO.consultarUltimoId());
    }

    @After
    public void tearDown() {
        pesDAO.excluir(pessoa.getId());
        pessoa.setId(0);
    }

    @Test
    public void validarInsercaoNaTabelaPessoa() {
        assertEquals("(Inserção na tabela Pessoa foi mal sucedida) ", pessoa.getEmail(), pesDAO.consultar(pessoa.getId()).getEmail());
    }

    @Test
    public void validarAlteracaoNaTabelaPessoa() {
        pessoa.setNome("joao cleber");

        pesDAO.salvar(pessoa);

        assertEquals("Alteração na tabela Pessoa foi mal sucedida", pessoa.getNome(), pesDAO.consultar(pessoa.getId()).getNome());
    }

    @Test
    public void validarExclusaoNaTabelaPessoa() {
        pesDAO.excluir(pessoa.getId());

        assertNull("Exclusão na tabela Pessoa foi mal sucedida", pesDAO.consultar(pessoa.getId()));
    }
}
