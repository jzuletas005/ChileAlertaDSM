/*
    * Copyright [2020] [Javier Zuleta Silva]
    *
    * Licensed under the Apache License, Version 2.0 (the "License");
    * you may not use this file except in compliance with the License.
    * You may obtain a copy of the License at
    *
    *     http://www.apache.org/licenses/LICENSE-2.0
    *
    * Unless required by applicable law or agreed to in writing, software
    * distributed under the License is distributed on an "AS IS" BASIS,
    * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    * See the License for the specific language governing permissions and
    * limitations under the License.
    */
package cl.ucn.disc.dsm.chilealerta.activities;

import android.os.AsyncTask;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import cl.ucn.disc.dsm.chilealerta.activities.adapters.AlertaAdapter;
import cl.ucn.disc.dsm.chilealerta.activities.adapters.AlertaViewModel;
import cl.ucn.disc.dsm.chilealerta.databinding.ActivityMainBinding;
import cl.ucn.disc.dsm.chilealerta.services.AlertaService;
import es.dmoral.toasty.Toasty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends AppCompatActivity {

  /**
   * The Logger
   */
  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

  /**
   * The bindings.
   */
  private ActivityMainBinding binding;
  /**
   * Adapter
   */
  private AlertaAdapter alertaAdapter;
  /**
   * alerta Service
   */
  private AlertaService alertaService;
  /**
   * ViewModel
   */
  private AlertaViewModel alertaViewModel;

  /**
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_main);

    // Inflate the layout
    this.binding = ActivityMainBinding.inflate(getLayoutInflater());

    // Assign to the main view.
    setContentView(binding.getRoot());

    // Set the toolbar
    {
      //this.setSupportActionBar(binding.toolbar);
    }


    //Adapter RecyclerView
    {

      this.alertaAdapter = new AlertaAdapter();

      this.binding.rvAlertasismos.setAdapter(this.alertaAdapter);

      this.binding.rvAlertasismos.setLayoutManager(new LinearLayoutManager(this));

      this.binding.rvAlertasismos.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
    }

    //ViewModel
    {
      this.alertaViewModel = new ViewModelProvider(this).get(AlertaViewModel.class);

      this.alertaViewModel.getAlertaSismos().observe(this,alertaSismos -> this.alertaAdapter.setAlertaSismos(alertaSismos));

      this.alertaViewModel.getException().observe(this,this::showException);
    }

    //REfresh
    {
      this.binding.swlRefresh.setOnRefreshListener(() -> {
        log.debug("Refreshing ..");

        // Run in background
        AsyncTask.execute(() -> {

          // All ok
          final int size = this.alertaViewModel.refresh();
          if (size != -1) {

            // In the UI
            runOnUiThread(() -> {

              // Hide the loading
              this.binding.swlRefresh.setRefreshing(false);

              // Show a message.
              Toasty.success(this, "Alerta Sismos fetched: " + size, Toast.LENGTH_SHORT, true).show();

            });
          }
        });
      });
    }
  }

  private void showException(final Exception exception){

    // Hide the loading
    this.binding.swlRefresh.setRefreshing(false);

    // Build the message
    final StringBuilder sb = new StringBuilder("Error: ");
    sb.append(exception.getMessage());
    if (exception.getCause() != null) {
      sb.append(", ");
      sb.append(exception.getCause().getMessage());
    }

    Toasty.error(this, sb.toString(), Toast.LENGTH_LONG, true).show();

  }
}
