package com.example.petcarepro.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdmin extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "petcareprodb";
    private static final int DATABASE_VERSION = 1;

    public DatabaseAdmin(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT UNIQUE, password TEXT)");
        db.execSQL("CREATE TABLE Mascota (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, especie TEXT, raza TEXT, fechaNacimiento TIMESTAMP, idUsuario INTEGER)");
        db.execSQL("CREATE TABLE CurUser (idUsuario INTEGER PRIMARY KEY)"); // Para guardar el usuario actual
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
