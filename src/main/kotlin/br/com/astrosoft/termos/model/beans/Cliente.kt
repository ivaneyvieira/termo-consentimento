package br.com.astrosoft.termos.model.beans

import br.com.astrosoft.termos.model.saci

class Cliente(
  val codigo: Int,
  val nome: String,
  val cpf: String,
  val email: String,
  var flagEntregaTroca: Boolean,
  var flagUsoAsistencia: Boolean,
  var flagHorarioDias: Boolean,
  var flagPromocoesOferta: Boolean,
  var flagPesquisaSatisfacao: Boolean,
             ) {
  fun flagAceito() =
          flagEntregaTroca || flagUsoAsistencia || flagHorarioDias || flagPromocoesOferta || flagPesquisaSatisfacao

  fun token(): String {
    return "https://www.engecopi.com.br/"
  }

  fun save() {
    saci.saveCliente(this)
  }

  companion object {
    fun findClientes(filtro: String, flagAceito: Boolean) =
            saci.findClientes(filtro).filter { it.flagAceito() == flagAceito }
  }
}