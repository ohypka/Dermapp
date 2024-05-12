package com.example.dermapp.database

import com.google.firebase.firestore.PropertyName
import com.google.type.DateTime
import java.util.Date

open class Prescription(
    @get:PropertyName("prescriptionId") @set:PropertyName("prescriptionId") open var prescriptionId: String = "",
    @get:PropertyName("doctorId") @set:PropertyName("doctorId") open var doctorId: String = "",
    @get:PropertyName("patientPesel") @set:PropertyName("patientPesel") open var patientPesel: String = "",
    @get:PropertyName("date") @set:PropertyName("date") open var prescriptionDate: DateTime,
    @get:PropertyName("prescriptionText") @set:PropertyName("prescriptionText") open var prescriptionText: String = "",
)