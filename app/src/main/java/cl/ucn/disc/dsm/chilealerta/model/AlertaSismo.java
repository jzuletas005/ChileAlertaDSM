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
package cl.ucn.disc.dsm.chilealerta.model;


public class AlertaSismo {

  /**
   * La Fecha
   */
  public final String fecha;
  /**
   * La Referencia
   */
  public final String referencia;
  /**
   * La Magnitud
   */
  public final Double magnitud;
  /**
   * La Escala
   */
  public final String escala;
  /**
   * La Latitud
   */
  public final Double latitud;
  /**
   * La Longuitud
   */
  public final Double longitud;
  /**
   * La Profundidad
   */
  public final  Double profundidad;
  /**
   * La Identificacion (id)
   */
  public final String identificacion;
  /**
   * La URL
   */
  public final String url;
  /**
   * El Recurso (Source)
   */
  public final String recurso;


  /**
   * @param fecha
   * @param referencia
   * @param magnitud
   * @param escala
   * @param latitud
   * @param longitud
   * @param profundidad
   * @param identificacion
   * @param url
   * @param recurso
   */
  public AlertaSismo(String fecha, String referencia, Double magnitud,
      String escala, Double latitud, Double longitud, Double profundidad,
      String identificacion, String url, String recurso) {
    this.fecha = fecha;
    this.referencia = referencia;
    this.magnitud = magnitud;
    this.escala = escala;
    this.latitud = latitud;
    this.longitud = longitud;
    this.profundidad = profundidad;
    this.identificacion = identificacion;
    this.url = url;
    this.recurso = recurso;
  }

  /**
   * @return La fecha
   */
  public String getFecha() {return this.fecha;}

  /**
   * @return La referencia
   */
  public String getReferencia() {return this.referencia;}

  /**
   * @return La magnitud
   */
  public Double getMagnitud() {return this.magnitud;}
  /**
   * @return La escala
   */
  public String getEscala() {return this.escala;}
  /**
   * @return La latitud
   */
  public Double getLatitud() {return this.latitud;}
  /**
   * @return La longuitud
   */
  public Double getLonguitud() {return this.longitud;}
  /**
   * @return La profundidad
   */
  public Double getProfundidad() {return this.profundidad;}
  /**
   * @return La identificacion (id)
   */
  public String getIdentificacion() {return this.identificacion;}
  /**
   * @return La URL
   */
  public String getUrl() {return this.url;}
  /**
   * @return El recurso (source)
   */
  public String getRecurso() {return this.recurso;}
}
