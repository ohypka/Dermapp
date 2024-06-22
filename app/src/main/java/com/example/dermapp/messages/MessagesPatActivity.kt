package com.example.dermapp.messages

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dermapp.R
import com.example.dermapp.database.Doctor
import com.example.dermapp.NewMessagePatActivity
import com.example.dermapp.startPatient.StartPatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MessagesPatActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapterMessagesPat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages_pat)

        recyclerView = findViewById(R.id.recyclerViewMessagesPat)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapterMessagesPat(this, mutableListOf())
        recyclerView.adapter = adapter

        val header = findViewById<LinearLayout>(R.id.backHeaderPat)
        backButton = header.findViewById(R.id.arrowButton)

        backButton.setOnClickListener {
            val intent = Intent(this, StartPatActivity::class.java)
            startActivity(intent)
        }

        // Apply system bars insets to the main layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_messages_pat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fetch doctors list and populate RecyclerView
        fetchDoctorsList()
    }

    private fun fetchDoctorsList() {
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid

        currentUserUid?.let { uid ->
            val doctorsList = mutableListOf<Doctor>()

            // Fetch doctors from Firestore
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val doctorsSnapshot = FirebaseFirestore.getInstance().collection("doctors").get().await()

                    for (document in doctorsSnapshot.documents) {
                        val doctor = document.toObject(Doctor::class.java)
                        doctor?.let {
                            it.doctorId = document.id // Assign Firestore document ID to doctorId field
                            doctorsList.add(it)
                        }
                    }

                    // Set doctors list in the RecyclerView adapter
                    adapter.setDoctorsList(doctorsList)

                } catch (e: Exception) {
                    // Handle Firestore fetch errors
                    // For example:
                    // Log.e(TAG, "Error fetching doctors", e)
                    // Toast.makeText(this@MessagesPatActivity, "Error fetching doctors", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
