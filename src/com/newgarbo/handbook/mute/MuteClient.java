package com.newgarbo.handbook.mute;

import java.io.File;
import java.nio.charset.Charset;
import java.util.UUID;

import com.newgarbo.handbook.main.Handbook;
import com.newgarbo.handbook.utils.FileUtil;

public class MuteClient
{
	private String uuid;
	private String name;
	
	private long time;
	
	private boolean isMuted;
	private boolean isPermanentlyMuted;
	
	public MuteClient(String uuid)
	{
		this.uuid = uuid;
		this.name = UUIDUtils.getNameFromUUID(this.uuid);
	}
	
	/**
	 * Populates the 'time', 'isMuted', and 'isPermanentlyMuted' values by reading from the mutes file
	 */
	public void populateData()
	{
		try
		{
			String contents = FileUtil.readFile(Handbook.instance.getDataFolder() + File.separator + "mutes.data", Charset.defaultCharset());
			
			for (String entry : contents.split("\n"))
			{
				if (entry.startsWith(this.uuid + ":"))
				{
					this.time = Long.parseLong(entry.split(":")[1]);
					
					if (this.time != 0) this.isMuted = true;
					if (this.time == -1) this.isPermanentlyMuted = true;
					
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the client's muted state (boolean.)
	 * 
	 * @return true - if client is muted.
	 */
	public boolean isMuted()
	{
		return this.isMuted;
	}
	
	/**
	 * Returns whether or not the client is permanently muted.
	 * 
	 * @return true - if client is permanently muted.
	 */
	public boolean isPermMuted()
	{
		if (!isMuted()) return false;
		return this.isPermanentlyMuted;
	}
	
	/**
	 * Returns the uuid of this client.
	 * 
	 * @return this client's UUID.
	 */
	public String getUUID()
	{
		return this.uuid;
	}
	
	/**
	 * Returns the name of this client.
	 * 
	 * @return the client's name.
	 */
	public String getName()
	{
		return this.name;
	}
	
       /**
	 * Returns the uuid of this client in the form of a UUID instance.
	 * 
	 * @return this client's UUID as a UUID object.
	 */
	public UUID getUUIDObj()
	{
		return UUID.fromString(getUUID();
	}
	
	/**
	 * Gets how long this client is muted for.
	 * 
	 * @return this client's muting time left.
	 */
	public long getTime()
	{
		return this.time;
	}
	
}
