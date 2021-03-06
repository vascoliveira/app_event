package pt.ipg.appevent

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Organizador (
    var nomeOrganizador : String,
    var idade: String,
    var telemovel: String,
    var email: String,
    var id: Long = -1
) : Serializable {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(TabelaBDOrganizador.NOME_ORGANIZADOR, nomeOrganizador)
        valores.put(TabelaBDOrganizador.IDADE, idade)
        valores.put(TabelaBDOrganizador.TELEMOVEL, telemovel)
        valores.put(TabelaBDOrganizador.EMAIL, email)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Organizador {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDOrganizador.NOME)
            val posEmail = cursor.getColumnIndex(TabelaBDOrganizador.EMAIL)
            val posIdade = cursor.getColumnIndex(TabelaBDOrganizador.IDADE)
            val posTelemovel = cursor.getColumnIndex(TabelaBDOrganizador.TELEMOVEL)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val email = cursor.getString(posEmail)
            val idade = cursor.getString(posIdade)
            val telemovel = cursor.getString(posTelemovel)

            return Organizador(nome,idade,telemovel,email,id)
        }
    }
}