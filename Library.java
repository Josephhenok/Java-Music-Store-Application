//Joseph Henok (501165533)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  //private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public boolean download(AudioContent content) {
		if (content == null) {
			return false;
		}
		String contentType = content.getType();
		if (contentType.equals(Song.TYPENAME)) {
			if (songs.contains(content)) {
				errorMsg = "Song already downloaded";
				return false;
			} else {
				songs.add((Song) content);
				return true;
			}
		} else if (contentType.equals(AudioBook.TYPENAME)) {
			if (audiobooks.contains(content)) {
				errorMsg = "Audiobook already downloaded";
				return false;
			} else {
				audiobooks.add((AudioBook) content);
				return true;
			}
		}  else {
			errorMsg = "Invalid audio content type";
			return false;
		}
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");//Creates the formatting (chapter number then period)
			songs.get(i).printInfo();//Prints out the index of the song, followed by the song's information using the printInfo() method
			System.out.println();//adds a newline character for formatting	
		
		
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");//Creates the formatting (chapter number then period)
			audiobooks.get(i).printInfo();//Prints out the index of the song, followed by the song's information using the printInfo() method
			System.out.println();//adds a newline character for formatting
		}
		
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");//Creates the formatting (chapter number then period)
			System.out.print(playlists.get(i).getTitle());// Prints out the title of the playlist
			System.out.println();//adds a new line for formatting	
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> artists = new ArrayList<String>();
		for (int i = 0; i < songs.size(); i++){
			if (!(artists.contains(songs.get(i).getArtist()))){ //Checks if the current song's artist is already in the artists ArrayList
				artists.add(songs.get(i).getArtist());//adds current song's artist to the artists arraylist if it's not already contained in it
				System.out.println(i+1 + ". " + songs.get(i).getArtist());//prints the number and period for formatting then the name of the artist
			}
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index)
	{
		if (index-1 < 0 || index-1 >= songs.size()){//Checks if the specified index is valid
			return false;
		}
		else{

		for (int i = 0; i < playlists.size(); i++)//Iterates through all the playlists in the library
		{
			for (int j = 0; j < playlists.get(i).getContent().size(); j++)//Iterates through all the contents of each playlist
			{
				if (playlists.get(i).getContent().get(j).getType().equalsIgnoreCase("SONG"))//checks if the content is a song
				{
					if (playlists.get(i).getContent().get(j).equals(songs.get(index-1)))//Checks if the song in the playlist is the same as the one being deleted
					{
						playlists.get(i).deleteContent(j+1);//Deletes the song from the playlist
					}
				}
			}
		}
		songs.remove(songs.get(index-1));//Removes the song from the library
		return true;
	}
		
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());//passes in a SongYearComparator object to the method as a comparator to define how to compare the songs by year
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song x, Song y){
			return Integer.compare(x.getYear(), y.getYear());//compares the year of the two song objects using the compare() method
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 
	 Collections.sort(songs, new SongLengthComparator());//passes in a SongLengthComparator object to the method as a comparator to define how to compare the length of songs
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song x, Song y){
			return Integer.compare(x.getLength(), y.getLength());//compares the length of the two song objects the compare() method
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs, new SongNameComparator());//passes in a SongNameComparator object to the method as a comparator to define how to compare the names of songs
	}
	private class SongNameComparator implements Comparator<Song>{
		public int compare(Song x, Song y){
			return x.compareTo(y);//compares the names of the two song objects using the compareTo() method
		}
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public boolean playSong(int index)
	{
		if (index < 1 || index > songs.size())
		{
			errorMsg = "Song Not Found";
			return false;
		}
		songs.get(index-1).play();
		return true;
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public boolean playAudioBook(int index, int chapter)
	{
		if (index <= 0 || index > audiobooks.size()){//If the index is out of bounds, an error message is set and the method returns false.
			errorMsg = "Audiobook Not Found";
			return false;
		}
		
		audiobooks.get(index-1).selectChapter(chapter);
		audiobooks.get(index-1).play();
		// If the index is valid, the audiobook at that index is selected, and the specified chapter is played.
		return true;
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public boolean printAudioBookTOC(int index)
	{
		if (index <= 0 || index > audiobooks.size()){
			errorMsg = "Audiobook Not Found";
			return false;
			//checks if the index is within the range of valid indices, if not it sets an error message and returns false.
		}
		audiobooks.get(index-1).printTOC();//printTOC() method of audiobook is called to print the Table of Contents.
		return true;	
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public boolean makePlaylist(String title)
	{
		Playlist newPlaylist = new Playlist (title);
		for (int i = 0; i < playlists.size(); i++){
			if (playlists.get(i).equals(newPlaylist)){
				this.errorMsg = "Playlist " + title + " Already Exists";
				return false;
				// Check if a playlist with the same title already exists in the library and returns false if so
			}
		}
		playlists.add(newPlaylist);
		return true;
		// If no playlist with the same title exists, add the new playlist to the library and return true
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public boolean printPlaylist(String title)
	{
		for (int i = 0; i < playlists.size(); i++){
			if (playlists.get(i).getTitle().equalsIgnoreCase(title)){//Check if the title of the current playlist matches the specified title
				playlists.get(i).printContents();//// If the title matches, print the contents of the playlist and return tru
				return true;
			}
		}
		this.errorMsg = "Playlist not found";
		return false;	
		//If the specified title was not found, set an error message and return false
	}
	
	// Play all content in a playlist
	public boolean playPlaylist(String playlistTitle)
	{
		for (int i = 0; i < playlists.size(); i++){
			if (playlists.get(i).getTitle().equals(playlistTitle)){
				playlists.get(i).playAll();
				return true;
				//checks if the title of each playlist is equal to the playlistTitle parameter provided. If a match is found, 
				//the playAll() method is called on the matched playlist, which plays all the songs in the playlist and returns true
			}
		}
		this.errorMsg = "Playlist not found";
		return false;
		//If the playlist was not found, set an error message and return false
	}
	
	// Play a specific song/audiobook in a playlist
	public boolean playPlaylist(String playlistTitle, int indexInPL)
	{
		for (int i = 0; i < playlists.size(); i++){
			if (playlists.get(i).getTitle().equals(playlistTitle)){
				playlists.get(i).play(indexInPL);
				return true;
			//if a match is found, it calls the play method of the playlist with the specified index and returns true.
			}
		}
		this.errorMsg = "Playlist not found";
		return false;
		//If the playlist was not found, set an error message and return false
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public boolean addContentToPlaylist(String type, int index, String playlistTitle)
	{
    	
		if ((type.equalsIgnoreCase(Song.TYPENAME)) && (index <= songs.size() || index >= 1)) 
		{
			playlists.get((playlists.indexOf(playlistTitle))+1).addContent(songs.get(index-1));
			return true;
			//If the type is a song, the method checks if the index is within the range of the available songs and if it is, it adds the song to the playlist specified by the playlistTitle.
		}
		else if ((type.equalsIgnoreCase(AudioBook.TYPENAME)) && (index <= audiobooks.size() || index >= 1)) 
		{
			playlists.get(playlists.indexOf(playlistTitle)+1).addContent(audiobooks.get(index-1));
			return true;
			//if the type is an audiobook, the method checks if the index is within the range of the available audiobooks and if it is, it adds the audiobook to the playlist specified by the playlistTitle.
		}
		
		errorMsg = "The content doesn't exist";
		return false;
		//If either of the conditions is not met, the method sets an error message and returns false.
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public boolean delContentFromPlaylist(int index, String title)
	{
		for (int i = 0; i < playlists.size(); i++)
		{
			if (title.equals(playlists.get(i).getTitle()))// Check if the current playlist has the same title as the specified title
			{
				Playlist playlist = playlists.get(i);
				playlist.deleteContent(index);// Call the deleteContent method on the playlist object

				return true;
			}
		}
		return false;
}
	
}

