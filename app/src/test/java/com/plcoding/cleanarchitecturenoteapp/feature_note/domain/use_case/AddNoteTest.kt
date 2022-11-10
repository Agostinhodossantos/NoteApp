package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import org.junit.Before
import org.junit.Test
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import org.junit.Assert
import org.junit.Assert.assertThrows


class AddNoteTest {
    lateinit var fakeNoteRepository: FakeNoteRepository
    lateinit var addNote: AddNote
    lateinit var note: Note
    @Before
    fun setup(){

        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNote(fakeNoteRepository)
        note = Note(content = "a", timestamp = 1, color = 0, title = "")
    }

    @Test
    fun `AddNotes title must not be blank`() = runBlocking {
        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                var result = addNote(note)
            }
        }

        assertThat("The title of the note can't be empty.").isEqualTo(exception.message)
    }

    @Test
    fun `AddNotes The content of the note can't be empty`() = runBlocking {
        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                note.title = "content"
                note.content = ""
                var result = addNote(note)
            }
        }

        assertThat("The content of the note can't be empty.").isEqualTo(exception.message)
    }

    @Test
    fun `AddNotes must not be null`() = runBlocking {
        note.title = "content"
        var result = addNote(note)
        assertThat(result).isNotNull()
    }

}
