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
package cl.ucn.disc.dsm.chilealerta.services;

import cl.ucn.disc.dsm.chilealerta.model.AlertaSismo;
import cl.ucn.disc.dsm.chilealerta.services.alertapi.AlertaSismoApiService.AlertaApiException;
import cl.ucn.disc.dsm.chilealerta.services.alertapi.UltimosSismosChile;
import java.net.URI;
import java.net.URISyntaxException;
import kotlin.text.UStringsKt;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transformer {

  private static final Logger log = LoggerFactory.getLogger(Transformer.class);

  /**
   * UltimosSismosChile a AlertaSismo
   * @param ultimosSismosChile
   * @return
   */
  public static AlertaSismo transform(final UltimosSismosChile ultimosSismosChile){

    if(ultimosSismosChile == null){
      throw new AlertaApiException("ultimo sismo es null");
    }

    final String host = getHost(ultimosSismosChile.url);


    return new AlertaSismo(
        ultimosSismosChile.chileanTime,
        ultimosSismosChile.reference,
        ultimosSismosChile.magnitude,
        ultimosSismosChile.scale,
        ultimosSismosChile.latitude,
        ultimosSismosChile.longitude,
        ultimosSismosChile.depth,
        ultimosSismosChile.id,
        ultimosSismosChile.url,
        ultimosSismosChile.source
        );
  }

  private static String getHost(final String url){

    try{
      final URI uri = new URI(url);
      final String hostname = uri.getHost();

      if(hostname!=null){
        return hostname.startsWith("www.") ? hostname.substring(4) : hostname;
      }

      return null;
    }catch (final URISyntaxException|NullPointerException ex){
      return null;
    }
  }

  public static <T> String toString(final T t){
    return ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE);
  }

}
