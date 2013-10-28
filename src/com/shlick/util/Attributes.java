package com.shlick.util;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Attributes extends AbstractMap<String, String> 
{
	final Map<String, String> content = new HashMap<String, String>();
	
	@Override
	public Set<java.util.Map.Entry<String, String>> entrySet() {
		
		return content.entrySet();
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public String put(final String key, final String value) {
		return content.put(key, value);
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractMap#keySet()
	 */
	@Override
	public Set<String> keySet() {
		
		return content.keySet();
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractMap#values()
	 */
	@Override
	public Collection<String> values() {
		
		return content.values();
	}

}
