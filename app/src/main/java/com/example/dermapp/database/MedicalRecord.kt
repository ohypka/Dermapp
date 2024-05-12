package com.example.dermapp.database

import com.google.firebase.firestore.PropertyName
import com.google.type.DateTime
import java.util.Date

open class MedicalRecord(
    @get:PropertyName("medicalRecordId") @set:PropertyName("medicalRecordId") open var medicalRecordId: String = "",
    @get:PropertyName("doctorId") @set:PropertyName("doctorId") open var doctorId: String = "",
    @get:PropertyName("patientPesel") @set:PropertyName("patientPesel") open var patientPesel: String = "",
    @get:PropertyName("date") @set:PropertyName("date") open var medicalRecordDate: DateTime,
    @get:PropertyName("type") @set:PropertyName("type") open var type: String = "",
    @get:PropertyName("attachment") @set:PropertyName("attachment") open var attachment: String = ""
)