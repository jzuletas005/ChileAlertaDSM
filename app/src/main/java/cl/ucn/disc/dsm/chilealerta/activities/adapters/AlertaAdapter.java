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
package cl.ucn.disc.dsm.chilealerta.activities.adapters;

import cl.ucn.disc.dsm.chilealerta.model.AlertaSismo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertaAdapter {

  /**
   * Log
   */
  private static final Logger log = LoggerFactory.getLogger(AlertaAdapter.class);
  /**
   * List of Alertas
   */
  private List<AlertaSismo> AlertasChile;

  /**
   * Constructor
   */
  public AlertaAdapter(){
    //Lista vacia
    this.AlertasChile = new ArrayList<>();

  }

  public void setAlertaSismos(final List<AlertaSismo> alertas) {

    //Actualiza alertas
    this.AlertasChile = alertas;

  }

}
