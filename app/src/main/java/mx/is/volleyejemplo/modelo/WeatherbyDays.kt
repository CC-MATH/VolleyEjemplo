data class WeatherbyDays (

    val cod : Int,
    val message : Int,
    val cnt : Int,
    val list : List<WeatherByCity>,
    val city : City
)