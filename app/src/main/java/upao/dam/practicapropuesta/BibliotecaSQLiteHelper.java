package upao.dam.practicapropuesta;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class BibliotecaSQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bdnotas.db";

    public BibliotecaSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + NotasContract.NotaEntry.TABLE_NAME + " ("
                + NotasContract.NotaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NotasContract.NotaEntry.ID + " TEXT NOT NULL,"
                + NotasContract.NotaEntry.CATEGORIA + " TEXT NOT NULL,"
                + NotasContract.NotaEntry.TITULO + " TEXT NOT NULL,"
                + NotasContract.NotaEntry.DESCRIPCION + " TEXT NOT NULL,"
                + "UNIQUE (" + NotasContract.NotaEntry.ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    public Cursor getAllNotas() {
        return getReadableDatabase()
                .query(NotasContract.NotaEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public void insertNota(SQLiteDatabase db, Nota nota) {
        db.insert(
                NotasContract.NotaEntry.TABLE_NAME,
                null,
                nota.toContentValues());
    }

    /**
     * Esquema de la base de datos para abogados
     */
    class NotasContract {

        abstract class NotaEntry implements BaseColumns {
            static final String TABLE_NAME = "notas";
            public static final String ID = "id";
            public static final String CATEGORIA = "categoria";
            public static final String TITULO = "titulo";
            public static final String DESCRIPCION = "descripcion";
        }
    }
}
