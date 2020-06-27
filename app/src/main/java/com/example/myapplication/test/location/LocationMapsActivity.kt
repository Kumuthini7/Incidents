package com.example.myapplication.test.location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


/**
 * Created by Kumuthini.N on 28-06-2020
 */
class LocationMapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34

    private lateinit var mMap: GoogleMap
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    protected var mLastLocation: Location? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_maps)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        latitude = intent.extras?.getDouble("Latitude") ?: 0.0
        longitude = intent.extras?.getDouble("Longitude") ?: 0.0

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val coordinate = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(coordinate).title("Incident Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate))
    }

    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {

            showSnackbar(
                R.string.permission_rationale
            )

        } else {
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest()
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> {
                    // If user interaction was interrupted, the permission request is cancelled and you
                    // receive empty arrays.
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> // Permission granted.
                    getLastLocation()
                else -> // Permission denied.

                    // Notify the user via a SnackBar that they have rejected a core permission for the
                    // app, which makes the Activity useless. In a real app, core permissions would
                    // typically be best requested during a welcome-screen flow.

                    // Additionally, it is important to remember that a permission might have been
                    // rejected without asking the user for permission (device policy or "Never ask
                    // again" prompts). Therefore, a user interface affordance is typically implemented
                    // when permissions are denied. Otherwise, your app could appear unresponsive to
                    // touches or interactions which have required permissions.

                    showSnackbar(
                        R.string.permission_denied_explanation
                    )
            }
        }

    }

    private fun getLastLocation() {
        mFusedLocationClient!!.lastLocation
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && task.result != null) {
                    mLastLocation = task.result

                    mLastLocation?.latitude?.let {
                        mLastLocation?.longitude?.let { it1 ->
                            distanceBetweenLocation(
                                it,
                                it1
                            )
                        }
                    }
                } else {
                    showMessage(getString(R.string.no_location_detected))
                }
            }
    }

    private fun distanceBetweenLocation(latitude: Double, longitude: Double) {
        val l1 = Location("One")
        l1.latitude = latitude
        l1.longitude = longitude

        val l2 = Location("Two")
        l2.latitude = this.latitude
        l2.longitude = this.longitude

        val distanceBwOneAndTwo = l1.distanceTo(l2)
        Toast.makeText(this, "Distance from my location:$distanceBwOneAndTwo meters", Toast.LENGTH_LONG).show()

    }

    private fun showSnackbar(
        mainTextStringId: Int
    ) {
        Toast.makeText(this, getString(mainTextStringId), Toast.LENGTH_LONG).show()
    }

    private fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }


}
