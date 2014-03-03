package com.app.jspad;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MidiacontrolActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.midiacontrol);
	        
	    Button btVoltar3 = (Button)findViewById(R.id.btVoltar3);//botao voltar tela midia
	    
	    //evento click do botao voltar
	    btVoltar3.setOnClickListener(new OnClickListener() {
	    	public void onClick (View arg0) {
        		finish();
	    		}
		    });
	    
	    
	};
}
