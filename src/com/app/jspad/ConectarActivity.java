package com.app.jspad;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ConectarActivity extends Activity {
	private EditText edtip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.telaconecta);

	        edtip = (EditText) findViewById(R.id.edtip);
	        
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
	        
	        //evento click do botao voltar
			Button btVoltar = (Button)findViewById(R.id.btvoltar4);
			
		    btVoltar.setOnClickListener(new OnClickListener() {
		    	public void onClick ( View arg0 ) {
		    		try {
	    				ConnectionSocket.getCurentConnection().disconnect();
	    				
	    			} catch (Exception e) {

	    			}
		    		
	        		finish();
		    		}
		    });
		    
	    }
	    private void Conectar(){
	    	String edIp = edtip.getText().toString();
			String edPorta = "1234";
			
			if (edIp.length() != 0 ) {
				try {        	
					// Tenta iniciar uma conexão com o Servidor de Socket
					ConnectionSocket connection = ConnectionSocket.createConnection(edIp, edPorta);
			            	connection.connect();
			        //inicia o envio das msg
			            	
			        ConnectionSocket.getCurentConnection().startSender();
				} catch (Exception e) {
					}
				}
	    }
	}
	                
