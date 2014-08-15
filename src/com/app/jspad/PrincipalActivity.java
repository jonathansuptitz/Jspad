package com.app.jspad;

import servicos.ConexaoSocket;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PrincipalActivity extends Activity {
	private ProgressDialog prog;
	private String senha = "";
	String tela = "";
	boolean conectou = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.principal);//carrega tela
       
        //linkando botões
        Button btJoystick = (Button)findViewById(R.id.btJoystick);
        Button btMidiacontrol = (Button)findViewById(R.id.btMediacontrol);
        Button btSair = (Button)findViewById(R.id.btSair); 
        
        /*
         * abre tela conectar passado qual tela será executada
         * @joystick
         */
        btJoystick.setOnClickListener(new OnClickListener() {
        	public void onClick ( View arg0 ) {
        		//verifica se wifi esta conectado
        		tela = "JoystickActivity";
        		obterSenha();
        	}
        });
        
        /*
         *abre tela conectar passado qual tela será executada
         * @multimidia 
         */
        
        btMidiacontrol.setOnClickListener(new OnClickListener() {
        	public void onClick ( View arg0 ) {
        		//verifica se wifi esta conectado
        		tela = "MultimidiaActivity";
        		obterSenha();
             		
        	}
        });
     
        /*
         * finaliza o aplicativo
         */
        btSair.setOnClickListener(new OnClickListener() {
        	public void onClick ( View arg0 ) {    
        		finish();
        	}
        });
      
 }; 

 	/*
 	 * exibi uma tela de carregamento enquanto ativa o wifi
 	 */
	private boolean carregarLoadingbar() {
		 prog = new ProgressDialog(PrincipalActivity.this);
	     
	     prog.setTitle("Aguarde ...");        
	     prog.setMessage("Conectando ...");        
	     prog.setCancelable(false);
	     prog.setIndeterminate(true); 
	     prog.show();
	     
	     Conectar();
	     
	     return false;
	}

	private boolean Conectar(){	    		

		try {        	
			// Tenta iniciar uma conexão com o Servidor de Socket
			ConexaoSocket.criarConexao(ConexaoSocket.obterEndereco(senha), "5555");
			
			ConexaoSocket.conectar();
			
			//inicia o envio das msg
			ConexaoSocket.iniciarEnvios();        	
		
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void obterSenha(){	
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Senha");
		alert.setMessage("Digite a senha de conexão: ");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {

			senha = input.toString();
			
			if(Conectar()){
				abrirIntent(tela);
			}
			else{
				Toast.makeText(null, "Não foi possível conectar.", Toast.LENGTH_SHORT);
			}
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			  tela = "";
		  }
		});

		alert.show();
	}
	
	private void abrirIntent(String tela){
		
		Intent intent = null;
		try {
			intent = new Intent(PrincipalActivity.this, Class.forName(tela));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startActivity(intent);
	}
}

