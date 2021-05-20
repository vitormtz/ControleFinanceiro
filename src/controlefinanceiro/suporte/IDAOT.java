/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.suporte;

import java.util.ArrayList;

/**
 *
 * @author pretto
 */

// Utiliza Generics como tipo de dado

public interface IDAOT <T> {

    public boolean salvar(T o);

    public boolean atualizar(T o);

    public boolean excluir(int id);

    public ArrayList<T> consultarTodos();

    public T consultar(String criterio);

    public T consultar(int id);
    
    public Integer consultarUltimoId();
}
