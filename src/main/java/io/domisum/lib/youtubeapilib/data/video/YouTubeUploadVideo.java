package io.domisum.lib.youtubeapilib.data.video;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString(includeFieldNames = false, exclude = {"videoStream"})
public class YouTubeUploadVideo
{
	
	@Getter
	private final VideoStream videoStream;
	@Getter
	private final YouTubeVideoMetadata metadata;
	
}
