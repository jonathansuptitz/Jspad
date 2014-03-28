package com.app.jspad;

import java.io.DataOutputStream;
import java.io.IOException;
import android.os.Handler;
import android.os.Message;

class Sender implements Runnable {

    private DataOutputStream output;
    private boolean executando = true;
    private Handler handler;
    private Message msg;
    private String sendMessage;

    public Sender(DataOutputStream out) {
        this.output = out;
    }

    @Override
    public void run() {
        while (executando) {// Enquanto estiver executando

            try {
                if (sendMessage != null) { // Se mensagem <> 0
                                            // enviar
                    msg = new Message();
                    msg.arg1 = ConnectionSocket.SENDING_MESSAGE;

                    output.writeUTF(sendMessage); // Escreve mensagem
                    output.flush();
                    
                    sendMessage = null; // Seta zero na mensagem
                }

            } catch (IOException e) {
                e.printStackTrace();
                msg = new Message();
                msg.arg1 = ConnectionSocket.ERROR;
                msg.obj = e.getMessage();
                handler.sendMessage(msg);
                executando = false;
            }

        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public boolean estaExecutando() {
        return executando;
    }

    public void setExecutando(boolean executando) {
        this.executando = executando;
    }

    public void stop() {
        executando = false;
    }

    public void setMessage(String n) {
        this.sendMessage = n;

    }

    public void disconnect() throws Exception {
        msg = new Message();
        msg.arg1 = ConnectionSocket.DISCONNECTED;
        handler.sendMessage(msg); // Notifica Handler
        executando = false;
        output.close();
    }

}
 