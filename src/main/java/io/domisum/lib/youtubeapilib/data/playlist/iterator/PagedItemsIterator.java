package io.domisum.lib.youtubeapilib.data.playlist.iterator;

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
	private final Queue<T> items = new LinkedList<>();
	
	private String nextPageToken = null;
	private boolean fetchDone = false;
	
	
	// ITERATOR
	@Override
	public boolean hasNext()
		throws UncheckedIOException
	{
		if(items.size() > 0)
			return true;
		if(fetchDone)
			return false;
		
		fetchAndPutNextPage();
		return items.size() > 0;
	}
	
	@Override
	public T next()
		throws UncheckedIOException
	{
		if(items.isEmpty() && !fetchDone)
			fetchAndPutNextPage();
		
		return items.remove();
	}
	
	private void fetchAndPutNextPage()
	{
		try
		{
			fetchAndPutNextPageUncaught();
		}
		catch(IOException e)
		{
			throw new UncheckedIOException(e);
		}
	}
	
	private void fetchAndPutNextPageUncaught()
		throws IOException
	{
		var page = fetchNextPage(nextPageToken);
		items.addAll(page.getA());
		
		nextPageToken = page.getB();
		if(nextPageToken == null)
			fetchDone = true;
	}
	
	protected abstract Pair<Set<T>,String> fetchNextPage(String pageToken)
		throws IOException;
	
}
