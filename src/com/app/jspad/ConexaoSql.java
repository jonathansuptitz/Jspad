package com.app.jspad;

import java.sql.*;;

public class ConexaoSql {

	    public static void main (String[] args)
	    {
	        Connection conn = null;

	        try
	        {
	  
	        }
	        catch (Exception e)
	        {
	            System.err.println ("Não foi possível estabelecer conexão com o BD");
	        }
	        finally
	        {
	            if (conn != null)
	            {
	                try
	                {
	                    conn.close ();
	                    System.out.println ("Conexão finalizada");
	                }
	                catch (Exception e) { /* ignore close errors */ }
	            }
	        }
	    }
	}