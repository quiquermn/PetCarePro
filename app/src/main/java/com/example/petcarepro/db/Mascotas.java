package com.example.petcarepro.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.petcarepro.model.Mascota;

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

        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        long newId = sqLiteDatabase.insert("Mascota", null, contentValues);

        if (newId == -1 || newId == 0) {
            return null;
        }
        return new Mascota(mascota, (int) newId);
    }
}
