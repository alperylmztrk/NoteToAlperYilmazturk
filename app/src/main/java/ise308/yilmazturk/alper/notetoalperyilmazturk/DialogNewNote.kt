package ise308.yilmazturk.alper.notetoalperyilmazturk

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
// Inherit from DialogFragment
class DialogNewNote : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)
        // we initialize a LayoutInflater object
        val inflater = activity!!.layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_new_note, null)
        // get a reference to each of the UI widgets
        val editTitle = dialogLayout.findViewById<EditText>(R.id.edit_title)
        val editDescription = dialogLayout.findViewById<EditText>(R.id.edit_description)
        val checkBoxIdea = dialogLayout.findViewById<CheckBox>(R.id.checkBox_idea)
        val checkBoxToDo = dialogLayout.findViewById<CheckBox>(R.id.checkBox_todo)
        val checkBoxImportant = dialogLayout.findViewById<CheckBox>(R.id.checkBox_important)
        val buttonOk = dialogLayout.findViewById<Button>(R.id.button_ok)
        val buttonCancel = dialogLayout.findViewById<Button>(R.id.button_cancel)

        builder.setView(dialogLayout).setMessage(resources.getString(R.string.add_new_note))
        // Handle the cancel button
        buttonCancel.setOnClickListener {
            dismiss()
        }
        buttonOk.setOnClickListener {
            //Create a new note
            val newNote = Note()
            newNote.title = editTitle.text.toString()
            newNote.description = editDescription.text.toString()
            newNote.idea = checkBoxIdea.isChecked
            newNote.todo = checkBoxToDo.isChecked
            newNote.important = checkBoxImportant.isChecked
            // Get a reference to MainActivity
            val callingActivity = activity as MainActivity?
            // Pass newNote back to MainActivity
            callingActivity!!.createNewNote(newNote)
            // Quit the dialog
            dismiss()
        }

        return builder.create()
    }
}