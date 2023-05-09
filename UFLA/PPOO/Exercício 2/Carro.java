public class Carro {

    private String modelo;
    private int velocidade;

    public Carro(String ummodelo) {
        modelo = ummodelo;
        velocidade = 0;
    }

    public String getModelo() {
        return modelo;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void acelerar(int x) {
        velocidade = velocidade + (5 * x);
    }

    public void desacelerar(int x) {
        velocidade = velocidade - (5 * x);
    }
}
