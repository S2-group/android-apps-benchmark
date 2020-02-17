package e.www.gpstest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.*

// Constants for the frequencies
val low_frequency: Long = 3000 // 3 seconds
val medium_frequency: Long = 2000 // 2 seconds
val high_frequency: Long = 1000 // 1 second

// Constants for the priority
val high_priority = LocationRequest.PRIORITY_HIGH_ACCURACY
val medium_priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
val low_priority = LocationRequest.PRIORITY_LOW_POWER

// Location variables
private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
private lateinit var locationCallback: LocationCallback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a location provider client
        mFusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        // Start the location updates
        startLocationUpdates(low_frequency, low_priority)
    }
}

// Starts the location updates
fun startLocationUpdates(frequency: Long, priority: Int) {
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            return
        }
    }
    // Update location at the given frequency and priority
    val request = LocationRequest()
    request.interval = frequency
    request.fastestInterval = frequency / 2
    request.priority = priority
    try {
        mFusedLocationProviderClient.requestLocationUpdates(
            request,
            locationCallback,
            null
        )
    } catch (e: SecurityException) {
        Log.e("Exception", e.toString())
    }
}