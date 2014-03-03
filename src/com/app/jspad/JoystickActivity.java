package com.app.jspad;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JoystickActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.telajoystick);
              
	    Button btVoltar = (Button)findViewById(R.id.btvoltar);//botao voltar joystick
	    
	    //evento click do botao voltar
	    btVoltar.setOnClickListener(new OnClickListener() {
	    	public void onClick ( View arg0 ) {
        		finish();
	    		}
	    });

	}
}
