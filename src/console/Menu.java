package console;


import basedados.Banco;
import entidade.*;
import entidade.constantes.TipoCaderno;
import negocio.ClienteNegocio;
import negocio.PedidoNegocio;
import negocio.ProdutoNegocio;
import utilidade.LeitoraDados;

import java.util.Optional;

public class Menu {


    private static Cliente clienteLogado = null;

    private static Banco banco = new Banco();

    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);


    public void menu (){
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Realizar cadastro" );
            System.out.println("2 - Realizar o login");
            System.out.println("3 - Sair");

            String escolha = LeitoraDados.lerDado();
            if (escolha.equals("1")){
                System.out.println("Digite seu nome: ");
                String nome = LeitoraDados.lerDado();
                System.out.println("Digite seu cpf: ");
                String cpf = LeitoraDados.lerDado();
                Cliente cliente = new Cliente(nome, cpf);
                clienteNegocio.salvar(cliente);
            }
            else if (escolha.equals("2")){
                System.out.println("Digite seu cpf: ");
                String cpf = LeitoraDados.lerDado();
                clienteNegocio.consultar(cpf);
                if (cpf.equals(null)){
                    System.out.println("Cliente não encontrado");
                }
                else {
                    System.out.println("Login realizado");
                    clienteLogado = clienteNegocio.consultar(cpf);
                    System.out.println(String.format("Olá %s! Você está logado.", clienteLogado.getNome()));

                    menu2();
                }

            }
            else if (escolha.equals("3")){
                System.out.println("Aplicação encerrada.");
                System.exit(0);
                break;
            }
            else {
                System.out.println("Escolha invalida");
            }

        }

    }


    public void menu2 (){
        String opcao = "";


        while(clienteLogado != null) {


            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Excluir Livro");
            System.out.println("3 - Cadastrar Caderno");
            System.out.println("4 - Excluir Caderno");
            System.out.println("5 - Fazer pedido");
            System.out.println("6 - Excluir pedido");
            System.out.println("7 - Listar produtos");
            System.out.println("8 - Listar pedidos");
            //TODO Desafio: Consultar Livro(nome)
            System.out.println("9 - Consultar livro");
            //TODO Desafio: Consultar Caderno(matéria)
            System.out.println("10 - Consultar materia");
            //TODO Desafio: Consultar Pedido(código)
            System.out.println("11 - Consultar pedido");
            System.out.println("12 - Deslogar");
            System.out.println("13 - Sair");

            opcao = LeitoraDados.lerDado();

            switch (opcao) {
                case "1":
                    Livro livro = LeitoraDados.lerLivro();
                    produtoNegocio.salvar(livro);
                    break;
                case "2":
                    System.out.println("Digite o código do livro");
                    String codigoLivro = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoLivro);
                    break;
                case "3":
                    //TODO Cadastrar Caderno
                    Caderno caderno = LeitoraDados.lerCaderno();
                    produtoNegocio.salvar(caderno);
                    break;
                case "4":
                    //TODO Excluir Caderno
                    System.out.println("Digite o código do caderno");
                    String codigoCaderno = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoCaderno);
                    break;

                case "5":
                    Pedido pedido = LeitoraDados.lerPedido(banco);
                    Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

                    if (cupom.isPresent()) {
                        pedidoNegocio.salvar(pedido, cupom.get(), clienteLogado);
                    } else {
                        pedidoNegocio.salvar(pedido, clienteLogado);
                    }
                    break;
                case "6":
                    System.out.println("Digite o código do pedido");
                    String codigoPedido = LeitoraDados.lerDado();
                    pedidoNegocio.excluir(codigoPedido);
                    break;
                case "7":
                    produtoNegocio.listarTodos();
                    break;
                case "8":
                    pedidoNegocio.listarPedidos();
                    break;

                case "9":
                    System.out.println("Digite o nome do livro: ");
                    String nome = LeitoraDados.lerDado();
                    produtoNegocio.consultarNome(nome);
                    break;

                case "10":
                    System.out.println("Digite a Materia (M2, M5  ou M10: ");
                    String materia = LeitoraDados.lerDado();
                    TipoCaderno tipo = TipoCaderno.valueOf(materia.toUpperCase());
                    produtoNegocio.consultarMateria(tipo);
                    break;

                case "11":
                    System.out.println("Digite o codigo do pedido");
                    String codigo = LeitoraDados.lerDado();
                    pedidoNegocio.consultarPedido(codigo);
                    break;
                case "12":
                    System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                    clienteLogado = null;

                    menu();
                case "13":
                    System.out.println("Aplicação encerrada.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

}


