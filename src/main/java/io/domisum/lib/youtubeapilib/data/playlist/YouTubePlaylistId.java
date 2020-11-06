package io.domisum.lib.youtubeapilib.data.playlist;

import io.domisum.lib.auxiliumlib.PHR;
import io.domisum.lib.auxiliumlib.util.ValidationUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class YouTubePlaylistId
{
	
	private final String youTubePlaylistId;
	
	
	// INIT
	public static YouTubePlaylistId of(String youTubePlaylistIdString)
	{
		validate(youTubePlaylistIdString);
		return new YouTubePlaylistId(youTubePlaylistIdString);
	}
	
	public static YouTubePlaylistId uploadsOfChannel(String channelId)
	{
		String channelPrefix = "UC";
		if(!channelId.startsWith(channelPrefix))
			throw new IllegalArgumentException(PHR.r("The channelId has to start with '{}'", channelPrefix));
		
		String channelIdWithoutTypePrefix = channelId.substring(channelPrefix.length());
		String youTubePlaylistId = "UU"+channelIdWithoutTypePrefix;
		validate(youTubePlaylistId);
		return new YouTubePlaylistId(youTubePlaylistId);
	}
	
	
	private static void validate(String youTubePlaylistIdString)
	{
		ValidationUtil.notBlank(youTubePlaylistIdString, "youTubePlaylistIdString");
		
		if("WL".equals(youTubePlaylistIdString))
			throw new IllegalArgumentException("Watch later is a relative playlist, only absolute playlist id allowed");
		
		if(!StringUtils.startsWithAny(youTubePlaylistIdString, "PL", "UU", "LL")) // regular, user uploads, liked
			throw new IllegalArgumentException("YouTube playlist ids have to start with 'PL'");
		
		String randomPart = youTubePlaylistIdString.substring(2);
		ValidationUtil.notBlank(randomPart, "random part of YouTube playlist id");
		if(!randomPart.matches("^[A-Za-z0-9-_]+$"))
		{
			String exceptionMessage = PHR.r("Random part of YouTube playlist id contained illegal characters: '{}'", randomPart);
			throw new IllegalArgumentException(exceptionMessage);
		}
	}
	
	
	// OBJECT
	@Override
	public String toString()
	{
		return youTubePlaylistId;
	}
	
}
