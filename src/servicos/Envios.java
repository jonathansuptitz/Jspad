package servicos;

import java.io.DataOutputStream;
import java.io.IOException;

public class Envios implements Runnable {

   //private DataOutputStream saida;
    private boolean executando = true;
    private String Menssagem;
    
    private DataOutputStream saida;

    public Envios(DataOutputStream saida2) {
    	this.saida = saida2;
	}

	@Override
    public void run() {
        while (executando) {// Enquanto estiver executando
        	
        	/*
        	 * enquando estiver conectado se a mensagem nao estiver nula
        	 * envia ela ao servidor
        	 */
            if (Menssagem != null) {

			    try {
					saida.writeUTF(Menssagem);
					saida.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			    
			    Menssagem = null; // Seta nulo na mensagem
			}
        }
    }

	/*
	 * verifica se esta executando
	 */
    public void setExecutando(boolean executando) {
        this.executando = executando;
    }

    /*
     * defina a mensagem a ser enviada
     */
    public void setMensagem(String n) {
        this.Menssagem = n;

    }
    
    
    /*
     * disconecta o serviço
     */
    public void disconectar() throws Exception {
        executando = false;
        saida.close();
    }

}
 