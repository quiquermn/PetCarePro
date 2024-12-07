package com.example.petcarepro.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.petcarepro.model.Mascota;

import java.util.ArrayList;
import java.util.List;

public class Mascotas {
    private DatabaseAdmin db;

    public Mascotas(DatabaseAdmin db) {
        this.db = db;
    }

    // db.execSQL("CREATE TABLE Mascota (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, especie TEXT, raza TEXT, fechaNacimiento TIMESTAMP, idUsuario INTEGER)");
    public Mascota insertarMascota(Mascota mascota) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", mascota.getNombre());
        contentValues.put("especie", mascota.getEspecie());
        contentValues.put("raza", mascota.getRaza());
        contentValues.put("fechaNacimiento", mascota.getFechaNacimiento());
        contentValues.put("idUsuario", mascota.getIdUsuario());

        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        long newId = sqLiteDatabase.insert("Mascota", null, contentValues);

        if (newId == -1 || newId == 0) {
            return null;
        }
        return new Mascota(mascota, (int) newId);
    }

    public List<Mascota> getMascotasFromCurUser() {
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor fila = sqLiteDatabase.rawQuery("SELECT id, nombre, especie, raza, fechaNacimiento, idUsuario FROM Mascota WHERE idUsuario = (SELECT idUsuario FROM CurUser)", null);

        List<Mascota> lista = new ArrayList<>();

        while (fila.moveToNext()) {
            String nombre = fila.getString(1);
            String especie = fila.getString(2);
            String raza = fila.getString(3);
            String fechaNacimiento = fila.getString(4);
            int idUsuario = fila.getInt(5);

            Mascota mascota = new Mascota(nombre, especie, raza, fechaNacimiento, idUsuario);
            mascota.setId(fila.getInt(0));

            lista.add(mascota);
        }
        fila.close();

        return lista;
    }

    public boolean eliminarMascota(int idMascota) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        int eliminados = sqLiteDatabase.delete("Mascota", "id = " + idMascota, null);
        return eliminados > 0;
    }

    public boolean editarMascota(Mascota mascota) {
        if (mascota.getId() == 0) {
            return false;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", mascota.getNombre());
        contentValues.put("especie", mascota.getEspecie());
        contentValues.put("raza", mascota.getRaza());
        contentValues.put("fechaNacimiento", mascota.getFechaNacimiento());

        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        int actualizados = sqLiteDatabase.update("Mascota", contentValues, "id = " + mascota.getId(), null);

        return actualizados > 0;
    }
}
