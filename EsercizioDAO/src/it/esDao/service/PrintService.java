package it.esDao.service;

public interface PrintService<T> {
    void saveListAsPdf();
    void saveListAsCsv();
    void saveListAsTxt();
    void saveAsPdf(T t);
    void saveAsCsv(T t);
    void saveAsTxt(T t);
}
