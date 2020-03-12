import java.lang.*;

class Principal{
    public static void main(String[] args){
        String arquivo = "C:/estudos/Google_Charts/bitcoin.html";
        
        try{
            Runtime.getRuntime().exec("cmd /c "+arquivo);
        }catch(Exception ex){
            
        }
    }
}