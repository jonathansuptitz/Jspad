package com.app.jspad;

	import java.util.Random;
	import android.os.Bundle;
	import android.app.Activity;
	import android.content.Context;
	import android.graphics.Canvas;
	import android.graphics.Color;
	import android.graphics.Paint;
	import android.view.View;

	public class testecanvas extends Activity {
		private Tela tela;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			tela = new Tela(this);
			setContentView(tela);
		}

		public class Tela extends View {

			private Paint paint = new Paint();
			private Random rnd = new Random();
			
			public Tela(Context context) {
				super(context);
			}

			@Override
			protected void onDraw(Canvas canvas) {
				super.onDraw(canvas);
				
					paint.setColor(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
					canvas.drawCircle(30, 30, 50, paint);
				
		}
	}
}
