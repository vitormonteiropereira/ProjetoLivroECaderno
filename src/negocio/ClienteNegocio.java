package negocio;


import basedados.Banco;
import entidade.Cliente;

import java.util.List;

/**
 * Classe para manipular a entidade {@link Cliente}.
 * @author thiago leite
 */
public class ClienteNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter acesso aos clientes cadastrados
     */
    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Consulta o cliente pelo seu CPF.
     * @param cpf CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     */


    public Cliente consultar (String cpf){
        List<Cliente> clientes = bancoDados.getClientes();
        for (Cliente cliente : clientes){
            if (cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;

    }




        /**
         * Cadastra um novo cliente.
         * @param cliente Novo cliente que terá acesso a aplicação
         */
    //TODO Fazer a inclusão de cliente

    public void salvar (Cliente cliente){
        bancoDados.adicionarCliente(cliente);
        System.out.println("Adicionado com sucesso");
    }


    /**
     * Exclui um cliente específico.
     * @param cpf CPF do cliente
     */
    //TODO Fazer a exclusão de cliente

    public void excluir(String cpf){
        List<Cliente> clientes = bancoDados.getClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                bancoDados.removerCliente(cliente);
            }
            System.out.println("Cliente não encontrado");
        }

    }


}
