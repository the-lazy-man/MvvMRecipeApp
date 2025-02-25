package com.example.testapp


import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity

class CustomDialog : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_custom_dialog)
//
//        // Button to show the dialog
//        val showDialogButton = findViewById<Button>(R.id.showDialogButton)
//
//        showDialogButton.setOnClickListener {
//            // Create an AlertDialog
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("Confirmation")
//            builder.setMessage("Are you sure you want to proceed?")
//
//            // Positive button
//            builder.setPositiveButton("Yes") { dialog, which ->
//                Toast.makeText(this, "You clicked Yes!", Toast.LENGTH_SHORT).show()
//            }
//
//            // Negative button
//            builder.setNegativeButton("No") { dialog, which ->
//                Toast.makeText(this, "You clicked No!", Toast.LENGTH_SHORT).show()
//            }
//
//            // Neutral button
//            builder.setNeutralButton("Cancel") { dialog, which ->
//                Toast.makeText(this, "You clicked Cancel!", Toast.LENGTH_SHORT).show()
//            }
//
//            // Show the dialog
//            builder.create().show()
//        }
//    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_custom_dialog)

            val showCustomDialogButton = findViewById<Button>(R.id.showDialogButton)

            showCustomDialogButton.setOnClickListener {
                // Use the CustomDialog class
                val customDialog = CustomDialogfun()

                // Call cancel
                customDialog.cancel()
                Toast.makeText(this, "Cancel called on CustomDialog", Toast.LENGTH_SHORT).show()

                // Call dismiss
                customDialog.dismiss()
                Toast.makeText(this, "Dismiss called on CustomDialog", Toast.LENGTH_SHORT).show()
            }
        }
}


class CustomDialogfun : DialogInterface {
    override fun cancel() {
        println("Custom Dialog: Cancel was called.")
    }

    override fun dismiss() {
        println("Custom Dialog: Dismiss was called.")
    }
}
