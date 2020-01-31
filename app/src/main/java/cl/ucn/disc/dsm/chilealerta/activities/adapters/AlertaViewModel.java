package cl.ucn.disc.dsm.chilealerta.activities.adapters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cl.ucn.disc.dsm.chilealerta.model.AlertaSismo;
import cl.ucn.disc.dsm.chilealerta.services.AlertaService;
import cl.ucn.disc.dsm.chilealerta.services.alertapi.AlertaSismoApiService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertaViewModel extends ViewModel {

  /**
   * Logger
   */
  private static final Logger log = LoggerFactory.getLogger(AlertaViewModel.class);

  private final MutableLiveData<List<AlertaSismo>> theAlertas = new MutableLiveData<>();

  private final MutableLiveData<Exception> theException = new MutableLiveData<>();

  private AlertaService alertaService = new AlertaSismoApiService();

  private AlertaSismo ultimosSismos;

  /**
   *
   * @return
   */
  public LiveData<List<AlertaSismo>> getAlertaSismos(){return this.theAlertas;}

  /**
   *
   * @return
   */
  public LiveData<Exception> getException(){return this.theException;}


  public int refresh() {

    try {

      // 1. Get the list of noticia from NewsApi
      final List<AlertaSismo> alertas = this.alertaService.getAlertas(ultimosSismos);

      // 2. Set the values (NEED to be in background)
      this.theAlertas.postValue(alertas);

      // 3. All ok!
      return alertas.size();

    } catch (final Exception ex) {

      log.error("Error", ex);

      // 2. Set the exception
      this.theException.postValue(ex);

      // 3. All error!
      return -1;

    }
  }
}
