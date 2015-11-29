package com.groupProject.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.Session;

import javafx.scene.chart.PieChart.Data;

@Entity
public class Song {
	@Id
	@Column(nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int songId;
	
	@Column(nullable=true)
	private String albumName;
	@Column(nullable=false)
	private String name, length, author, mediaType;
	@ManyToOne
	private User user;
	public Song() {}

	
	public Song(String albumName, String name, String length, String author, String mediaType, User user) {
		super();
		this.albumName = albumName;
		this.name = name;
		this.length = length;
		this.author = author;
		this.mediaType = mediaType;
		this.user = user;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	@Override
	public String toString() {
		return songId + "," + albumName + "," + name + "," + length + "," + author + "," + mediaType;
	}

	public HashMap<String, String> getSongErrors() {
		HashMap<String, String> errors = new HashMap<String, String>();
		if (this.getName().length() < 1) {
			errors.put("name", "Song Name cannot be empty");
		}
		if (this.getAuthor().length() < 1) {
			errors.put("author", "Song Author cannot be empty");
		}
		if (!this.getLength().matches("[0-9][0-9]:[0-9][0-9]")) {
			errors.put("length", "Song length must be in the format mm:ss");
		}
		List<String> options = Arrays.asList("Vinyl,CD,MP3 Player,Computer".split(","));
		Arrays.asList(this.getMediaType().split(",")).forEach((e) -> {
			if (!options.contains(e)) {
				errors.put("mediaType", "That was not an option");
				return;
			}
		});
		return errors;
	}
	
	public static Song getSongById(int id) {
		Session session = Database.getNewSession();
		Song song = (Song) session.get(Song.class, id);
		session.close();
		return song;
	}
	
	public boolean saveSong() {
		if (this.getSongErrors().isEmpty()) {
			if (Database.saveToDatabase(this)) {
				return true;
			} else {
				System.out.println("Save failed");
				return false;
			}
		}
		System.out.println("Song had errors");
		return false;
	}
	
	public static void main(String[] args) {
	}
	
}
