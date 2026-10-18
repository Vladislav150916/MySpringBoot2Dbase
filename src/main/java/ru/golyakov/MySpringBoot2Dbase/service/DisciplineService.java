package ru.golyakov.MySpringBoot2Dbase.service;

import org.springframework.stereotype.Service;
import ru.golyakov.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

@Service
public interface DisciplineService {

    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline Discipline);

    Discipline getDiscipline(int id);

    void deleteDiscipline(int id);
    
}
