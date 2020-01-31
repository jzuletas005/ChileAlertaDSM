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

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import cl.ucn.disc.dsm.chilealerta.databinding.RowAlertasismoBinding;
import cl.ucn.disc.dsm.chilealerta.model.AlertaSismo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertaAdapter extends RecyclerView.Adapter<AlertaViewHolder> {

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

    // Each Noticia has unique id
    this.setHasStableIds(true);
  }

  @NonNull
  @Override
  public AlertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // The inflater
    final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

    final RowAlertasismoBinding rowAlertasismoBinding = RowAlertasismoBinding.inflate(layoutInflater,parent,false);

    final AlertaViewHolder alertaViewHolder = new AlertaViewHolder(rowAlertasismoBinding);


    // Click in the row
    rowAlertasismoBinding.getRoot().setOnClickListener(view ->{

      final int position = alertaViewHolder.getAdapterPosition();

      final long id = alertaViewHolder.getItemId();
      log.debug("Click! position: {}, id: {}.", position, Long.toHexString(id));

      final AlertaSismo alertaSismo = this.AlertasChile.get(position);

      log.debug("Link: {}.", alertaSismo.getUrl());
      if(alertaSismo.getUrl() != null){

        parent.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(alertaSismo.getUrl())));
      }
    });
    return alertaViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull AlertaViewHolder holder, int position) {
    holder.bind(this.AlertasChile.get(position));
  }

  @Override
  public int getItemCount() {
    return this.AlertasChile.size();
  }

  public void setAlertaSismos(final List<AlertaSismo> alertas) {

    //Actualiza alertas
    this.AlertasChile = alertas;

    // Notify to re-layout
    this.notifyDataSetChanged();

  }

}
