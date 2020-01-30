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
package cl.ucn.disc.dsm.chilealerta.services.alertapi;

import cl.ucn.disc.dsm.chilealerta.model.AlertaSismo;
import cl.ucn.disc.dsm.chilealerta.services.Transformer;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlertaSismoApiService {

  /**
   * Logger
   */
  private static final Logger log = LoggerFactory.getLogger(AlertaSismoApiService.class);
  /**
   * AlertaAPI
   */
  private final AlertaApi alertaApi;


  /**
   * Constructor
   */
  public AlertaSismoApiService() {
    // Logging with slf4j
    final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(log::debug)
        .setLevel(Level.BODY);

    // Web Client
    final OkHttpClient httpClient = new Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .addNetworkInterceptor(loggingInterceptor)
        .build();

    // https://futurestud.io/tutorials/retrofit-getting-started-and-android-client
    this.alertaApi = new Retrofit.Builder()
        // The main URL
        .baseUrl(AlertaApi.BASE_URL)
        // JSON to POJO
        .addConverterFactory(GsonConverterFactory.create())
        // Validate the interface
        .validateEagerly(true)
        // Build the Retrofit ..
        .build()
        // .. get the AlertaAPI.
        .create(AlertaApi.class);
  }
  private static List<AlertaSismo> getAlertaSismoFromCall(final Call<AlertaApiResult> theCall){

    try{
      // Get the result from the call
      final Response<AlertaApiResult> response = theCall.execute();
      // Error!
      if(!response.isSuccessful()){

        throw new AlertaApiException(
            "Can't get the AlertaResult, code: " + response.code(),
            new HttpException(response)
        );
      }
      final AlertaApiResult theResult = response.body();
      //Nada
      if(theResult==null){
        throw new AlertaApiException("AlertaResul was null");
      }
      //No Sismos
      if(theResult.ultimosSismosChile ==null){
        throw new AlertaApiException("Ultimos Sismos Chile en AlertaResult wa null");
      }
      // UltimosSismos a AlertaSismos (transformer)
      return theResult.ultimosSismosChile.stream()
          .map(Transformer::tranform)//TODO Clase TRansformer
          .collect(Collectors.toList());


    }catch (final IOException ex){
      throw new AlertaApiException("Can't get the AlertaResult", ex);
    }
  }

  public static final class AlertaApiException extends RuntimeException{

    public AlertaApiException (final String message){super(message);}

    public AlertaApiException (final String message, final Throwable cause){super(message, cause);}
  }
}
