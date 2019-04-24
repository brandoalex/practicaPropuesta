package upao.dam.practicapropuesta;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spnTipo;
    EditText edtDato;
    EditText edtDescrip;
    Button btnGuardar;
    ListView lvNotas;
    BibliotecaSQLiteHelper bibliotecaSQLiteHelper;
    SQLiteDatabase db;
    List<Nota> ListaNotas = new ArrayList<>();
    ArrayAdapter<Nota> NotaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Cambio de titulo
        getSupportActionBar().setTitle("Laboratorio 5");
        // Instancia de SQLite
        bibliotecaSQLiteHelper = new BibliotecaSQLiteHelper(this);
        db = bibliotecaSQLiteHelper.getWritableDatabase();
        // Declaración de controles
        spnTipo = (Spinner) findViewById(R.id.spnTipo);
        edtDato = (EditText) findViewById(R.id.edtDato);
        edtDescrip = (EditText) findViewById(R.id.edtDecrip);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        lvNotas = (ListView) findViewById(R.id.lvListaNotas);
        LoadData();
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Datos de entrada
                String categoria = spnTipo.getSelectedItem().toString();
                String titulo = edtDato.getText().toString();
                String descrip = edtDescrip.getText().toString();
                // Guardar en SQLite
                bibliotecaSQLiteHelper.insertNota(db, new Nota(categoria, titulo, descrip));
                // Añadir a la lista
                LoadData();
                // Limpiar campos
                edtDato.getText().clear();
                edtDescrip.getText().clear();
            }
        });

    }

    private void LoadData() {
        Cursor c = bibliotecaSQLiteHelper.getAllNotas();
        ListaNotas.clear();
        while (c.moveToNext()) {
            Nota nota = new Nota();
            nota.setCATEGORIA(c.getString(c.getColumnIndex(BibliotecaSQLiteHelper.NotasContract.NotaEntry.CATEGORIA)));
            nota.setTITULO(c.getString(c.getColumnIndex(BibliotecaSQLiteHelper.NotasContract.NotaEntry.TITULO)));
            nota.setDESCRIPCION(c.getString(c.getColumnIndex(BibliotecaSQLiteHelper.NotasContract.NotaEntry.DESCRIPCION)));
            ListaNotas.add(nota);
        }
        NotaAdapter = new ArrayAdapter<Nota>(this, android.R.layout.simple_list_item_1, ListaNotas);
        lvNotas.setAdapter(NotaAdapter);
    }
}
