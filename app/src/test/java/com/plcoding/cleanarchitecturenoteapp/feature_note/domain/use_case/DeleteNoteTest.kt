package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import org.junit.Test


class DeleteNoteTest {

    lateinit var fakeNoteRepository: FakeNoteRepository
    lateinit var deleteNote: DeleteNote
    lateinit var getNotes: GetNotes

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        deleteNote = DeleteNote(fakeNoteRepository)
        getNotes = GetNotes(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed{index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }
        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach {
                fakeNoteRepository.insertNote(it)
            }
        }
    }

    @Test
    fun `DeleteNote Test`() = runBlocking {
        var notes = getNotes().first()
        var target = notes[0]

        var result = deleteNote(target)
        notes = getNotes().first()
        assertThat(target).isNotEqualTo(notes[0])
    }

}