package io.domisum.lib.youtubeapilib.data;

public enum PrivacyStatus
{
	
	PUBLIC,
	UNLISTED,
	PRIVATE;
	
	
	public static PrivacyStatus parse(String privacyStatusString)
	{
		for(var privacyStatus : values())
			if(privacyStatus.name().equalsIgnoreCase(privacyStatusString))
				return privacyStatus;
		
		throw new IllegalArgumentException("No privacy status equal to '"+privacyStatusString+"'");
	}
	
}
