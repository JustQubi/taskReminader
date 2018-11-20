package pl.justqubi.taskReminader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.justqubi.taskReminader.models.UserSession;
import pl.justqubi.taskReminader.models.forms.NoteForm;
import pl.justqubi.taskReminader.models.services.NotesService;

@Controller
public class NotesController {

    final NotesService notesService;
    final UserSession userSession;

    @Autowired
    public NotesController(NotesService notesService, UserSession userSession) {
        this.notesService = notesService;
        this.userSession = userSession;
    }

    @GetMapping("/note/add")
    public String showAddNoteForm(Model model){
        model.addAttribute("noteForm", new NoteForm());
        return "noteForm";
    }

    @PostMapping("/note/add")
    public String getDataFromNoteForm(@ModelAttribute NoteForm noteForm,
                                      Model model){
        if(notesService.checkIfNoteExists(noteForm.getTitle())){
            model.addAttribute("isTitleBusy", true);
            return "noteForm";
        }
        notesService.addNote(noteForm);

        return "noteFotm";
    }

    @GetMapping("/note/show")
    public String showAllNotes(Model model) {
        if (!userSession.isLogin()) {
            return "redirect:/user/login";
        }
        model.addAttribute("notes", notesService.getNotes());
        return "notesList";
    }
}
