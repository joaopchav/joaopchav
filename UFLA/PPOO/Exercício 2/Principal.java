import java.util.Scanner;

public class Principal {

    private Carro[] carros = new Carro[5];

    public Boolean comparar(String novo_carro) {

        int contador = 0;

        for (int i = 0; i < carros.length; i++) {
            if (novo_carro.equals(carros[i].getModelo())) {
                contador++;
            }
        }
        if (contador > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void executar(Carro carro) {

        Scanner entrada = new Scanner(System.in);
        int tamanho = 0;
        int opcao = 0;

        for (int i = 0; i < carros.length; i++) {
            carros[i] = new Carro("");
        }

        do {
            System.out.println("------------- MENU --------------");
            System.out.println("----- SELECIONE UMA OPÇÃO -------");
            System.out.println("------- 1) CRIAR O CARRO --------");
            System.out.println("---------- 2) ACELERAR ----------");
            System.out.println("---------- 3) REDUZIR -----------");
            System.out.println("----------- 4) EXIBIR -----------");
            System.out.println("------------ 5) SAIR ------------");
            opcao = Integer.parseInt(entrada.nextLine());

            if (opcao == 1 && tamanho != 5) {

                System.out.println("Cadastre até 5 carros");
                if (tamanho < carros.length) {
                    String modelo = entrada.nextLine();
                    carros[tamanho] = new Carro(modelo);
                    tamanho++;
                }

            } else if (opcao == 2) {

                System.out.println("Qual carro você quer acelerar?");
                String qual = entrada.nextLine();
                if (comparar(qual) == true) {

                    System.out.println("Quantas vezes você quer acelerar?");
                    int aceleradas = Integer.parseInt(entrada.nextLine());

                    for (int i = 0; i < carros.length; i++) {
                        Carro n = carros[i];
                        if (qual.equals(n.getModelo()))
                            n.acelerar(aceleradas);
                    }
                } else {
                    System.out.println("Esse carro não foi cadastrado!");
                }
            } else if (opcao == 3) {

                System.out.println("Qual carro você quer desacelerar?");
                String qual = entrada.nextLine();
                if (comparar(qual) == true) {
                    System.out.println("Quantas vezes você quer desacelerar?");
                    int reduzir = Integer.parseInt(entrada.nextLine());

                    for (int i = 0; i < carros.length; i++) {
                        Carro n = carros[i];
                        if (qual.equals(n.getModelo()))
                            n.desacelerar(reduzir);
                    }
                } else {
                    System.out.println("Esse carro não foi cadastrado");
                }

            } else if (opcao == 4) {
                System.out.println("Qual carro você quer exibir?");
                String qual_carro = entrada.nextLine();
                if (comparar(qual_carro) == true) {
                    for (int i = 0; i < carros.length; i++) {
                        if (qual_carro.equals(carros[i].getModelo())) {
                            System.out.println("------- DADOS DO CARRO -------");
                            System.out.println(carros[i].getModelo());
                            System.out.println(carros[i].getVelocidade());
                        }
                    }
                } else {
                    System.out.println("Esse carro não foi cadastrado!");
                }
            }
        } while (opcao != 5);

        System.out.println("Adeus o/");

        entrada.close();
    }
}
