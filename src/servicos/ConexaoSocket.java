package servicos;

import java.io.DataOutputStream;

import java.net.Inet6Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;



public class ConexaoSocket {

    private static ConexaoSocket conexao;
    private static int Porta;
    private static String Host;
    private static Socket socket;
    private static Envios envios;
    private static DataOutputStream saida;
	static ArrayList<String> ips = new ArrayList<String>();

    private ConexaoSocket(String host, String porta) {
        Host = host;
        Porta = Integer.parseInt(porta);
    }

    // Método que cria Objecto ConnectionSocket
    public static ConexaoSocket criarConexao(String host, String porta) {
        conexao = new ConexaoSocket(host, porta);
        return conexao;
    }

    // Retorna conexão atual
    public static ConexaoSocket getConexao() {
        return conexao;
    }

    // Conecta com o Servidor
    public static void conectar() throws Exception {
        socket = new Socket(Host, Porta);
        socket.setSoTimeout(3*60*1000);
        socket.setTcpNoDelay(true);
     
        saida = new DataOutputStream(socket.getOutputStream());
    }

    // Inicia Thread para envio de mensagens
    public static void iniciarEnvios() {
        envios = new Envios(saida);
        new Thread(envios).start();
    }

    // Método set mensagem para envio
    public void enviarMensagem(String n) {
        envios.setMensagem(n);
    }

    // Método para disconectar do Servidor
    public void disconectar() throws Exception {
        envios.disconectar();
        socket.close();
    }
    
	/**
     * obten a mascara de rede
     * @return 
     * 
     * @return
     */
    public static String obterEndereco(String senha){
    	String mascara = "";
    	String ip = "";

    	try {  
    		  
    	    Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();  
    	  
    	    while (ifaces.hasMoreElements()) {  
    	        NetworkInterface iface = ifaces.nextElement();  
    	        
    	        if(iface.getName().equals("wlan0")){
	    	        for (InterfaceAddress address : iface.getInterfaceAddresses()) {     	        	
	    	        	if(address.getAddress() instanceof Inet6Address)
	    	        		continue;
	    	        	
	    	        	mascara = address.getBroadcast().toString();  	    	  
	    	        }  
    	        }
    	        
    	    }  

        	int index = 0;
        	String sessoes[] = {"","","",""};
        	
        	
        	for(int x = 1; x < mascara.length(); x++){
        		if (!(mascara.charAt(x) == '.')){
        			sessoes[index] += mascara.charAt(x);
        		}
        		else{
        			index++;
        		}
        	}	
        	
        	ip.concat(sessoes[0].concat(".").concat(sessoes[1]).concat(".").concat(sessoes[2]).concat(".").concat(senha));

    	} catch(SocketException ex) {  
    	    System.err.println(ex);  
    	}
    	
		return ip;		  
    }
    
}
 