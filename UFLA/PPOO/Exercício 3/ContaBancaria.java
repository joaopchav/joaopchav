public class ContaBancaria {

        private double saldo;
        private double limite;
        private Cliente cliente1;
        private int numerodaconta;
        private static int UlitmaContaCriada = 100;
    
        public ContaBancaria(Cliente novoCliente){
    
            saldo = 0;
            limite = -1000.00;
            cliente1 = novoCliente;
            this.numerodaconta = UlitmaContaCriada++;
        }

        public ContaBancaria(Cliente novoCliente1, double saldoincial){
    
            saldo = saldoincial;
            limite = -1000.00;
            cliente1 = novoCliente1;
            this.numerodaconta = UlitmaContaCriada++;
        }

        public double getSaldo(){
        
            return saldo;
        }
    
        public double getLimite(){
        
            return limite;
        }

        public int getNumConta(){
        
            return numerodaconta;
        }
        public boolean saque(double valor){
            if (saldo - valor > limite ){
                saldo = saldo - valor;
                return true;
            }else{
                return false;
            }
        }
    
        public boolean deposito(double valor){
            if (valor < 0 ){
                return false;
            }else{
                saldo = saldo + valor;
                return true;
            }
        }

        public boolean transferir(Double valor, ContaBancaria contaOrigem, ContaBancaria contaDestino) {

            if (contaOrigem.getSaldo() - valor > limite) {

                contaOrigem.saque(valor);
                contaDestino.deposito(valor);
                return true;

            } else {
                
                return false;

            }
        }
}

