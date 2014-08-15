package com.app.jspad;

import servicos.ConexaoSocket;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

public class MultimidiaActivity extends Activity{
    private Button btA;
    private Button btB;
    private Button btC;
    private Button btD;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button btVoltar3;
    
    private String mensagem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.midiacontrol);
	    
	    //linkando botões
	    btVoltar3 = (Button)findViewById(R.id.btVoltar3);//botao voltar tela midia	    
	    btA = (Button)findViewById(R.id.btA);
	    btB = (Button)findViewById(R.id.btB);
	    btC = (Button)findViewById(R.id.btC);
	    btD = (Button)findViewById(R.id.btD);
	    bt1 = (Button)findViewById(R.id.bt1);
	    bt2 = (Button)findViewById(R.id.bt2);
	    bt3 = (Button)findViewById(R.id.btm3);
	    bt4 = (Button)findViewById(R.id.btm4);
	    
	    /*evento click do botao voltar
	     *  volta a tela e fecha a conexao socket
	     */   
	    btVoltar3.setOnClickListener(new OnClickListener() {
	    	public void onClick (View arg0) {
	    		try {
    				ConexaoSocket.getConexao().disconectar();
    				
    			} catch (Exception e) {
    				Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
    			}
	    		
        		finish();
	    	}
		});
	    
	    //defini o ontouch para todos os botoes
	    btA.setOnTouchListener(meutouchlistener);
	    btB.setOnTouchListener(meutouchlistener);
	    btC.setOnTouchListener(meutouchlistener);
	    btD.setOnTouchListener(meutouchlistener);
	    bt1.setOnTouchListener(meutouchlistener);
	    bt2.setOnTouchListener(meutouchlistener);
	    bt3.setOnTouchListener(meutouchlistener);
	    bt4.setOnTouchListener(meutouchlistener);
	    btA.setOnTouchListener(meutouchlistener);
	}
	
	/*
	 * evento on click da tela
	 * verifica qual botao foi pressionado
	 * e manda a mensagem apropriada   
	 */
	private OnClickListener meuClickLIstener= new OnClickListener() {
	        public void onClick(View v) {
	            if (v == btA){
	            	mensagem = "1";
	            }
	            else if (v == btB){
	            	mensagem = "2";
	            }
	            else if (v == btC){
	            	mensagem = "3";
	            }
	            else if (v == btD){
	            	mensagem = "4";
	            }
	            else if (v == bt1){
	            	mensagem = "u";
	            }
	            else if (v == bt2){
	            	mensagem = "r";
	            }
	            else if (v == bt3){
	            	mensagem = "l";
	            }
	            else if (v == bt4){
	            	mensagem = "d"; 
	            }
	            
	            ConexaoSocket.getConexao().enviarMensagem(mensagem);	            
	        }
	};  
	
	/**
	 * trata o evento touch dos botoes
	 */
	OnTouchListener meutouchlistener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
	        if(event.getAction() == MotionEvent.ACTION_DOWN) {
	        	ConexaoSocket.getConexao().enviarMensagem(mensagem);
	        } else if (event.getAction() == MotionEvent.ACTION_UP) {
	        	ConexaoSocket.getConexao().enviarMensagem("@"+mensagem);
	        }
			return true;
		}
	};
	    
}
