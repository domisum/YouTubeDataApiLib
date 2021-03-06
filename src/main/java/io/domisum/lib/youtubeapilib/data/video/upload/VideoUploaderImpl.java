package io.domisum.lib.youtubeapilib.data.video.upload;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube.Videos.Insert;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.PrivacyStatus;
import io.domisum.lib.youtubeapilib.data.video.model.YdaUploadVideo;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VideoUploaderImpl
	extends YouTubeUploader
	implements VideoUploader
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// UPLOAD
	@Override
	public String upload(YouTubeApiCredentials credentials, YdaUploadVideo ydaUploadVideo, PrivacyStatus privacyStatus)
		throws IOException
	{
		logger.info("Preparing to upload '{}' to YouTube ({})", ydaUploadVideo, privacyStatus);
		
		try(var videoInputStream = ydaUploadVideo.getVideoStream().getInputStream())
		{
			var videoContent = new InputStreamContent("video/*", videoInputStream);
			videoContent.setLength(ydaUploadVideo.getVideoStream().getLength());
			
			var videoToUploadSettings = buildVideoToUploadSettings(ydaUploadVideo, privacyStatus);
			
			logger.info("Starting video upload...");
			var uploadRequest = createUploadRequest(credentials, videoContent, videoToUploadSettings);
			var returnedVideo = uploadRequest.execute();
			logger.info("Video upload complete. Video id: {}", returnedVideo.getId());
			
			return returnedVideo.getId();
		}
	}
	
	
	// VIDEO METADATA
	private Video buildVideoToUploadSettings(YdaUploadVideo ydaUploadVideo, PrivacyStatus privacyStatus)
	{
		var videoToUpload = new Video();
		
		videoToUpload.setSnippet(createVideoSnippet(ydaUploadVideo));
		videoToUpload.setStatus(createVideoStatus(privacyStatus));
		
		return videoToUpload;
	}
	
	private VideoStatus createVideoStatus(PrivacyStatus privacyStatus)
	{
		var status = new VideoStatus();
		status.setPrivacyStatus(privacyStatus.name());
		status.setLicense("creativeCommon");
		return status;
	}
	
	private VideoSnippet createVideoSnippet(YdaUploadVideo ydaUploadVideo)
	{
		var youTubeVideoMetadata = ydaUploadVideo.getMetadata();
		
		var snippet = new VideoSnippet();
		snippet.setTitle(youTubeVideoMetadata.getTitle());
		snippet.setDescription(youTubeVideoMetadata.getDescription());
		snippet.setTags(youTubeVideoMetadata.getTags());
		snippet.setCategoryId(youTubeVideoMetadata.getCategory().categoryId+"");
		
		return snippet;
	}
	
	
	// UPLOAD REQUEST
	private Insert createUploadRequest(YouTubeApiCredentials credentials, InputStreamContent videoContent, Video videoToUpload)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var videoInsert = youTubeDataApiClient.videos().insert("snippet,statistics,status", videoToUpload, videoContent);
		configureMediaHttpUploader(videoInsert.getMediaHttpUploader());
		return videoInsert;
	}
	
}
