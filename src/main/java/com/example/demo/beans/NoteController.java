package com.example.demo.beans;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "index";
    }

    @PostMapping("/add")
    @Transactional
    public String addNote(@RequestParam String title, @RequestParam String content) {
        Note note = new Note();
        //note.setId(0 + (long) (Math.random() * (1000 - 0)));
        note.setTitle(title);
        note.setContent(content);
        noteRepository.save(note);
        return "redirect:/";
    }
}
