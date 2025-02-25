package NoteAppExample.NoteModel

class NoteRepo(private val noteDao: NoteDao) {
    val allNotes = noteDao.getAllNotes()
    suspend fun insertNoteFun(noteEntity: NoteEntity){
        noteDao.InsertNote(noteEntity)
    }
    suspend fun updateNoteFun(noteEntity: NoteEntity){
        noteDao.UpdateNote(noteEntity)
    }
    suspend fun deleteNoteFun(note: NoteEntity){
        noteDao.DeleteNote(note)
    }


}