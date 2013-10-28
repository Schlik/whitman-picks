package com.shlick.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class Util {

	public static Map<String, String> getMappedAttributes( HttpSession ses, String key )
	{
		return uncheckedCast( ses.getAttribute( key ) );
	}
	
	public static Map<String, String> getMappedAttributes( HttpSession ses )
	{
		return uncheckedCast( ses.getAttribute( "attributeKey" ) );
	}
	
	@SuppressWarnings( {"unchecked" } )
	public static synchronized <T> T uncheckedCast( Object obj )
	{
		return (T)obj;
	}

	public static Attributes getAttributes( HttpSession ses )
	{
		Attributes attr = new Attributes();
		
		Enumeration<String> keys = uncheckedCast( ses.getAttributeNames() );
		
		for( ; keys.hasMoreElements();  )
		{
			 String k = keys.nextElement();
			 Object o = ses.getAttribute( k );
			 if( o instanceof String )
			 {
				 String v = (String)o;
				 attr.put(k, v );
			 }
		}
		return attr;
	}


	public static final boolean isInteger( String str )
	{
		boolean ret = str.matches( "^[-+]?[0-9]+(\\.[0-9]+)?$" );
	
		return ret;
	}
	
	public static final boolean beginsWithInteger( String str )
	{
		boolean ret = false;
		Character c = str.charAt(0);
		ret = Character.isDigit( c );
		return ret;
	}

}
