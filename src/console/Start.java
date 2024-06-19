package console;

/**
 * Classe responsável por controlar a execução da aplicação.
 */
public class Start {
    /**
     * Método utilitário para inicializar a aplicação.
     *
     * @param args Parâmetros que podem ser passados para auxiliar na execução.
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        System.out.println("Bem vindo ao e-Compras");
        menu.menu();
        menu.menu2();
    }
}