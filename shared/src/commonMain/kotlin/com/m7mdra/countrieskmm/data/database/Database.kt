package com.m7mdra.countrieskmm.data.database

import com.example.AppDatabase

class Database (databaseDriverFactory: DatabaseDriverFactory){
     val database = AppDatabase(databaseDriverFactory.createDriver())

}