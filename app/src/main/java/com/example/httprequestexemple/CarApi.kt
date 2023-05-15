import com.example.httprequestexemple.Car
import retrofit2.Call
import retrofit2.http.GET

interface CarApi {

    @GET("cars.json")
    fun getCars(): Call<List<Car>>
}