package pl.justqubi.taskReminader.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.justqubi.taskReminader.models.UserSession;
import pl.justqubi.taskReminader.models.enteties.NoteEntity;
import pl.justqubi.taskReminader.models.enteties.UserEntity;
import pl.justqubi.taskReminader.models.forms.NoteForm;
import pl.justqubi.taskReminader.models.repositories.NotesRepository;
import pl.justqubi.taskReminader.models.repositories.UserRepository;

import java.util.List;

@Service
public class NotesService {

    final NotesRepository notesRepository;
    final UserSession userSession;
    final UserRepository userRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository, UserSession userSession, UserRepository userRepository) {
        this.notesRepository = notesRepository;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }

    public boolean checkIfNoteExists(String surname) {
        return notesRepository.existsByTitle(surname);
    }

    public void addNote(NoteForm noteForm){
        NoteEntity newNote = new NoteEntity();
        newNote.setTitle(noteForm.getTitle());
        newNote.setPriority(noteForm.getPriority());
        newNote.setDate(noteForm.getDate());
        newNote.setContent(noteForm.getContent());

        notesRepository.save(newNote);
    }

    public void deleteNote(int id) {
        if (userSession.isLogin()) {
            if(userSession.getUserEntity().getNotes()
                    .stream()
                    .anyMatch(s -> s.getId() == id)){
                notesRepository.deleteById(id);
            }
        }
    }

    public List<NoteEntity> getNotes(){
        return notesRepository.findByUser(userSession.getUserEntity());
    }


}
