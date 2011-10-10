package com.google.code.iriyanote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteShelf {
	
	private Map<Integer, List<Note>> shelf = new HashMap<Integer, List<Note>>();

	public NoteShelf(List<Note> notes) {
		for (Note note : notes) {
			getNotes(note.getShowDay()).add(note);
		}
	}
	
	public List<Note> getNotes(Integer showDay) {
		List<Note> notes = shelf.get(showDay);
		if(notes == null) {
			notes = new ArrayList<Note>();
			shelf.put(showDay, notes);
		}
		return notes;
	}

}
