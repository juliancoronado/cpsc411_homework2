package com.example.cpsc411_hw2

import android.util.Log
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity

class ClaimService (val ctx: MainActivity) {

    companion object {
        private var cService : ClaimService? = null
        fun getInstance(act: MainActivity) : ClaimService {
            if (cService == null) {
                cService = ClaimService(act)
            }

            return cService!!
        }
    }

    inner class addServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            if (responseBody != null) {
                val respStr = String(responseBody)
                Log.d("Claim Service", "The add Claim service response: ${respStr}")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            Log.d("Claim Service", "Error from ClaimService.kt -> addClaim")
        }

    }

    fun addClaim(cObj : Claim) {
        // creates AsyncHttp client
        val client = AsyncHttpClient()

        // my personal IP address
        val requestUrl = "http://192.168.86.27:8040/ClaimService/add"

        // convert from Claim object to JSON string
        val claimJsonString = Gson().toJson(cObj)
        val entity = StringEntity(claimJsonString)

        // POST JSON string to server
        client.post(ctx, requestUrl, entity, "application/json", addServiceRespHandler())
    }
}