package com.app.jspad;

import junit.framework.TestCase;

public class Testejs extends TestCase {

	public void testPegaIpServidor() {
		Jspad js = new Jspad();
		
		String expected = "192.168.129.212";
		
		assertEquals(expected, js.Criptografa("5gc5hadh86hd9"));
	}

}
