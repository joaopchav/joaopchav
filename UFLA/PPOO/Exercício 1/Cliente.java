public class Cliente {

    private String CPF;
    private String Nome;

    public String getCPF(){
    
        return CPF;
    }

    public String getNome(){
    
        return Nome;
    }

    public Cliente (String x, String y){
        Nome = x;
        CPF = y;
    }
    
}