package com.app.jspad;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PrincipalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.principal);//carrega tela
        
        final boolean conectado = isOnline(this);
        
        //botao para joystick
        Button btJoystick = (Button)findViewById(R.id.btJoystick);
        //abre tela conectar
        btJoystick.setOnClickListener(new OnClickListener() {
        	public void onClick ( View arg0 ) {
        		//verifica se wifi esta conectado
        		if (conectado){
        			Intent intent = new Intent(PrincipalActivity.this, ConectarActivity.class);
        			startActivity(intent);
        		}else{
        			Toast.makeText(PrincipalActivity.this, "Sem conexão com a internet.", Toast.LENGTH_SHORT).show();
        		}
        }
    });
                
        //botao para midia
        Button btMidiacontrol = (Button)findViewById(R.id.btMediacontrol);
        //abre tela media
        btMidiacontrol.setOnClickListener(new OnClickListener() {
        	public void onClick ( View arg0 ) {
        		Intent intent = new Intent(PrincipalActivity.this, MidiacontrolActivity.class);
         		startActivity(intent);
        }
    });
     
        //botao para sair
        Button btSair = (Button)findViewById(R.id.btSair); 
        //sair do app
        btSair.setOnClickListener(new OnClickListener() {
        	public void onClick ( View arg0 ) {        		
        		finish();
        }
    });
      
 }; 

 //verifica conexao wifi com a internet
 public static boolean isOnline(Context contexto){
	 //Pego a conectividade do contexto o qual o metodo foi chamado
	 ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

	 if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
		 return true;
	 }
	 else{
	 	return false;
	 }
 }
     
}
