package com.app.jspad;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PrincipalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);//carrega tela
        
        //botao para tela joystick
        Button btJoystick = (Button)findViewById(R.id.btJoystick);
        //abre tela joystick
        btJoystick.setOnClickListener(new OnClickListener() {
        	public void onClick ( View arg0 ) {
        		Intent intent = new Intent(PrincipalActivity.this, JoystickActivity.class);
         		startActivity(intent);
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
        
        Button bt = (Button)findViewById(R.id.bt);
        bt.setOnClickListener(new OnClickListener() {
        	public void onClick ( View arg0 ) {
        		Intent intent = new Intent(PrincipalActivity.this, testecanvas.class);
         		startActivity(intent);
        }
    });
        
};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }
    
    
     
}
