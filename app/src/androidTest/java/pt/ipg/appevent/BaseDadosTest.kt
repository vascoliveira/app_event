package pt.ipg.appevent

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BaseDadosTest {
    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BdHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereTipoEvento(db: SQLiteDatabase, eventos: TipoEventos) {
        eventos.id = TabelaBDTipoEvento(db).insert(eventos.toContentValues())
        assertNotEquals(-1, eventos.id)
    }

    private fun insereOrganizador(db: SQLiteDatabase, organizador: Organizador) {
        organizador.id = TabelaBDOrganizador(db).insert(organizador.toContentValues())
        assertNotEquals(-1, organizador.id)
    }

    private fun insereLocalidade(db: SQLiteDatabase, localidade: Localidade) {
        localidade.id = TabelaBDLocalidade(db).insert(localidade.toContentValues())
        assertNotEquals(-1, localidade.id)
    }

    private fun insereEvento(db: SQLiteDatabase, evento: Evento) {
        evento.id = TabelaBDEvento(db).insert(evento.toContentValues())
        assertNotEquals(-1, evento.id)
    }


    @Before
    fun apagaBaseDados() {
        appContext().deleteDatabase(BdHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BdHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueInserirTipoEvento() {
        val db = getWritableDatabase()

        insereTipoEvento(db, TipoEventos("Educacao"))

        db.close()
    }

    @Test
    fun consegueInserirOrganizador() {
        val db = getWritableDatabase()

        insereOrganizador(db, Organizador("Vasco", 25, "912392239", "vascodasd@gamil.com"))

        db.close()
    }

    @Test
    fun consegueInserirLocalidade() {
        val db = getWritableDatabase()

        insereLocalidade(db, Localidade("Lisboa"))

        db.close()
    }

    @Test
    fun consegueInserirEvento() {
        val db = getWritableDatabase()

        val organizador = Organizador("Vasco", 23, "2321312", "asdasdasd@adasd")
        insereOrganizador(db, organizador)

        val localidade = Localidade("Lisboa")
        insereLocalidade(db, localidade)

        val tipo_evento = TipoEventos("Aventura")
        insereTipoEvento(db, tipo_evento)

        insereEvento(
            db,
            Evento(
                "Docas",
                "25/04/2022",
                "Evento no mar",
                organizador.id,
                localidade.id,
                tipo_evento.id
            )
        )

        db.close()
    }

    @Test
    fun consegueAlterarLocalidade() {
        val db = getWritableDatabase()

        val localidade = Localidade("LISBOA")
        insereLocalidade(db, localidade)

        localidade.Nome_Localidade = "Aveiro"

        val registosAlterados = TabelaBDLocalidade(db).update(
            localidade.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${localidade.id}")
        )

        assertEquals(1, registosAlterados)

        db.close()

    }
    @Test
    fun consegueAlterarOrganizador() {
        val db = getWritableDatabase()

        val organizador = Organizador("Joao",37,"91919911","nnajaajaj@jajja.com")
        insereOrganizador(db, organizador)

        organizador.Nome_organizador = "vasco"
        organizador.Telemovel= "919199191"
        organizador.email = "asdas@gasasd.com"
        organizador.idade = 43

        val registosAlterados = TabelaBDOrganizador(db).update(
            organizador.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${organizador.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }


    @Test
    fun consegueEliminarTipoEvento() {
        val db = getWritableDatabase()

        val tipoevento = TipoEventos("tecnologico")
        insereTipoEvento(db, tipoevento)

        val registosEliminados = TabelaBDTipoEvento(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${tipoevento.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }
}














