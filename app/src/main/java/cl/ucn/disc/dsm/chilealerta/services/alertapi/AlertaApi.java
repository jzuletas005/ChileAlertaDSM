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
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlertaApi {

  String BASE_URL = "https://chilealerta.com/api/query/?user=jzuletas&select=ultimos_sismos&country=chile";

  @GET(BASE_URL)//TODO REvisar retrofit
  Call<AlertaApiResult> getUltimosSismos(@Query("select") final AlertaSismo ultimossimos);
}
