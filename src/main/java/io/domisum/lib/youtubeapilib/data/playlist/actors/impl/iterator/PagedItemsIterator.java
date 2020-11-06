package io.domisum.lib.youtubeapilib.data.playlist.actors.impl.iterator;

import io.domisum.lib.auxiliumlib.datacontainers.tuple.Pair;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@RequiredArgsConstructor
public abstract class PagedItemsIterator<T>
	implements Iterator<T>
{
	
	// STATUS
	private String nextPageToken = null;
	private boolean done = false;
	private final Queue<T> items = new LinkedList<>();
	
	
	// ITERATOR
	@Override
	public boolean hasNext()
		throws UncheckedIOException
	{
		if(done)
			return false;
		if(items.size() > 0)
			return true;
		
		fetchAndPutNextPage();
		return items.size() > 0;
	}
	
	@Override
	public T next()
		throws UncheckedIOException
	{
		if(items.isEmpty() && !done)
			fetchAndPutNextPage();
		
		return items.remove();
	}
	
	private void fetchAndPutNextPage()
	{
		try
		{
			fetchNextPageUncaught();
		}
		catch(IOException e)
		{
			throw new UncheckedIOException(e);
		}
	}
	
	private void fetchNextPageUncaught()
		throws IOException
	{
		var page = fetchNextPage(nextPageToken);
		items.addAll(page.getA());
		
		nextPageToken = page.getB();
		if(nextPageToken == null)
			done = true;
	}
	
	protected abstract Pair<Set<T>,String> fetchNextPage(String pageToken)
		throws IOException;
	
}
