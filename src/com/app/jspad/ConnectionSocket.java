package com.app.jspad;

import java.io.DataOutputStream;
import java.net.Socket;

public class ConnectionSocket {

    private static ConnectionSocket connection;
    private int porta;
    private String host;
    private Socket socket;
    private Sender sender;
    public static final int CONNECTED = 1;
    public static final int ERROR = 2;
    public static final int SENDING_MESSAGE = 3;
    public static final int DISCONNECTED = 4;
    private DataOutputStream out;

    private ConnectionSocket(String host, String porta) {
        this.host = host;
        this.porta = Integer.parseInt(porta);
    }

    // Método que cria Objecto ConnectionSocket
    public static ConnectionSocket createConnection(String host, String porta) {
        connection = new ConnectionSocket(host, porta);
        return connection;
    }

    // Retorna conexão atual
    public static ConnectionSocket getCurentConnection() {
        return connection;
    }

    // Conecta com o Servidor
    public void connect() throws Exception {
        this.socket = new Socket(host, porta);
        out = new DataOutputStream(socket.getOutputStream());
    }

    // Inicia Thread para envio de mensagens
    public void startSender() {
        sender = new Sender(out);
        new Thread(sender).start();
    }

    // Método set mensagem para envio
    public void senMessage(String n) {
        sender.setMessage(n);
    }

    // Método para disconectar do Servidor
    public void disconnect() throws Exception {
        sender.disconnect();
        socket.close();
        }
}
 