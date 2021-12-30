package maykeee.music.entities;

public class Music {
	
	private String music;
	private String artist;
	private String path;
	private String format;

	public Music(String music, String artist, String path, String format) {
		this.music = music;
		this.artist = artist;
		this.path = path;
		this.format = format;
	}
	
	public Music() {
	}
	
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
}
