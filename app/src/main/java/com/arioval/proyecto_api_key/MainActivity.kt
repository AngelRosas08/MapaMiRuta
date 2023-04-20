package com.arioval.proyecto_api_key

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), OnMapReadyCallback, OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private lateinit var map:GoogleMap
    private lateinit var btnSatelite:Button
    private lateinit var btnTerrain:Button
    private lateinit var btnNormal:Button


    private var start:String = ""
    private var end:String = ""

    companion object{
        const val REQUEST_CODE_LOCATION =0
    }

    override fun onMapReady(googleMap: GoogleMap) {  //metodo ofuncion en la cual vamos a mandar a llamar al mapa y demas metodos los cuales tendran funcinoes distintas
        map = googleMap
        createMarker()
        createPolylines()
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
        enableMyLocation()
        animarCasa()
    }

    private fun createPolylines(){      //creacion del polyline en un sitio especifico, mandando a llamar a un metodo para hacer cambios de color
        val polylineOptions:PolylineOptions = PolylineOptions()
            .add(LatLng(19.85271779521706, -99.60933526438617))
            .add(LatLng(19.85270142904955,-99.60968944780937))
            .add(LatLng(19.852652488172225,-99.6100402200352))
            .add(LatLng(19.852571443942313,-99.61038420272632))
            .add(LatLng(19.85245907690849,-99.6107180829487))
            .add(LatLng(19.8523164692919,-99.61103864508445))
            .add(LatLng(19.852144994561254,-99.61134280180637))
            .add(LatLng(19.851946304202556,-99.61162762381598))
            .add(LatLng(19.851722311810896,-99.6118903680583))
            .add(LatLng(19.85147517465769,-99.61212850414121))
            .add(LatLng(19.85120727291105,-99.6123397387055))
            .add(LatLng(19.85092118670956,-99.61252203751029))
            .add(LatLng(19.850619671310316,-99.61267364502142))
            .add(LatLng(19.85030563055084,-99.6127931013143))
            .add(LatLng(19.849982088880477,-99.61287925612837))
            .add(LatLng(19.84965216223079,-99.61293127993834))
            .add(LatLng(19.849319028005574,-99.61294867193551))
            .add(LatLng(19.84898589447958,-99.61293126484281))
            .add(LatLng(19.848655969900694,-99.61287922651746))
            .add(LatLng(19.848332431593125,-99.6127930583259))
            .add(LatLng(19.84801839535921,-99.6126735903076))
            .add(LatLng(19.84771688547438,-99.61252197317363))
            .add(LatLng(19.847430805564237,-99.61233966721845))
            .add(LatLng(19.84716291064411,-99.61212842825095))
            .add(LatLng(19.846915780590237,-99.61189029068129))
            .add(LatLng(19.84669179529791,-99.61162754792575))
            .add(LatLng(19.846493111765724,-99.61134273031931))
            .add(LatLng(19.846321643326426,-99.61103858074779))
            .add(LatLng(19.846179041224243,-99.61071802823488))
            .add(LatLng(19.84606667871598,-99.61038415973795))
            .add(LatLng(19.84598563784887,-99.61004019042431))
            .add(LatLng(19.845936699042344,-99.60968943271386))
            .add(LatLng(19.845920333574057,-99.60933526438617))
            .add(LatLng(19.845936699042344,-99.60898109605847))
            .add(LatLng(19.84598563784887,-99.60863033834802))
            .add(LatLng(19.84606667871598,-99.6082863690344))
            .add(LatLng(19.846179041224243,-99.60795250053746))
            .add(LatLng(19.846321643326426,-99.60763194802455))
            .add(LatLng(19.846493111765724,-99.60732779845303))
            .add(LatLng(19.84669179529791,-99.60704298084659))
            .add(LatLng(19.846915780590237,-99.60678023809106))
            .add(LatLng(19.84716291064411,-99.60654210052138))
            .add(LatLng(19.847430805564237,-99.60633086155389))
            .add(LatLng(19.84771688547438,-99.60614855559871))
            .add(LatLng(19.84801839535921,-99.60599693846474))
            .add(LatLng(19.848332431593125,-99.60587747044643))
            .add(LatLng(19.848655969900694,-99.60579130225489))
            .add(LatLng(19.84898589447958,-99.60573926392952))
            .add(LatLng(19.849319028005574,-99.60572185683682))
            .add(LatLng(19.84965216223079,-99.60573924883401))
            .add(LatLng(19.849982088880477,-99.60579127264398))
            .add(LatLng(19.85030563055084,-99.60587742745804))
            .add(LatLng(19.850619671310316,-99.60599688375092))
            .add(LatLng(19.85092118670956,-99.60614849126206))
            .add(LatLng(19.85120727291105,-99.60633079006685))
            .add(LatLng(19.85147517465769,-99.60654202463112))
            .add(LatLng(19.851722311810896,-99.60678016071404))
            .add(LatLng(19.851946304202556,-99.60704290495634))
            .add(LatLng(19.852144994561254,-99.60732772696596))
            .add(LatLng(19.8523164692919,-99.60763188368789))
            .add(LatLng(19.85245907690849,-99.60795244582364))
            .add(LatLng(19.852571443942313,-99.60828632604601))
            .add(LatLng(19.852652488172225,-99.60863030873712))
            .add(LatLng(19.85270142904955,-99.60898108096296))
            .add(LatLng(19.85271779521706,-99.60933526438617))
            .width(30f)
            .color(ContextCompat.getColor(this, R.color.kotlin))

        val polyline: Polyline = map.addPolyline((polylineOptions))

        val pattern = listOf<PatternItem>(
            Dot(), Gap(10f), Dash(50f)
        )
        polyline.pattern = pattern
        polyline.isClickable = true
        map.setOnPolylineClickListener { polyline -> changeColor(polyline) }

    }

    fun changeColor(polyline: Polyline){    //metodo para cambiar aleatoriamente el color de nuestro polyline establecida
        val color:Int = (0..5).random()
        when(color){
            0 -> polyline.color = ContextCompat.getColor(this, R.color.blue)
            1 -> polyline.color = ContextCompat.getColor(this, R.color.red)
            2 -> polyline.color = ContextCompat.getColor(this, R.color.green)
            3 -> polyline.color = ContextCompat.getColor(this, R.color.yellow)
            4 -> polyline.color = ContextCompat.getColor(this, R.color.purple)
            5 -> polyline.color = ContextCompat.getColor(this, R.color.pink)
        }
    }

    private fun createMarker() {    //creamos el marker en donde nos dirigira principalmente, en donde enmarcaremos con un texto de referencia
        val coordinates = LatLng(19.849487, -99.609308)
        val marker = MarkerOptions().position(coordinates).title("mi casa..")
        map.addMarker((marker))
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f),
            4000,
            null
        )
        map.uiSettings.isZoomControlsEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {    //dentro de este metodo vamos a tener los eventos de nuestros botones
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSatelite = findViewById(R.id.btnSatelite)
        btnSatelite.setOnClickListener {
            Satelite()
        }
        btnTerrain = findViewById(R.id.btnTerrain)
        btnTerrain.setOnClickListener{
            Terrain()
        }
        btnNormal = findViewById(R.id.btnNormal)
        btnNormal.setOnClickListener {
            Normal()
        }
        createFragment()
    }

    private fun createFragment() {      //dentro de este metodo vamos a llamar el fragment para poner el mapa
        val mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {   //metodo para hacer una advertencia de falta de permisos en ajustes
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this, "Permitir acceso dentro de ajustes", Toast.LENGTH_LONG).show()
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(    //de igual forma se usa este metodo para los permisos de la aplicacion
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                map.isMyLocationEnabled = true
            }else{
                Toast.makeText(this, "para activar la localizacion ve a ajustes y acepta los permisos", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if (!isLocationPermissionGranted()){
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = false
            Toast.makeText(this, "para activar la localizacion ve a ajustes y acepta los permisos", Toast.LENGTH_LONG).show()

        }
    }

    override fun onMyLocationButtonClick(): Boolean {   //imprime un toast al momento de presionar el boton que te redirige a tu ubicacion actual
        Toast.makeText(this, "Ver mi ubicacion actual", Toast.LENGTH_LONG).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {  //al presionar tu ubicacion actual te muestra la latitud y longitud
        Toast.makeText(this, "Estas en${p0.latitude}, ${p0.longitude}", Toast.LENGTH_LONG).show()

    }

    private fun Terrain(){  //para el cambi de la vista del mapa a modo terreno
        map.mapType = MAP_TYPE_TERRAIN
    }

    private fun Satelite(){ //para el cambio de la vista del mapa a modo satelite
        map.mapType = MAP_TYPE_SATELLITE
    }

    private fun Normal(){   //para el cambio de la vista del mapa a modo predeterminado
        map.mapType = MAP_TYPE_NORMAL
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun animarCasa() {  //metodo para dar animacion a la primera vista de la ruta a donde queremos que nos dirija
        val casa = LatLng(19.849383265624624,-99.60926253342616)
        val camPos = CameraPosition.Builder()
            .target(casa) //Centramos el mapa en el sitio
            .zoom(19f) //Establecemos el zoom en 19
            .bearing(45f) //Establecemos la orientación con el noreste arriba
            .tilt(70f) //Bajamos el punto de vista de la cámara 70 grados
            .build()
        val camUpd3 = CameraUpdateFactory.newCameraPosition(camPos)
        map.animateCamera(camUpd3)
    }
}