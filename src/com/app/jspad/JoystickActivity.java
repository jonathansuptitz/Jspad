package com.app.jspad;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JoystickActivity extends Activity implements SensorEventListener{

	private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    
    private Button btVoltar;
    private Button btmover;
    
    private String numerotel;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.telajoystick);
		
		/*pega o numero do telefone e passa para o servidor*/
		TelephonyManager tMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);  
		numerotel = tMgr.getLine1Number();
		
		
				
		ConnectionSocket.getCurentConnection().senMessage(cryptografa(numerotel));
		
     
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        btmover = (Button)findViewById(R.id.btMover);//botao voltar joystick
		btVoltar = (Button)findViewById(R.id.btvoltar);//botao voltar joystick
		
		//evento click do botao voltar
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
	     
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }
	 
	    
	    public void onSensorChanged(SensorEvent event) {
	    	
	        float x= event.values[0];
	        float y= event.values[1];
	        
	        if (btmover.isPressed()){ 
	            if(x < 6){ 
	            	ConnectionSocket.getCurentConnection().senMessage("l");
	            }
	            if(x > 8){  
	                ConnectionSocket.getCurentConnection().senMessage("r");
	            }
	            if(y > 2){  
	                ConnectionSocket.getCurentConnection().senMessage("u");
	            }
	            if(y < -2){  
	                ConnectionSocket.getCurentConnection().senMessage("d");
	        	}
	        }
	    }
	    
	    public String cryptografa(String numero) {
	    	numero = numero.replaceAll("8", "w");
	    	numero = numero.replaceAll("2", "t");
	    	numero = numero.replaceAll("3", "x");
	    	numero = numero.replaceAll("4", "e");
	    	numero = numero.replaceAll("5", "h");
	    	numero = numero.replaceAll("6", "u");
	    	numero = numero.replaceAll("7", "a");
	    	numero = numero.replaceAll("1", "p");
	    	numero = numero.replaceAll("9", "z");
	    	numero = numero.replaceAll("0", "m");
	    	
	    	return numero;
	    }
	}
