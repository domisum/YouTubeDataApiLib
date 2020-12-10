package io.domisum.lib.youtubeapilib.data.playlist.item;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.iterator.PlaylistItemIteratorFactory;
import io.domisum.lib.youtubeapilib.data.playlist.model.YouTubePlaylistId;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistVideoIdsFetcherImpl
	implements PlaylistVideoIdsFetcher
{
	
	// DEPENDENCIES
	private final PlaylistItemIteratorFactory playlistItemIteratorFactory;
	
	
	// UPLOAD
	@Override
	public List<String> fetch(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId, Integer maxNrOfVideos)
		throws IOException
	{
		int maxNrOfVideosInt = maxNrOfVideos == null ? Integer.MAX_VALUE : maxNrOfVideos;
		var videoIds = new ArrayList<String>();
		
		try
		{
			var iterator = playlistItemIteratorFactory.create(credentials, youTubePlaylistId, "contentDetails");
			while(iterator.hasNext() && videoIds.size() < maxNrOfVideosInt)
			{
				var playlistItem = iterator.next();
				String videoId = playlistItem.getContentDetails().getVideoId();
				videoIds.add(videoId);
			}
		}
		catch(UncheckedIOException e)
		{
			throw e.getCause();
		}
		
		// remove excessive videos ids to exactly match requested number of videos
		while(videoIds.size() > maxNrOfVideosInt)
			videoIds.remove(videoIds.size()-1);
		
		return videoIds;
	}
	
}
