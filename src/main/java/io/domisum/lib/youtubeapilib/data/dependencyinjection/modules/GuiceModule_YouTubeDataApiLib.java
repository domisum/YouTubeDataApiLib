package io.domisum.lib.youtubeapilib.data.dependencyinjection.modules;

import com.google.inject.AbstractModule;
import io.domisum.lib.youtubeapilib.data.channel.ChannelFeaturedChannelsSetter;
import io.domisum.lib.youtubeapilib.data.channel.ChannelFeaturedChannelsSetterImpl;
import io.domisum.lib.youtubeapilib.data.playlist.PlaylistCreator;
import io.domisum.lib.youtubeapilib.data.playlist.PlaylistCreatorImpl;
import io.domisum.lib.youtubeapilib.data.playlist.PlaylistDeleter;
import io.domisum.lib.youtubeapilib.data.playlist.PlaylistDeleterImpl;
import io.domisum.lib.youtubeapilib.data.playlist.PlaylistIdFetcher;
import io.domisum.lib.youtubeapilib.data.playlist.PlaylistIdFetcherImpl;
import io.domisum.lib.youtubeapilib.data.playlist.item.PlaylistLister;
import io.domisum.lib.youtubeapilib.data.playlist.item.PlaylistListerImpl;
import io.domisum.lib.youtubeapilib.data.playlist.item.PlaylistVideoIdsFetcher;
import io.domisum.lib.youtubeapilib.data.playlist.item.PlaylistVideoIdsFetcherImpl;
import io.domisum.lib.youtubeapilib.data.playlist.item.PlaylistVideoInserter;
import io.domisum.lib.youtubeapilib.data.playlist.item.PlaylistVideoInserterImpl;
import io.domisum.lib.youtubeapilib.data.video.VideoDeleter;
import io.domisum.lib.youtubeapilib.data.video.VideoDeleterImpl;
import io.domisum.lib.youtubeapilib.data.video.VideoMetadataSetter;
import io.domisum.lib.youtubeapilib.data.video.VideoMetadataSetterImpl;
import io.domisum.lib.youtubeapilib.data.video.VideoPrivacyStatusSetter;
import io.domisum.lib.youtubeapilib.data.video.VideoPrivacyStatusSetterImpl;
import io.domisum.lib.youtubeapilib.data.video.fetchinfo.VideoDefinitionFetcher;
import io.domisum.lib.youtubeapilib.data.video.fetchinfo.VideoDefinitionFetcherImpl;
import io.domisum.lib.youtubeapilib.data.video.fetchinfo.VideoDurationFetcher;
import io.domisum.lib.youtubeapilib.data.video.fetchinfo.VideoDurationFetcherImpl;
import io.domisum.lib.youtubeapilib.data.video.fetchinfo.VideoMetadataFetcher;
import io.domisum.lib.youtubeapilib.data.video.fetchinfo.VideoMetadataFetcherImpl;
import io.domisum.lib.youtubeapilib.data.video.fetchinfo.VideoPrivacyStatusFetcher;
import io.domisum.lib.youtubeapilib.data.video.fetchinfo.VideoPrivacyStatusFetcherImpl;
import io.domisum.lib.youtubeapilib.data.video.upload.VideoThumbnailUploader;
import io.domisum.lib.youtubeapilib.data.video.upload.VideoThumbnailUploaderImpl;
import io.domisum.lib.youtubeapilib.data.video.upload.VideoUploader;
import io.domisum.lib.youtubeapilib.data.video.upload.VideoUploaderImpl;

public class GuiceModule_YouTubeDataApiLib
	extends AbstractModule
{
	
	@Override
	protected void configure()
	{
		bind(ChannelFeaturedChannelsSetter.class).to(ChannelFeaturedChannelsSetterImpl.class);
		
		bind(PlaylistCreator.class).to(PlaylistCreatorImpl.class);
		bind(PlaylistDeleter.class).to(PlaylistDeleterImpl.class);
		bind(PlaylistIdFetcher.class).to(PlaylistIdFetcherImpl.class);
		bind(PlaylistLister.class).to(PlaylistListerImpl.class);
		bind(PlaylistVideoIdsFetcher.class).to(PlaylistVideoIdsFetcherImpl.class);
		bind(PlaylistVideoInserter.class).to(PlaylistVideoInserterImpl.class);
		
		bind(VideoDefinitionFetcher.class).to(VideoDefinitionFetcherImpl.class);
		bind(VideoDeleter.class).to(VideoDeleterImpl.class);
		bind(VideoThumbnailUploader.class).to(VideoThumbnailUploaderImpl.class);
		bind(VideoUploader.class).to(VideoUploaderImpl.class);
		bind(VideoDurationFetcher.class).to(VideoDurationFetcherImpl.class);
		bind(VideoMetadataFetcher.class).to(VideoMetadataFetcherImpl.class);
		bind(VideoMetadataSetter.class).to(VideoMetadataSetterImpl.class);
		bind(VideoPrivacyStatusFetcher.class).to(VideoPrivacyStatusFetcherImpl.class);
		bind(VideoPrivacyStatusSetter.class).to(VideoPrivacyStatusSetterImpl.class);
	}
	
}
