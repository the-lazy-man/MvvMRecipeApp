package NoteAppExample.NotePresenetation

import NoteAppExample.NoteDomain.NoteViewModel
import NoteAppExample.NoteDomain.NoteViewModelFactory
import NoteAppExample.NoteModel.NoteDatabase
import NoteAppExample.NoteModel.NoteEntity
import NoteAppExample.NoteModel.NoteRepo
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainNoteBinding
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainNoteActivity : AppCompatActivity() {
    val database: NoteDatabase by lazy { NoteDatabase.getDatabase(this) }
    val repository: NoteRepo by lazy { NoteRepo(database.noteDao()) }
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(repository)
    }
    private lateinit var binding: ActivityMainNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter =  NoteAdapter(
                onEditClick = { note -> showNoteDialog(note) },
                onDeleteClick = { note -> noteViewModel.delete(note) }
        )
        binding.notesRecView.adapter = adapter
        binding.notesRecView.layoutManager = LinearLayoutManager(this)
        noteViewModel.getAllNotes.observe(this, Observer { notes ->
            notes?.let { adapter.setNotes(it) }
        })
        findViewById<Button>(R.id.addNoteButton).setOnClickListener {
            showNoteDialog(null)
        }
    }

    // dialog view function
    private fun showNoteDialog(note: NoteEntity?) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null)
        val noteEditText = dialogView.findViewById<EditText>(R.id.noteEditText)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        if (note != null) {
            noteEditText.setText(note.content)
        }

        dialogView.findViewById<Button>(R.id.saveButton).setOnClickListener {
            val content = noteEditText.text.toString()

            if (content.isNotEmpty()) {
                if (note == null) {
                    lifecycleScope.launch(Dispatchers.IO){
                    noteViewModel.insert(NoteEntity(title = "Note", content = content))}
                }
                else {
                    noteViewModel.update(note.copy(content = content))
                }
                dialog.dismiss()
            }
        }

        dialogView.findViewById<Button>(R.id.cancelButton).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}