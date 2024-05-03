package by.bsuir.discussion.controllers;

import by.bsuir.discussion.dto.requests.NoteRequestDto;
import by.bsuir.discussion.dto.responses.NoteResponseDto;
import by.bsuir.discussion.exceptions.EntityExistsException;
import by.bsuir.discussion.exceptions.Messages;
import by.bsuir.discussion.exceptions.NoEntityExistsException;
import by.bsuir.discussion.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponseDto> create(@RequestBody NoteRequestDto note) throws EntityExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.create(note));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDto> read(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.read(id).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.readAll());
    }

    @PutMapping
    public ResponseEntity<NoteResponseDto> update(@RequestBody NoteRequestDto note) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.update(note));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(noteService.delete(id));
    }
}
