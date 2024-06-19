package entidade;


import entidade.constantes.TipoCaderno;

public class Caderno extends Produto{



    private TipoCaderno tipo;



    public TipoCaderno getTipo() {
        return tipo;
    }

    public void setTipo(TipoCaderno tipo) {
        this.tipo = tipo;
    }



    @Override
    public double calcularFrete() {
        return (getPreco() * getQuantidade()) + tipo.getFator();
    }


    @Override
    public String toString() {
        return "Caderno{" +
                " tipo=" + tipo +
                ", codigo='" + getCodigo() + '\'' +
                ", preço='" + getPreco() + '\'' +
                '}';
    }
}
