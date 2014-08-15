package com.app.jspad;

import servicos.ConexaoSocket;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.Toast;

public class JoystickActivity extends Activity implements SensorEventListener{

	private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private boolean mover = false;
    
    private Button btVoltar;
    private Button btmover;
    private Button btA;
    private Button btB;
    private Button btC;
    private Button btD;
    
    private String mensagem;
        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.telajoystick);
     
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        btmover = (Button)findViewById(R.id.btmover);
		btVoltar = (Button)findViewById(R.id.btvoltar);
		btA = (Button)findViewById(R.id.botaoA);
		btB = (Button)findViewById(R.id.botaoB);
		btC = (Button)findViewById(R.id.botaoC);
		btD = (Button)findViewById(R.id.botaoD);
		
		//evento click do botao voltar
	    btVoltar.setOnClickListener(new OnClickListener() {
	    	public void onClick ( View arg0 ) {
	    		try {
    				ConexaoSocket.getConexao().disconectar();
    				
    			} catch (Exception e) {
    				Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
    			}
	    		
        		finish();
	    		}
	    });
	    
	    //ativa ou nao o uso do acelerometro
	    btmover.setOnClickListener(new OnClickListener() {
	    	public void onClick ( View arg0 ) {
	    		if(mover){
	    			mover = false;
	    		}
	    		else{
	    			mover = true;
	    		}
	    	}
	    });
	    
	    btA.setOnClickListener(meuClickLIstener);
	    btB.setOnClickListener(meuClickLIstener);
	    btC.setOnClickListener(meuClickLIstener);
	    btD.setOnClickListener(meuClickLIstener);
    
	}
		
	/*
	 * evento para quando a tela tiver aberta
	 * definido senSibilidade do acelerometro
	 */
	    @Override
	    protected void onResume() {
	        super.onResume();
	        mSensorManager.registerListener((SensorEventListener) this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
	    }
	     
		@Override
	    protected void onPause() {
		       super.onPause();
		        mSensorManager.unregisterListener(this);
	    }
	    
		/*
		 * evendo iniciado quando o sensor mudar
		 * testa o valor e manda as mensagems
		 */
	    public void onSensorChanged(SensorEvent event) {
	    	
	       float x= event.values[0];
	       float y= event.values[1];
	        
	       if (mover){ 
	           if(x < 6){ 
	            	mensagem = "d";
	           }
	           
	           else if(x > 9){  
	        	   mensagem = "u";
	            }
	           
	           else if(y > 2){  
	        	   mensagem = "r" ;
	            }
	           
	           else if(y < -2){  
	                mensagem = "l";
	        	}
	           else{
	        	   mensagem = "s";
	           }
	      
	       
	           ConexaoSocket.getConexao().enviarMensagem(mensagem);
	       }
	    }
	
	//evento de click   
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
	            
	            ConexaoSocket.getConexao().enviarMensagem(mensagem);   
	        }
	    };

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
