package testes;

import servicos.ConexaoSocket;
import junit.framework.TestCase;

public class TesteIP extends TestCase {
	public void testAcessaUrl() throws Exception
    {
		boolean status = true;
		ConexaoSocket.obterEndereco("123");
        assertTrue(status);
    }
		
}
