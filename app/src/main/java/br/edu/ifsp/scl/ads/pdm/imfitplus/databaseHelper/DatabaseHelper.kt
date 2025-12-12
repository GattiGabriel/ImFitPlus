package br.edu.ifsp.scl.ads.pdm.imfitplus.databaseHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "agendaImFitPlus.db"
        private const val DATABASE_VERSION = 3

        const val TABLE_FICHA = "ficha"

        const val COL_ID = "id"
        const val COL_NOME = "nome"
        const val COL_IDADE = "idade"
        const val COL_SEXO = "sexo"
        const val COL_ALTURA = "altura"
        const val COL_PESO = "peso"
        const val COL_NVL_ATIVIDADE = "nivelAtividade"
        const val COL_IMC = "imc"
        const val COL_IMC_CATEG = "categoriaImc"
        const val COL_TMB = "tmb"
        const val COL_PESO_IDEAL = "pesoIdeal"
        const val COL_FREQ_CARDIACA = "freqCardiacaMaxima"
        const val COL_DATA_NASC = "dataNasc"
        const val COL_TREINO_LEVE = "zonaTreinoLeve"
        const val COL_TREINO_QUEIMA_GORDURA = "zonaTreinoQueimaGordura"
        const val COL_TREINO_AEROBICA = "zonaTreinoAerobica"
        const val COL_TREINO_ANAEROBICA = "zonaTreinoAnaerobica"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_FICHA (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NOME TEXT NOT NULL,
                $COL_IDADE INTEGER NOT NULL,
                $COL_SEXO TEXT NOT NULL,
                $COL_ALTURA INTEGER NOT NULL,
                $COL_PESO INTEGER NOT NULL,
                $COL_NVL_ATIVIDADE TEXT NOT NULL,
                $COL_IMC REAL NOT NULL,
                $COL_IMC_CATEG TEXT NOT NULL,
                $COL_TMB REAL NOT NULL,
                $COL_PESO_IDEAL REAL NOT NULL,
                $COL_FREQ_CARDIACA INTEGER NOT NULL,
                $COL_DATA_NASC TEXT NOT NULL,
                $COL_TREINO_LEVE TEXT NOT NULL,
                $COL_TREINO_QUEIMA_GORDURA TEXT NOT NULL,
                $COL_TREINO_AEROBICA TEXT NOT NULL,
                $COL_TREINO_ANAEROBICA TEXT NOT NULL
            );
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FICHA")
        onCreate(db)
    }
}
