package pt.ipg.appevent

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDLocalidade (db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {

        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $NOME_LOCALIDADE TEXT NOT NULL)")
    }

    companion object {
        const val NOME = "LOCALIDADES"

        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val NOME_LOCALIDADE = "Nome_localidade"

        val TODAS_COLUNAS = arrayOf (CAMPO_ID, NOME_LOCALIDADE)

    }
}