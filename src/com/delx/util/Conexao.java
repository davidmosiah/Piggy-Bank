package com.delx.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class Conexao {
	Context context;
	public Conexao(Context context){
		this.context = context;
	}

	public boolean conectado() {
		try {
			ConnectivityManager cm = (ConnectivityManager)
					this.context.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
				return true;

			} else if(cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}
}
