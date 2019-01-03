package com.fratt.museum_app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QR_manager extends AppCompatActivity {


    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private TextView textView;
    private BarcodeDetector barcodeDetector;
    private LinearLayout container;
    private Context context;
    private final int RequestCam = 1001;
    private Activity activity;




    public QR_manager (LinearLayout container , Context context, Activity activity)
    {
        this.container = container;
        this.context=context;
        this.activity= activity;


    }


    public void displayCamera()
    {
        LayoutInflater layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view;
        container.removeAllViews();
        view = layoutInflater.inflate(R.layout.qr_scanner_layout,container,false);
        container.addView(view);

        surfaceView = view.findViewById(R.id.surfcaceview);
        textView = view.findViewById(R.id.textViewqr);
        barcodeDetector = new BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(context,barcodeDetector).setRequestedPreviewSize(640,480).build();



        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if(ActivityCompat.checkSelfPermission(context,Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED)
                {
                      ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA},RequestCam);
                    return;
                }
                else {

                    System.out.println("qui entra");
                    try {
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {
                        System.out.println("e>" + e.toString());
                    }
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if(qrCodes.size() != 0 )
                {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator =  (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
                            vibrator.vibrate(100);
                            textView.setText(qrCodes.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });
    }
}
