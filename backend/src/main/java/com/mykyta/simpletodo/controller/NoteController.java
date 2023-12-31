package com.mykyta.simpletodo.controller;

import com.mykyta.simpletodo.data.NoteData;
import com.mykyta.simpletodo.dto.NoteDTO;
import com.mykyta.simpletodo.facade.NoteFacade;
import com.mykyta.simpletodo.service.impl.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "*")
public class NoteController {
	private final NoteFacade noteFacade;
	private final MapperService mapperService;

	@Autowired
	public NoteController(final NoteFacade noteFacade, final MapperService mapperService) {
		this.noteFacade = noteFacade;
		this.mapperService = mapperService;
	}

	@GetMapping
	public List<NoteDTO> getNotes() {
		return mapNotes(noteFacade.getNotes());
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public NoteDTO addNote(@RequestBody final NoteDTO note) {
		final NoteData noteData = mapperService.map(note, NoteData.class);

		var obj = new Object() {};
		System.out.println(obj);

		return mapNote(noteFacade.addNote(noteData));
	}

	private NoteDTO mapNote(final NoteData source) {
		return mapperService.map(source, NoteDTO.class);
	}

	private List<NoteDTO> mapNotes(final List<NoteData> sources) {
		return mapperService.mapAll(sources, NoteDTO.class);
	}
}
