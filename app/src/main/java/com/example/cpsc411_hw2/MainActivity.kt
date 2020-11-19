// CPSC 411-01 - Fall 2020 (Android)
// Julian Coronado
// CWID: 889838066
// Homework #2 - Due November 26th, 2020

package com.example.cpsc411_hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

open class MainActivity : AppCompatActivity() {

    lateinit var cService: ClaimService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // gets ClaimService instance
        cService = ClaimService.getInstance(this)

        // TASK: get input from EditText / UI

        val etClaimTitle : EditText = findViewById(R.id.edit_claimtitle)
        val etClaimDate : EditText = findViewById(R.id.edit_claimdate)
        val addButton : Button = findViewById(R.id.add_btn)
        val statusMsg : TextView = findViewById(R.id.status)

        // listens for button to be clicked
        addButton.setOnClickListener {

            // check if editText fields are empty
            if (TextUtils.isEmpty(etClaimTitle.text.toString()) || TextUtils.isEmpty(etClaimDate.text.toString())) {
                // if either one is empty, throw error message
                statusMsg.text = "Status Message: Empty field not allowed!"
            } else {
                // if both are filled, proceed

                // extracts data from editText fields
                var inputClaim = Claim(etClaimTitle.text.toString(), etClaimDate.text.toString())
                // adds Claim to database
                cService.addClaim(inputClaim)
                // clears editText fields
                etClaimDate.text.clear()
                etClaimTitle.text.clear()
                // updates status message
                statusMsg.text = "Status Message: Claim successfully added."
            }

        }

    }
}