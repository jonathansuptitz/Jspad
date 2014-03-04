package com.app.jspad;

	import java.util.Random;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

	public class testecanvas extends Activity implements SensorEventListener{
		private Tela tela;
		
		private SensorManager mSensorManager;
	    private Sensor mAccelerometer;
	    private Float x ;
	    private Float y ;
	    private int a;
	    private int b;
	    
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			tela = new Tela(this);
			setContentView(tela);
			
			mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	        
		}

		public class Tela extends View {

			private Paint paint = new Paint();
			
			public Tela(Context context) {
				super(context);
			}

			@Override
			protected void onDraw(Canvas canvas) {
				super.onDraw(canvas);
				
					paint.setColor(Color.rgb(250, 256, 250));
					canvas.drawCircle(a, b, 30, paint);
				
			}
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
			         y = event.values[0];
			         x = event.values[1];
			         
			         if(x > 0)
			        	 a++ ;
			        	 ;
			         if(x < 0) 
			        	 a--;
			        	 ;   

			         if(y > 0)
				         b++;
				     	;
			         if(y < 0)
			        	 b--;
			        	 ;
			        	 
				     setContentView(tela);
			        
			    }
	}
