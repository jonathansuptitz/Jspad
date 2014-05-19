package com.app.jspad;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ConectarActivity extends Activity {
	private EditText edtSenha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.telaconecta);

	        edtSenha = (EditText) findViewById(R.id.edtSenha);
	        
	        //botao para tela joystick
	        Button btConectar = (Button)findViewById(R.id.btConect);
	        //abre tela joystick
	        btConectar.setOnClickListener(new OnClickListener() {
	        	public void onClick ( View arg0 ) {
	        		try{
	        			Conectar(); 
	        		}
	        		finally{
	        			Intent intent = new Intent(ConectarActivity.this, JoystickActivity.class);
	         		
	        			startActivity(intent);
	        		}
	        }
	    });
		    
	    }
	    private void Conectar(){
	    	Jspad js = new Jspad();
	    	
	    	String Ip = edtSenha.getText().toString();   	
	    	
			if (Ip.length() != 0 ) {
				try {        	
					// Tenta iniciar uma conexão com o Servidor de Socket
					ConnectionSocket connection = ConnectionSocket.createConnection(js.Decriptografa(Ip), "1234");
			            	connection.connect();
			        //inicia o envio das msg
			            	
			        ConnectionSocket.getCurentConnection().startSender();
				} catch (Exception e) {
					}
				}
	    }
	}
	                
