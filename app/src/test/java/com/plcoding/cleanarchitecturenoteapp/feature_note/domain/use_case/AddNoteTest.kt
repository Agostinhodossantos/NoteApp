package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import org.junit.Assert.*
import org.junit.Before

class AddNoteTest {
    private lateinit var getNotes: GetNotes

    @Before
    fun setUp() {
        getNotes = GetNotes()
    }
}