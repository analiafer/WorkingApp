package com.example.workingapp.ui.viewModel

import android.Manifest
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.widget.Button
import java.io.IOException

class ScannedBarcodeActivity<BarcodeDetector, Barcode> : AppCompatActivity() {
    var surfaceView: SurfaceView? = null
    var txtBarcodeValue: TextView? = null
    private var barcodeDetector: BarcodeDetector? = null
    private var cameraSource: CameraSource? = null
    var btnAction: Button? = null
    var intentData = ""
    var isEmail = false
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanned_barcode)
        initViews()
    }

    private fun initViews() {
        txtBarcodeValue = findViewById<TextView>(R.id.txtBarcodeValue)
        surfaceView = findViewById<SurfaceView>(R.id.surfaceView)
        btnAction = findViewById<Button>(R.id.btnAction)
        btnAction!!.setOnClickListener {
            if (intentData.length > 0) {
                if (isEmail) startActivity(
                    Intent(
                        this@ScannedBarcodeActivity,
                        ViewTicketActivity::class.java
                    ).putExtra("List_Tasks", intentData)
                ) else {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(intentData)))
                }
            }
        }
    }

    private fun initialiseDetectorsAndSources() {
        Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT)
            .show()
        barcodeDetector = Builder(this)
            .setBarcodeFormats(Barcode?.ALL_FORMATS)
            .build()
        cameraSource = CameraSource.Builder(this, barcodeDetector as Detector<*>?)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()
        surfaceView.getHolder().addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            this@ScannedBarcodeActivity,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource.start(surfaceView.getHolder())
                    } else {
                        ActivityCompat.requestPermissions(
                            this@ScannedBarcodeActivity, arrayOf(
                                Manifest.permission.CAMERA
                            ), REQUEST_CAMERA_PERMISSION
                        )
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            //comentario
            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })
        (barcodeDetector as Detector<*>?).setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                Toast.makeText(
                    getApplicationContext(),
                    "To prevent memory leaks barcode scanner has been stopped",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun receiveDetections(detections: Detections<Barcode>) {
                val barcodes: SparseArray<Barcode> = detections.getDetectedItems()
                if (barcodes.size() != 0) {
                    txtBarcodeValue.post(object : Runnable {
                        override fun run() {
                            run {
                                isEmail = false
                                btnAction!!.text = "LAUNCH URL"
                                intentData = barcodes.valueAt(0).displayValue
                                txtBarcodeValue.setText(intentData)
                            }
                        }
                    })
                }
            }
        })
    }

    protected override fun onPause() {
        super.onPause()
        cameraSource.release()
    }

    protected override fun onResume() {
        super.onResume()
        initialiseDetectorsAndSources()
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 201
    }
}