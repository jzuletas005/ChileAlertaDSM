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

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.chilealerta.databinding.RowAlertasismoBinding;
import cl.ucn.disc.dsm.chilealerta.model.AlertaSismo;


public final class AlertaViewHolder extends RecyclerView.ViewHolder {

  private final RowAlertasismoBinding binding;


  /**
   * Constructor
   * @param rowAlertasismoBinding
   */
  public AlertaViewHolder(RowAlertasismoBinding rowAlertasismoBinding) {
    super(rowAlertasismoBinding.getRoot());
    this.binding = rowAlertasismoBinding;
  }

  public void bind(final AlertaSismo alerta){

    this.binding.tvFecha.setText(alerta.getFecha());
    this.binding.tvReferencia.setText(alerta.getReferencia());
    this.binding.tvEscala.setText(alerta.getEscala());
    this.binding.tvLatitud.setText(alerta.getLatitud().toString());
    this.binding.tvMagnitud.setText(alerta.getMagnitud().toString());
    this.binding.tvLongitud.setText(alerta.getLonguitud().toString());
    this.binding.tvProfundidad.setText(alerta.getProfundidad().toString());
  }
}
