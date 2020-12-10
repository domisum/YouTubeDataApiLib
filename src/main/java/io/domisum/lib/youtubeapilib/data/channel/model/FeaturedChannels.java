package io.domisum.lib.youtubeapilib.data.channel.model;

import io.domisum.lib.auxiliumlib.util.ValidationUtil;
import lombok.Getter;

import java.util.List;

public class FeaturedChannels
{
	
	@Getter
	private final String title;
	@Getter
	private final List<String> channelIds;
	
	
	// INIT
	public FeaturedChannels(String title, List<String> channelIds)
	{
		ValidationUtil.notBlank(title, "title");
		ValidationUtil.noNullElements(channelIds, "channelIds");
		
		this.title = title;
		this.channelIds = List.copyOf(channelIds);
	}
	
}
