package br.edu.ifsp.scl.ads.pdm.imfitplus.fichaRepo

import android.content.ContentValues
import android.content.Context
import br.edu.ifsp.scl.ads.pdm.imfitplus.databaseHelper.DatabaseHelper
import br.edu.ifsp.scl.ads.pdm.imfitplus.fichaDTO.FichaDTO

class FichaRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun inserir(ficha: FichaDTO): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COL_NOME, ficha.nome)
            put(DatabaseHelper.COL_IDADE, ficha.idade)
            put(DatabaseHelper.COL_SEXO, ficha.sexo)
            put(DatabaseHelper.COL_ALTURA, ficha.altura)
            put(DatabaseHelper.COL_PESO, ficha.peso)
            put(DatabaseHelper.COL_NVL_ATIVIDADE, ficha.nivelAtvFisica)
            put(DatabaseHelper.COL_IMC, ficha.imc)
            put(DatabaseHelper.COL_IMC_CATEG, ficha.imcCategoria)
            put(DatabaseHelper.COL_TMB, ficha.tmb)
            put(DatabaseHelper.COL_PESO_IDEAL, ficha.pesoIdeal)
            put(DatabaseHelper.COL_FREQ_CARDIACA, ficha.freqCardiacaMaxima)
            put(DatabaseHelper.COL_DATA_NASC, ficha.dataNasc)
            put(DatabaseHelper.COL_TREINO_LEVE, ficha.zonaTreinoLeve)
            put(DatabaseHelper.COL_TREINO_QUEIMA_GORDURA, ficha.zonaTreinoQueimaGordura)
            put(DatabaseHelper.COL_TREINO_AEROBICA, ficha.zonaTreinoAerobica)
            put(DatabaseHelper.COL_TREINO_ANAEROBICA, ficha.zonaTreinoAnaerobica)
        }
        return db.insert(DatabaseHelper.TABLE_FICHA, null, values)
    }

    fun listar(): List<FichaDTO> {
        val lista = mutableListOf<FichaDTO>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_FICHA,
            null, null, null, null, null, "${DatabaseHelper.COL_ID} DESC"
        )

        with(cursor) {
            while (moveToNext()) {
                lista.add(
                    FichaDTO(
                        id = getInt(getColumnIndexOrThrow(DatabaseHelper.COL_ID)),
                        nome = getString(getColumnIndexOrThrow(DatabaseHelper.COL_NOME)),
                        idade = getInt(getColumnIndexOrThrow(DatabaseHelper.COL_IDADE)),
                        sexo = getString(getColumnIndexOrThrow(DatabaseHelper.COL_SEXO)),
                        altura = getInt(getColumnIndexOrThrow(DatabaseHelper.COL_ALTURA)),
                        peso = getInt(getColumnIndexOrThrow(DatabaseHelper.COL_PESO)),
                        nivelAtvFisica = getString(getColumnIndexOrThrow(DatabaseHelper.COL_NVL_ATIVIDADE)),
                        imc = getDouble(getColumnIndexOrThrow(DatabaseHelper.COL_IMC)),
                        imcCategoria = getString(getColumnIndexOrThrow(DatabaseHelper.COL_IMC_CATEG)),
                        tmb = getDouble(getColumnIndexOrThrow(DatabaseHelper.COL_TMB)),
                        pesoIdeal = getDouble(getColumnIndexOrThrow(DatabaseHelper.COL_PESO_IDEAL)),
                        freqCardiacaMaxima = getInt(getColumnIndexOrThrow(DatabaseHelper.COL_FREQ_CARDIACA)),
                        dataNasc = getString(getColumnIndexOrThrow(DatabaseHelper.COL_DATA_NASC)),
                        zonaTreinoLeve = getString(getColumnIndexOrThrow(DatabaseHelper.COL_TREINO_LEVE)),
                        zonaTreinoQueimaGordura = getString(getColumnIndexOrThrow(DatabaseHelper.COL_TREINO_QUEIMA_GORDURA)),
                        zonaTreinoAerobica = getString(getColumnIndexOrThrow(DatabaseHelper.COL_TREINO_AEROBICA)),
                        zonaTreinoAnaerobica = getString(getColumnIndexOrThrow(DatabaseHelper.COL_TREINO_ANAEROBICA))
                    )
                )
            }
        }

        cursor.close()
        return lista
    }

    fun buscarPorId(id: Int): FichaDTO? {
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            DatabaseHelper.TABLE_FICHA,
            null,
            "${DatabaseHelper.COL_ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        return if (cursor.moveToFirst()) {
            val ficha = FichaDTO(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID)),
                nome = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOME)),
                idade = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_IDADE)),
                sexo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SEXO)),
                altura = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ALTURA)),
                peso = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PESO)),
                nivelAtvFisica = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NVL_ATIVIDADE)),
                imc = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_IMC)),
                imcCategoria = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_IMC_CATEG)),
                tmb = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TMB)),
                pesoIdeal = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PESO_IDEAL)),
                freqCardiacaMaxima = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_FREQ_CARDIACA)),
                dataNasc = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DATA_NASC)),
                zonaTreinoLeve = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TREINO_LEVE)),
                zonaTreinoQueimaGordura = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TREINO_QUEIMA_GORDURA)),
                zonaTreinoAerobica = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TREINO_AEROBICA)),
                zonaTreinoAnaerobica = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TREINO_ANAEROBICA))
            )
            cursor.close()
            ficha
        } else {
            cursor.close()
            null
        }
    }

    fun atualizar(ficha: FichaDTO) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DatabaseHelper.COL_NOME, ficha.nome)
            put(DatabaseHelper.COL_IDADE, ficha.idade)
            put(DatabaseHelper.COL_SEXO, ficha.sexo)
            put(DatabaseHelper.COL_ALTURA, ficha.altura)
            put(DatabaseHelper.COL_PESO, ficha.peso)
            put(DatabaseHelper.COL_NVL_ATIVIDADE, ficha.nivelAtvFisica)
            put(DatabaseHelper.COL_IMC, ficha.imc)
            put(DatabaseHelper.COL_IMC_CATEG, ficha.imcCategoria)
            put(DatabaseHelper.COL_TMB, ficha.tmb)
            put(DatabaseHelper.COL_PESO_IDEAL, ficha.pesoIdeal)
        }

        db.update(
            DatabaseHelper.TABLE_FICHA,
            values,
            "${DatabaseHelper.COL_ID} = ?",
            arrayOf(ficha.id.toString())
        )
    }

    fun deletar(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete(
            DatabaseHelper.TABLE_FICHA,
            "${DatabaseHelper.COL_ID} = ?",
            arrayOf(id.toString())
        )
    }
}
