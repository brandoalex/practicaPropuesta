package upao.dam.practicapropuesta;

import android.content.ContentValues;

import java.util.UUID;

public class Nota {
    private String ID;
    private String CATEGORIA;
    private String TITULO;
    private String DESCRIPCION;

    public Nota() {
        this.ID = UUID.randomUUID().toString();
    }

    public Nota(String CATEGORIA, String TITULO, String DESCRIPCION) {
        this.ID = UUID.randomUUID().toString();
        this.CATEGORIA = CATEGORIA;
        this.TITULO = TITULO;
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(BibliotecaSQLiteHelper.NotasContract.NotaEntry.ID, getID());
        values.put(BibliotecaSQLiteHelper.NotasContract.NotaEntry.CATEGORIA, getCATEGORIA());
        values.put(BibliotecaSQLiteHelper.NotasContract.NotaEntry.TITULO, getTITULO());
        values.put(BibliotecaSQLiteHelper.NotasContract.NotaEntry.DESCRIPCION, getDESCRIPCION());
        return values;
    }

    @Override
    public String toString() {
        return "CATEGORIA='" + CATEGORIA + '\'' +
                ", TITULO='" + TITULO + '\'' +
                ", DESCRIPCION='" + DESCRIPCION;
    }
}
