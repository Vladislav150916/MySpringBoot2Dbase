package ru.golyakov.MySpringBoot2Dbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.golyakov.MySpringBoot2Dbase.entity.Discipline;
import ru.golyakov.MySpringBoot2Dbase.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/disciplines")
    public List<Discipline> allDisciplines() {
        List<Discipline> allDisciplines = disciplineService.getAllDisciplines();
        return allDisciplines;
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<?> getDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Дисциплина с заданным id не найдена");
        }
        return ResponseEntity.ok(discipline);
    }

    @PostMapping("/disciplines")
    public ResponseEntity<?> saveDiscipline(@RequestBody Discipline discipline) {
        if (discipline.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("При создании дисциплины не указывайте id");
        }
        Discipline savingDiscipline = disciplineService.saveDiscipline(discipline);

        return ResponseEntity.status(HttpStatus.CREATED).body(savingDiscipline);
    }

    @PutMapping("/disciplines")
    public ResponseEntity<?> updateDiscipline(@RequestBody Discipline discipline) {
        Integer id = discipline.getId();
        if (id == null) {
            return ResponseEntity.badRequest().body("Для обновления дисциплины укажите id");
        }
        if (disciplineService.getDiscipline(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Дисциплина с данным id не найдена");
        }
        Discipline updatingDiscipline = disciplineService.saveDiscipline(discipline);
        return ResponseEntity.ok(updatingDiscipline);
    }

    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<?> deleteDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Дисциплина с данным id не найдена");
        }
        disciplineService.deleteDiscipline(id);

        return ResponseEntity.noContent().build();
    }

}