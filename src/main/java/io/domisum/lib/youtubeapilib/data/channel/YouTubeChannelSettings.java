package io.domisum.lib.youtubeapilib.data.channel;

import lombok.Getter;
import lombok.Setter;

public class YouTubeChannelSettings
{
	
	@Getter @Setter
	private String channelDescription;
	@Getter @Setter
	private FeaturedChannels featuredChannels;
	@Getter @Setter
	private String unsubscribedTrailerVideoId;
	
}
