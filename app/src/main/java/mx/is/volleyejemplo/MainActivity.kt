package mx.`is`.volleyejemplo

import GsonRequest
import WeatherByCity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Response
import mx.uv.tftstatistics.VolleySingleton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        BuscarClima();
    }


    fun BuscarClima() {
        val url =
            "http://api.openweathermap.org/data/2.5/weather?q=Veracruz&appid=156d7086924563e0b3958c649f7f0393"
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
            val txt_clima = findViewById<TextView>(R.id.txtClima)
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