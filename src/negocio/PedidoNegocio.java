package negocio;


import basedados.Banco;
import entidade.Cliente;
import entidade.Cupom;
import entidade.Pedido;
import entidade.Produto;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe para manipular a entidade {@link Pedido}.
 * @author thiago leite
 */
public class PedidoNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter armazenar e ter acesso os pedidos
     */
    public PedidoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    private double calcularTotal(List<Produto> produtos, Cupom cupom) {

        double total = 0.0;
        for (Produto produto: produtos) {
            total += produto.calcularFrete();
        }

        if (cupom != null) {
            return  total * (1 - cupom.getDesconto());
        } else {
            return  total;
        }

    }

    /**
     * Salva um novo pedido sem cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     */
    public void salvar(Pedido novoPedido, Cliente cliente) {
        salvar(novoPedido, null, cliente);
    }

    /**
     * Salva um novo pedido com cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     * @param cupom Cupom de desconto a ser utilizado
     */
    public void salvar(Pedido novoPedido, Cupom cupom, Cliente cliente) {


        String codigo = "PE%4d%2d%04d";
        LocalDate hoje = LocalDate.now();
        codigo = String.format(codigo,hoje.getYear(),hoje.getMonthValue(),bancoDados.getPedidos().length);

        //Setar código no pedido
        novoPedido.setCodigo(codigo);
        //Setar cliente no pedido
        novoPedido.setCliente(cliente);
        //Calcular e set total
        novoPedido.setTotal(calcularTotal(novoPedido.getProdutos(), cupom));

        //Adicionar no banco
        bancoDados.adicionarPedido(novoPedido);
        //Mensagem
        System.out.println("Pedido salvo com sucesso");
    }

    /**
     * Exclui um pedido a partir de seu código de rastreio.
     * @param codigo Código do pedido
     */
    public void excluir(String codigo) {

        int pedidoExclusao = -1;
        for (int i = 0; i < bancoDados.getPedidos().length; i++) {

            Pedido pedido = bancoDados.getPedidos()[i];
            if (pedido.getCodigo().equals(codigo)) {
                pedidoExclusao = i;
                break;
            }
        }

        if (pedidoExclusao != -1) {
            bancoDados.removerPedido(pedidoExclusao);
            System.out.println("Pedido excluído com sucesso.");
        } else {
            System.out.println("Pedido inexistente.");
        }
    }

    /**
     * Lista todos os pedidos realizados.
     */
    //TODO Método de listar todos os pedidos

    public void listarPedidos (){
        List<Pedido> pedidos = List.of(bancoDados.getPedidos());
        if (pedidos.isEmpty()){
            System.out.println("Não existem pedidos cadastrados");
        }
        else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido.toString());
            }
        }

    }

    public void consultarPedido (String codigo){
        List<Pedido> pedidos = List.of(bancoDados.getPedidos());

        for (Pedido pedido : pedidos){
            if (pedido.getCodigo().equals(codigo)){
                System.out.println(pedido.toString());
            }
            else {
                System.out.println("Pedido não encontrado");
            }
        }


    }

}
