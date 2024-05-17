package com.example.healthmoneyapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class},version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract UsuarioDao getUserDao();
}
