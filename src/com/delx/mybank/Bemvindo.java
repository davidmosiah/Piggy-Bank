package com.delx.mybank;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Bemvindo extends Activity{
	boolean primeira;
	private static final String NOME = "AngryBanks";
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.bemvindo);
		salvarVisitas();
		//Go
		Button go = (Button) findViewById(R.id.button_go);
		go.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				salvarVisitas();
				finish();
				startActivity(new Intent(Bemvindo.this,DashBoard.class));
			}
		});
	}
	public void salvarVisitas(){
		SharedPreferences pref = getSharedPreferences(NOME, 0);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean("primeira",true);
		editor.commit();
	}
}