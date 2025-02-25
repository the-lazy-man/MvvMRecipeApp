package NoteAppExample.NoteDomain
import NoteAppExample.NoteModel.NoteEntity
import NoteAppExample.NoteModel.NoteRepo
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(private var noteRepo : NoteRepo) : ViewModel(){
    val getAllNotes = noteRepo.allNotes

    fun insert(note: NoteEntity) = viewModelScope.launch {
        noteRepo.insertNoteFun(note)
    }

    fun update(note: NoteEntity) = viewModelScope.launch {
        noteRepo.updateNoteFun(note)

    }
    fun delete(note: NoteEntity) = viewModelScope.launch {
        noteRepo.deleteNoteFun(note)
    }


    fun collapseComment() {


        //    suspend fun insertNote(noteEntity : NoteEntity){
        //        noteRepo.insertNoteFun(noteEntity)
        //    }
        //    suspend fun updateNote(noteEntity : NoteEntity){
        //        noteRepo.updateNoteFun(noteEntity)
        //    }
        //    suspend fun deleteNote(noteEntity : NoteEntity){
        //        noteRepo.deleteNoteFun(noteEntity)
        //    }
    }

}

class NoteViewModelFactory(private val noteRepo: NoteRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}