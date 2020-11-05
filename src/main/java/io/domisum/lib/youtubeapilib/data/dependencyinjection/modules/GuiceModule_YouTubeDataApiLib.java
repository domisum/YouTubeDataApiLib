package io.domisum.lib.youtubeapilib.data.dependencyinjection.modules;

import com.google.inject.AbstractModule;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistCreator;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistDeleter;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistIdFetcher;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistLister;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistVideoCountFetcher;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistVideoIdsFetcher;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistVideoInserter;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistVideoRemover;
import io.domisum.lib.youtubeapilib.data.playlist.actors.impl.PlaylistCreatorUsingApi;
import io.domisum.lib.youtubeapilib.data.playlist.actors.impl.PlaylistDeleterUsingApi;
import io.domisum.lib.youtubeapilib.data.playlist.actors.impl.PlaylistIdFetcherUsingApi;
import io.domisum.lib.youtubeapilib.data.playlist.actors.impl.PlaylistListerUsingApi;
import io.domisum.lib.youtubeapilib.data.playlist.actors.impl.PlaylistVideoCountFetcherUsingApi;
import io.domisum.lib.youtubeapilib.data.playlist.actors.impl.PlaylistVideoIdsFetcherUsingApi;
import io.domisum.lib.youtubeapilib.data.playlist.actors.impl.PlaylistVideoInserterUsingApi;
import io.domisum.lib.youtubeapilib.data.playlist.actors.impl.PlaylistVideoRemoverUsingApi;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoDeleter;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoDurationFetcher;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoMetadataFetcher;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoMetadataSetter;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoPrivacyStatusSetter;
import io.domisum.lib.youtubeapilib.data.video.actors.impl.VideoDeleterUsingApi;
import io.domisum.lib.youtubeapilib.data.video.actors.impl.VideoDurationFetcherUsingApi;
import io.domisum.lib.youtubeapilib.data.video.actors.impl.VideoMetadataFetcherUsingApi;
import io.domisum.lib.youtubeapilib.data.video.actors.impl.VideoMetadataSetterUsingApi;
import io.domisum.lib.youtubeapilib.data.video.actors.impl.VideoPrivacyStatusSetterUsingApi;
import io.domisum.lib.youtubeapilib.data.video.actors.impl.upload.VideoThumbnailUploaderUsingApi;
import io.domisum.lib.youtubeapilib.data.video.actors.impl.upload.VideoUploaderUsingApi;
import io.domisum.lib.youtubeapilib.data.video.actors.upload.VideoThumbnailUploader;
import io.domisum.lib.youtubeapilib.data.video.actors.upload.VideoUploader;

public class GuiceModule_YouTubeDataApiLib
	extends AbstractModule
{
	
	@Override
	protected void configure()
	{
		bind(PlaylistCreator.class).to(PlaylistCreatorUsingApi.class);
		bind(PlaylistDeleter.class).to(PlaylistDeleterUsingApi.class);
		bind(PlaylistIdFetcher.class).to(PlaylistIdFetcherUsingApi.class);
		bind(PlaylistLister.class).to(PlaylistListerUsingApi.class);
		bind(PlaylistVideoCountFetcher.class).to(PlaylistVideoCountFetcherUsingApi.class);
		bind(PlaylistVideoIdsFetcher.class).to(PlaylistVideoIdsFetcherUsingApi.class);
		bind(PlaylistVideoInserter.class).to(PlaylistVideoInserterUsingApi.class);
		bind(PlaylistVideoRemover.class).to(PlaylistVideoRemoverUsingApi.class);
		
		bind(VideoDeleter.class).to(VideoDeleterUsingApi.class);
		bind(VideoThumbnailUploader.class).to(VideoThumbnailUploaderUsingApi.class);
		bind(VideoUploader.class).to(VideoUploaderUsingApi.class);
		bind(VideoDurationFetcher.class).to(VideoDurationFetcherUsingApi.class);
		bind(VideoMetadataFetcher.class).to(VideoMetadataFetcherUsingApi.class);
		bind(VideoMetadataSetter.class).to(VideoMetadataSetterUsingApi.class);
		bind(VideoPrivacyStatusSetter.class).to(VideoPrivacyStatusSetterUsingApi.class);
	}
	
}
