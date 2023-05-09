import java.util.Scanner;
public class CaixaEletronico {


    ContaBancaria conta;
    ContaBancaria conta1;
    Cliente cliente;
    Scanner entrada;
    
    
    public void menu(){
        
        int opcao = 0;

        do{
        System.out.println("--- Menu ---");
        System.out.println("1- Criar Conta");
        System.out.println("2- Consultar Saldo");
        System.out.println("3- Depositar");
        System.out.println("4- Sacar");
        System.out.println("5- Transferir");
        System.out.println("6- Sair");

        entrada = new Scanner(System.in);
        opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1:

                criarContas();
        
                break;

                case 2:
                
                consultarSaldo();
                break;

                case 3:
                
                DepositarValor();
                break;

                case 4:
                
                sacarValor();
                break;

                case 5:

                Transferir();
                break;

                default:
                System.out.println("Opção Inválida!");
                break;

            } 
        }while(opcao !=6);{
            System.out.println("Saindo...");
        }
    }

    public void consultarSaldo() {
        if(conta != null){

            System.out.println("Qual o numero da conta que você deseja consultar o saldo?");
            int opcao = Integer.parseInt(entrada.nextLine());

            if(opcao == conta.getNumConta()){
                System.out.println("--- Conta 1 ---");
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Saldo: " + conta.getSaldo());
            }
            else if(opcao == conta1.getNumConta()){
                System.out.println("--- Conta 2 ---");
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Saldo: " + conta1.getSaldo());
            }
            else{
                System.out.println("Não existe uma conta com esse número");
            }
        } else{
            System.out.println("Nenhuma conta foi criada!");
        }
    }

    public void criarContas() {

        System.out.print("Informe seu nome: ");
        String nome = entrada.nextLine();
        System.out.print("Informe seu CPF: ");
        String CPF = entrada.nextLine();
        cliente = new Cliente(nome,CPF);

        System.out.println("Você deseja declarar um saldo inicial em sua primeira conta?");
        System.out.println("1- Sim");
        System.out.println("2- Não");
        int opcao1 = Integer.parseInt(entrada.nextLine());

        if(opcao1 == 1){

            System.out.println("--- Dados da primeira conta ---");
            System.out.print("Digite o saldo inicial: ");
            double saldoinicial = Double.parseDouble(entrada.nextLine());
            conta = new ContaBancaria(cliente, saldoinicial);

        }else if(opcao1 == 2){
            conta = new ContaBancaria(cliente);
        }

        System.out.println("Você deseja declarar um saldo inicial em sua segunda conta?");
        System.out.println("1- Sim");
        System.out.println("2- Não");
        opcao1 = Integer.parseInt(entrada.nextLine());

        if(opcao1 == 1){

            System.out.println("--- Dados da segunda conta ---");
            System.out.print("Digite o saldo inicial: ");
            double saldoinicial = Double.parseDouble(entrada.nextLine());
            conta1 = new ContaBancaria(cliente, saldoinicial);
            System.out.println("Para acessar sua primeira conta: " + conta.getNumConta());
            System.out.println("Para acessar sua segunda conta: " + conta1.getNumConta());
        }else if(opcao1 == 2){
            conta1 = new ContaBancaria(cliente);
            System.out.println("Para acessar sua primeira conta: " + conta.getNumConta());
            System.out.println("Para acessar sua segunda conta: " + conta1.getNumConta());
        }

    }

    public void DepositarValor() {
        if (conta != null) {
            System.out.println("Qual o número da conta em que deseja depositar? ");
            int opcao = Integer.parseInt(entrada.nextLine());
            if (opcao == conta.getNumConta()) {
                System.out.println("Quanto deseja depositar em sua conta?");
                int valor = Integer.parseInt(entrada.nextLine());
                if(valor>0){
                    conta.deposito(valor);
                }else{
                    System.out.println("Você não pode depositar esse valor!");
                }
            } else if (opcao == conta1.getNumConta()) {

                System.out.println("Quanto deseja depositar em sua conta?");
                int valor = Integer.parseInt(entrada.nextLine());

                if(valor>0){

                    conta1.deposito(valor);

                }else{
                    System.out.println("Você não pode depositar esse valor!");
                }
            } else {
                System.out.println("Não existe uma conta com esse número!");
            }
        } else {
            System.out.println("Nenhuma conta foi criada!");
        }
    }

    public void sacarValor() {
        
        if (conta != null) {
            System.out.println("Qual o número da conta que deseja sacar? ");
            int opcao = Integer.parseInt(entrada.nextLine());
            
            if (opcao == conta.getNumConta()) {
                System.out.println("Quanto deseja sacar de sua conta?");
                Double valor = Double.parseDouble(entrada.nextLine());
                conta.saque(valor);

                if (conta.saque(valor) == false){
                    System.out.println("Saque não permitido!");
                }

            } else if (opcao == conta1.getNumConta()) {
                System.out.println("Quanto deseja sacar de sua conta?");
                Double valor = Double.parseDouble(entrada.nextLine());
                conta1.saque(valor);

                if (conta1.saque(valor) == false){
                    System.out.println("Saque não permitido!");
                }

            } else {
                System.out.println("Não existe uma conta com esse número!");
            }
        } else {
            System.out.println("Nenhuma conta foi criada!");
        }
    }

    public void Transferir(){

        if (conta != null || conta1 != null) {
            System.out.print("Qual a conta de origem: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            System.out.print("Qual a conta de destino: ");
            int opcao1 = Integer.parseInt(entrada.nextLine());
            if(opcao == conta.getNumConta() && opcao1 == conta1.getNumConta()){
                
                System.out.println("Quanto deseja transferir pra conta 101? ");
                double valor = Double.parseDouble(entrada.nextLine());
                boolean transferido = conta.transferir(valor, conta, conta1);

                if (transferido == true);{
                System.out.println("Transferido!");
                }
                if(transferido == false){
                    System.out.println("Transferência não permitida: o valor solicitado está excedendo o limite da conta.");  
                }
                

            }
            else if(opcao == conta1.getNumConta() && opcao1 == conta.getNumConta()){
                
                System.out.println("Quanto deseja transferir pra conta 100? ");
                double valor = Double.parseDouble(entrada.nextLine());
                boolean transferido = conta.transferir(valor, conta1, conta);

                if (transferido == true);{
                    System.out.println("Transferido!");
                }
                if(transferido == false){
                    System.out.println("Transferência não permitida: o valor solicitado está excedendo o limite da conta.");  
                }

            else{
                System.out.println("Ao menos uma dessas contas não existe!");
            }
        }else{
            System.out.println("As contas não existem!");
        }
    }
    }
}
