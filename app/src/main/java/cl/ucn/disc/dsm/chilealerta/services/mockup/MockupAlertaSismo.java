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
package cl.ucn.disc.dsm.chilealerta.services.mockup;

import cl.ucn.disc.dsm.chilealerta.model.AlertaSismo;
import cl.ucn.disc.dsm.chilealerta.services.AlertaService;
import java.util.ArrayList;
import java.util.List;

public class MockupAlertaSismo implements AlertaService {

  /**
   * Constructor
   */
  public  MockupAlertaSismo(){
  }
  @Override
  public List<AlertaSismo> getAlertas(int pageSize) {
    final List<AlertaSismo> alerta = new ArrayList<>();

    alerta.add(new AlertaSismo(
      "2018/07/18 20:48:02",
        "251 km al SE de Antofagasta - Chile",
        3.8,
        "Mb",
        -24.258,
        -68.021,
        132.15,
        "56620244",
        "http://sismologia.net/?user=jzuletas&p=detalles&id=56620244",
        "INSIMU")

    );

    return alerta;
  }

  @Override
  public List<AlertaSismo> getTopHeadLines(int pageSize) {
    return null;
  }
}
