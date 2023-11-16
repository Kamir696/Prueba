import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DigimounsAPI api;
    private RecyclerView recyclerView;
    private DigimounsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://digimon-api.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(DigimonAPI.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Digimouns>> call = api.getDigimouns();

        call.enqueue(new Callback<List<Digimouns>>() {
            @Override
            public void onResponse(Call<List<Digimouns>> call, Response<List<Digimouns>> response) {
                if (response.isSuccessful()) {
                    List<Digimouns> digimounsList = response.body();


                    adapter = new DigimounsAdapter(digimounsList);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Digimouns>> call, Throwable t) {
            }
        });
    }
}
