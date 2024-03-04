//Joseph Henok (501165533)
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		for (int i = 0; i < contents.size(); i++){
			System.out.print((i+1) + ". ");
			contents.get(i).printInfo();
			System.out.println();
			//prints formatting for the contents of the playlist
		}
		
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for (int i = 0; i < contents.size(); i++){
			if (contents.get(i).getType() == "SONG"){
				Song otherSong = (Song) contents.get(i);
				otherSong.play();
				//If the content is a song, it is cast to a Song object and played using the play() method
			}
			else if (contents.get(i).getType() == "AUDIOBOOK"){
				AudioBook OtherAudiobook = (AudioBook) contents.get(i);
				OtherAudiobook.play();
				//If the content is an audiobook, it is cast to an AudioBook object and played using the play() method
			}
		}
		
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if (index >= 1 && index <= contents.size()){//checks if index is valid
			if (contents.get(index-1).getType() == "SONG"){
			Song otherSong = (Song) contents.get(index-1);
			otherSong.play();
			System.out.println("\n");
			//if the type of content is a song then its played using an object of the song class
		}
		else if (contents.get(index-1).getType() == "AUDIOBOOK"){
			AudioBook OtherAudiobook = (AudioBook) contents.get(index-1);
			OtherAudiobook.play();
			System.out.println("\n");
			//if the type of content is a audiobook then its played using an object of the audiobook class
		}
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		Playlist otherTitle = (Playlist) other;
		if (this.title.equals(otherTitle.title)){//checks if titles are equals using an other object of playlist
			return true;
		}
		return false;
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return;
		contents.remove(index-1);
	}
	
	
}
