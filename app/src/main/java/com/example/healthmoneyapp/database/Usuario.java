package com.example.healthmoneyapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "usuario")
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String login;
    @ColumnInfo
    public String cidade;
    @ColumnInfo
    public String senha;
}
