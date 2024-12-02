package com.example.petcarepro.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.petcarepro.model.Usuario;

public class Usuarios {
    private DatabaseAdmin db;

    public Usuarios(DatabaseAdmin db) {
        this.db = db;
    }

    public boolean insertarUsuario(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", usuario.getNombre());
        contentValues.put("email", usuario.getEmail());
        contentValues.put("password", usuario.getPassword());

        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        long newId = sqLiteDatabase.insert("Usuario", null, contentValues);

        if (newId == -1 || newId == 0) {
            return false;
        }

        return true;
    }

    public Usuario login(String email, String password) {
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();

        Cursor fila = sqLiteDatabase.rawQuery("SELECT * FROM Usuario WHERE email = '" + email + "' AND password = '" + password + "'", null);
        if (fila.moveToFirst()) {
            Usuario usuario = new Usuario(fila.getString(1), fila.getString(2), fila.getString(3));
            sqLiteDatabase.execSQL("DELETE FROM CurUser");

            ContentValues contentValues = new ContentValues();
            contentValues.put("idUsuario", fila.getInt(0));
            long insertadas = sqLiteDatabase.insert("CurUser", null, contentValues);

            if (insertadas == -1 || insertadas == 0) {
                return null;
            }

            return new Usuario(fila.getString(1), fila.getString(2), fila.getString(3));
        }
        fila.close();

        return null;
    }

    public Usuario getCurUser() {
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor fila = sqLiteDatabase.rawQuery("SELECT * FROM Usuario WHERE id = (SELECT idUsuario FROM CurUser)", null);
        if (fila.moveToFirst()) {
            return new Usuario(fila.getString(1), fila.getString(2), fila.getString(3));
        }
        fila.close();

        return null;
    }

    public void logout() {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM CurUser");
    }

}
