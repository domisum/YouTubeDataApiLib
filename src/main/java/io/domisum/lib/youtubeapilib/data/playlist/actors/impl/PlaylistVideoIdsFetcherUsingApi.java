package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.api.services.youtube.YouTube.PlaylistItems;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistVideoIdsFetcher;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PlaylistVideoIdsFetcherUsingApi
		implements PlaylistVideoIdsFetcher
{
	
	// CONSTANTS
	private static final long MAX_RESULTS_LIMIT = 50L;
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// UPLOAD
	@Override
	public List<String> fetch(YouTubeApiCredentials credentials, String playlistId, Integer maxNrOfVideos)
			throws IOException
	{
		int maxNrOfVideosInt = maxNrOfVideos == null ? Integer.MAX_VALUE : maxNrOfVideos;
		
		var listRequest = createBaseRequest(credentials, playlistId);
		var videoIds = fetchVideoIdsUsingRequest(listRequest, maxNrOfVideosInt);
		
		return videoIds;
	}
	
	private List<String> fetchVideoIdsUsingRequest(PlaylistItems.List listRequest, int maxNrOfVideos)
			throws IOException
	{
		PlaylistItemListResponse response;
		var videoIds = new ArrayList<String>();
		do
		{
			response = listRequest.execute();
			for(var item : response.getItems())
				videoIds.add(item.getContentDetails().getVideoId());
			
			listRequest.setPageToken(response.getNextPageToken());
		}
		while((response.getNextPageToken() != null) && (videoIds.size() < maxNrOfVideos));
		
		// remove excessive videos ids to exactly match requested number of videos
		while(videoIds.size() > maxNrOfVideos)
			videoIds.remove(videoIds.size()-1);
		
		return videoIds;
	}
	
	private PlaylistItems.List createBaseRequest(YouTubeApiCredentials credentials, String playlistId)
			throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var listRequest = youTubeDataApiClient.playlistItems().list("snippet,contentDetails");
		listRequest.setMaxResults(MAX_RESULTS_LIMIT);
		listRequest.setPlaylistId(playlistId);
		
		return listRequest;
	}
	
}
