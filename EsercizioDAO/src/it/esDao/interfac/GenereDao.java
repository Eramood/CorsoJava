package it.esDao.interfac;

import java.util.List;

import it.esDao.model.Genere;

public interface GenereDao {
    List<Genere> getAll();
    Genere getGenereById(int codiceG);
    void insert(Genere genere);
    void update(Genere genere);
    void delete(Genere genere);
}