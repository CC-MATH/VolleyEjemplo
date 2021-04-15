package mx.`is`.volleyejemplo

import GsonRequest
import WeatherByCity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.volley.Response
import mx.uv.tftstatistics.VolleySingleton

lateinit var txtBuscar : TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtBuscar = findViewById(R.id.txt_search)

        val btnBuscar: Button = findViewById(R.id.btn_search)
        btnBuscar.setOnClickListener{
            BuscarClima()
        }

    }


    fun BuscarClima() {
        val url =
            "https://api.openweathermap.org/data/2.5/weather?q=" + txtBuscar.text +"&appid=c92933a9eecc1ffb5eef066d4182ee90"
        val mapHeaders: MutableMap<String, String> =
            mutableMapOf<String, String>() //agregar headers necesarios
        val myGsonRequest: GsonRequest<WeatherByCity> = GsonRequest<WeatherByCity>(
            url,
            WeatherByCity::class.java,
            mapHeaders,
            myRequestSuccessListener(), //Success Listener
            myRequestErrorListener() //Error Listener
        )
        VolleySingleton.getInstance(this).addToRequestQueue(myGsonRequest)
    }

    private fun myRequestSuccessListener(): Response.Listener<WeatherByCity> {
        return Response.Listener<WeatherByCity> {response ->
            val txt_clima = findViewById<TextView>(R.id.txt_clima)
            txt_clima.text = response.weather.get(0).description
        }
    }

    private fun myRequestErrorListener(): Response.ErrorListener {
        return Response.ErrorListener {error ->
            Log.e("ERROR", error.toString())
                //Código en caso de error
        }
    }
}