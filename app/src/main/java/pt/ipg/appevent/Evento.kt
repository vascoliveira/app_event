package pt.ipg.appevent

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Evento (
    var Nome_Evento : String,
    var Data: String,
    var Descricao: String,
    var organizador: String,
    var localidade: Long,
    var tipo_eventos: TipoEventos,
    var id: Long = -1
): Serializable {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDEvento.NOME_EVENTO, Nome_Evento)
        valores.put(TabelaBDEvento.DATA, Data)
        valores.put(TabelaBDEvento.DESCRICAO, Descricao)
        valores.put(TabelaBDEvento.ORGANIZADOR_ID, organizador)
        valores.put(TabelaBDEvento.LOCALIDADE_ID, localidade)
        valores.put(TabelaBDEvento.TIPO_EVENTOS_ID, tipo_eventos.id)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Evento {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNom = cursor.getColumnIndex(TabelaBDEvento.NOME_EVENTO)
            val posData = cursor.getColumnIndex(TabelaBDEvento.DATA)
            val posDesc = cursor.getColumnIndex(TabelaBDEvento.DESCRICAO)
            val posIdOrganizador = cursor.getColumnIndex(TabelaBDEvento.ORGANIZADOR_ID)
            val posIdLocalidade = cursor.getColumnIndex(TabelaBDEvento.LOCALIDADE_ID)
            val posIdTipoEventos = cursor.getColumnIndex(TabelaBDEvento.TIPO_EVENTOS_ID)
            val posNomeTipoEvento = cursor.getColumnIndex(TabelaBDTipoEvento.TIPO_EVENTO)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNom)
            val data = cursor.getString(posData)
            val descricao = cursor.getString(posDesc)
            val idOrganizador = cursor.getString(posIdOrganizador)
            val idLocalidade = cursor.getLong(posIdLocalidade)
            val idTipoEvento = cursor.getLong(posIdTipoEventos)
            val nomeTipoEventos = cursor.getString(posNomeTipoEvento)

            val tipoEvento = TipoEventos(nomeTipoEventos, idTipoEvento)



            return Evento (nome, data, descricao, idOrganizador, idLocalidade, tipoEvento, id)
        }
    }
}