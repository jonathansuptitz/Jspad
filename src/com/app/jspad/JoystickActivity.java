package com.app.jspad;

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
import android.widget.TextView;

public class JoystickActivity extends Activity implements SensorEventListener{
	private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;
    
	private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.telajoystick);
		
		textViewX = (TextView) findViewById(R.id.text_view_x);
        textViewY = (TextView) findViewById(R.id.text_view_y);
        textViewZ = (TextView) findViewById(R.id.text_view_z);
        
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        
	    //evento click do botao voltar
		Button btVoltar = (Button)findViewById(R.id.btvoltar);//botao voltar joystick
		
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
	        mSensorManager.registerListener((SensorEventListener) this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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
	        float z= event.values[2];
	        
	        textViewX.setText(String.valueOf(x));
	        textViewY.setText(String.valueOf(y));
	        textViewZ.setText(String.valueOf(z));
	        
	         
	            if(z < 7){ 
	            	ConnectionSocket.getCurentConnection().senMessage("d");
	            }
	            else if(z > 8){  
	                ConnectionSocket.getCurentConnection().senMessage("u");
	            }
	            else if(y > 1){  
	                ConnectionSocket.getCurentConnection().senMessage("r");
	            }
	            else if(y < -1){  
	                ConnectionSocket.getCurentConnection().senMessage("l");
	        	}
	        }
		    
	}
