/*
    Share to Talk, Copyright (C) 2012 GDR! <gdr@go2.pl>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package name.gdr.sharetotalk;

import org.jivesoftware.smack.packet.Presence;

/**
 * This class represents an item displayed in contact list
 * @author GDR!
 */
public class ContactItem implements Comparable<ContactItem>{
	/* Enums for simplified statuses handling outside class */
	static final public int PRESENCE_OFFLINE = 0;
	static final public int PRESENCE_ONLINE = 1;
	static final public int PRESENCE_AWAY = 2;
	
	public String name;
	public String jid;
	public Presence presence; 
	
	/**
	 * Implementation of Comparable interface
	 */
	public int compareTo(ContactItem o2) {
		String s1 = this.getComparableString();
		String s2 = o2.getComparableString();
		
		return s1.compareToIgnoreCase(s2);
	}
	
	/**
	 * Returns any string that's comparable when sorting
	 * @return in order of preference: name, jid and empty string
	 */
	private String getComparableString()
	{
		if(this.name != null) return name;
		if(this.jid != null) return jid;
		return "";
	}

	/**
	 * Returns printable name: name or jid
	 * @return printable contact name
	 */
	public String getName() {
		if(this.name != null) return this.name;
		if(this.jid != null) {
			String[] parts = this.jid.split("@");
			return parts[0];
		}
		
		return "(unknown)";
	}
	
	/**
	 * Queries the presence object if available
	 * @return PRESENCE_* constant
	 */
	public int getPresence() {
		int rv = PRESENCE_OFFLINE;
		
		if(presence != null) {
			if(presence.isAvailable()) {
				rv = PRESENCE_ONLINE;
			}
			if(presence.isAway()) {
				rv = PRESENCE_AWAY;
			}
		}
		
		return rv;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
