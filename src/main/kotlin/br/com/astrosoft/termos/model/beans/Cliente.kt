package br.com.astrosoft.termos.model.beans

import br.com.astrosoft.termos.model.saci

class Cliente(
  val codigo: Int,
  val nome: String,
  val cpf: String,
  val email: String,
             ) {

  companion object{
   fun findClientes(filtro: String) = saci.findClientes(filtro)
  }
}