package com.app.jspad;

import java.util.Random;

public class Jspad {
    private int chave;
	
	public String Decriptografa(String senha) {
		String num = "";		
		StringBuilder IP = new StringBuilder();
		
		
		chave = Character.getNumericValue(senha.charAt(0));
		
		for (int x = 2; x < senha.length(); x++){
			if (senha.charAt(x) == 'h'){
				
				IP.append(Integer.toString(Integer.parseInt(num, 16) - chave));
					
				num = "";
				
				IP.append(".");
			}
			else{
					num += String.valueOf(senha.charAt(x));
			}
		}
		IP.append(Integer.toString(Integer.parseInt(num, 16) - chave));
		
		return IP.toString();
	}
	
/*gera a senha de conexao*/
	
	public String Criptografa(String in){
		String num = "";
		Random gerador = new Random();
		
		chave = gerador.nextInt(9) + 1;
		StringBuilder out = new StringBuilder();
		
		out.append(chave);
		out.append("g");
		
		for (int x = 0; x < in.length(); x++){
			
			if (in.charAt(x) == '.'){
				out.append(Integer.toHexString(chave + Integer.parseInt(num)));
				num = "";
								
				out.append("h");
			}
			else{
				num += in.charAt(x);
		
			}
		}
		out.append(Integer.toHexString(chave + Integer.parseInt(num)));
		
		return out.toString();
	};
}