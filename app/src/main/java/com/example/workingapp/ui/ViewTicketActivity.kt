package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityViewTicketBinding
import com.example.workingapp.ui.viewModel.ViewTicketViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ViewTicketActivity : AppCompatActivity() {
    private lateinit var bindingViewTicket: ActivityViewTicketBinding
    private val viewModel: ViewTicketViewModel by viewModel()
    private var idTicket: Long = 0
    private val ImageView;

    ImageView imageView;
    Button button;
    Button btnScan;
    EditText editText;
    String EditTextValue ;
    Thread thread ;
    public final static int QRcodeWidth = 350 ;
    Bitmap bitmap ;

    TextView tv_qr_readTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        btnScan = (Button)findViewById(R.id.btnScan);
        tv_qr_readTxt = (TextView) findViewById(R.id.tv_qr_readTxt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(!editText.getText().toString().isEmpty()){
                    EditTextValue = editText.getText().toString();

                    try {
                        bitmap = TextToImageEncode(EditTextValue);

                        imageView.setImageBitmap(bitmap);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    editText.requestFocus();
                    Toast.makeText(MainActivity.this, "Please Enter Your Scanned Test" , Toast.LENGTH_LONG).show();
                }

            }
        });


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
    }


    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                Value,
                BarcodeFormat.DATA_MATRIX.QR_CODE,
                QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

            pixels[offset + x] = bitMatrix.get(x, y) ?
            getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
        }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 350, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");

            } else {
                Log.e("Scan", "Scanned");

                tv_qr_readTxt.setText(result.getContents());
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    internal lateinit var sharedpref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedpref = SharedPref(this)
        if (sharedpref.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme_WorkingApp)
        } else {
            setTheme(R.style.Theme_WorkingApp)
        }

        super.onCreate(savedInstanceState)
        bindingViewTicket = ActivityViewTicketBinding.inflate(layoutInflater)
        setContentView(bindingViewTicket.root)
        //Guardamos en una variable los datos del ID del Ticket pasados desde la otra activity.
        val idDetalle = intent.getLongExtra("ID", 0)
        //Ese mismo número lo seteamos a otra vareable, que la pasamos por el metodo para que el viewModel obtenga el ticket via la función.
        idTicket = idDetalle
        viewModel.getById(idTicket)
        setObserver()
        setListener()
    }

    //función que setea los datos en la base de datos con los elementos del activity.
    private fun setObserver() {
        viewModel.ticket.observe(this, Observer {
            bindingViewTicket.textTituloTicket.text = it.titulo
            bindingViewTicket.textNombreAutor.text = it.autor
            bindingViewTicket.textDescripcionTicket.text = it.descripcion
            bindingViewTicket.textDateTicket.text = it.fechahora
        })
    }

    //función que se encarga del comportamiento de los botones de la barra.
//Uno borra el ticket, el otro vuelve atrás.
    private fun setListener() {
        bindingViewTicket.tbTicketView.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.optionDeleteTicket) {
                viewModel.ticket.removeObservers(this)
                viewModel.delete(viewModel.ticket.value!!)
                finish()
            } else finish()
            super.onOptionsItemSelected(item)
        }

        bindingViewTicket.btnEditTicket.setOnClickListener() {
            val intentEdit = Intent(this, EditActivity::class.java)
            intentEdit.putExtra("IdTicketEdit", idTicket)
            startActivity(intentEdit)
            finish()
        }
    }
}
