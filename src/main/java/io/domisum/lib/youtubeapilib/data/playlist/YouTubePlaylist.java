package io.domisum.lib.youtubeapilib.data.playlist;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode(of = {"playlistId"})
@ToString(includeFieldNames = false)
public class YouTubePlaylist
{
	
	@Getter
	private final YouTubePlaylistSpecification specification;
	@Getter
	private final String playlistId;
	
	
	// GETTERS
	public String getUrl()
	{
		return "https://www.youtube.com/playlist?list="+playlistId;
	}
	
}