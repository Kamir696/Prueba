import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://digimon-api.vercel.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DigimonAPI digimonAPI = retrofit.create(DigimonAPI.class);

        Call<List<Digimouns>> call = digimonAPI.getDigimon();

        call.enqueue(new Callback<List<Digimouns>>() {
            @Override
            public void onResponse(Call<List<Digimouns>> call, Response<List<Digimouns>> response) {
                if (!response.isSuccessful()) {
                    // handle the error
                    return;
                }

                List<Digimouns> digimons = response.body();
                // handle the list of digimons
            }

            @Override
            public void onFailure(Call<List<Digimouns>> call, Throwable t) {
            }
        });
    }
}