import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface DigimonAPI {
    @GET("digimon")
    Call<List<Digimouns>> getDigimon();
}