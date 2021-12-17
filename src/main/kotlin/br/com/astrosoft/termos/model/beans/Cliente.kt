package br.com.astrosoft.termos.model.beans

import br.com.astrosoft.termos.model.saci
import java.time.LocalDate

class Cliente(val codigo: Int,
              val nome: String,
              val cpf: String,
              val email: String,
              var flagEntregaTroca: Boolean,
              var flagUsoAsistencia: Boolean,
              var flagHorarioDias: Boolean,
              var flagPromocoesOferta: Boolean,
              var flagPesquisaSatisfacao: Boolean,
              var flagCadastro: Boolean,
              val dataAceite: LocalDate?) {
  fun flagAceito() = flagCadastro

  fun token(): String {
    return "https://www.engecopi.com.br/"
  }

  fun save() {
    saci.saveCliente(this)
  }

  fun desfaz() {
    saci.desfazCliente(this)
  }

  companion object {
    fun findClientes(filtro: String, flagAceito: Boolean? = null) = saci.findClientes(filtro, flagAceito)
  }
}