package io.domisum.lib.youtubeapilib.data.video;

import io.domisum.lib.auxiliumlib.annotations.API;
import io.domisum.lib.auxiliumlib.util.ValidationUtil;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(of = {"title"})
public class YdaVideoMetadata
{
	
	// CONSTANTS
	@API
	public static final int MAX_TITLE_LENGTH = 100;
	@API
	public static final int MAX_DESCRIPTION_LENGTH = 5000;
	@API
	public static final int MAX_TAGS_LENGTH = 500;
	
	// ATTRIBUTES
	@Getter
	private final String title;
	@Getter
	private final String description;
	@Getter
	private final List<String> tags;
	@Getter
	private final VideoCategory category;
	
	
	// INIT
	public YdaVideoMetadata(String title, String description, List<String> tags, VideoCategory category)
	{
		this.title = title;
		this.description = description;
		this.tags = List.copyOf(tags);
		this.category = category;
		
		ValidationUtil.notNull(title, "title");
		ValidationUtil.notNull(description, "description");
		ValidationUtil.noNullElements(tags, "tags");
		ValidationUtil.notNull(category, "category");
		
		ValidationUtil.lessThanOrEqual(title.length(), MAX_TITLE_LENGTH, "title length");
		ValidationUtil.lessThanOrEqual(description.length(), MAX_DESCRIPTION_LENGTH, "description length");
		ValidationUtil.lessThanOrEqual(countFormattedTagsLength(tags), MAX_TAGS_LENGTH, "tags length");
	}
	
	
	// UTIL
	@API
	public static int countFormattedTagsLength(List<String> tags)
	{
		int tagsLength = 0;
		
		tagsLength += tags.size() == 0 ? 0 : tags.size()-1; // commas
		for(String tag : tags)
		{
			tagsLength += tag.length();
			tagsLength += tag.contains(" ") ? 2 : 0; // tag wrapped in quotes if contains space
		}
		
		return tagsLength;
	}
	
}
