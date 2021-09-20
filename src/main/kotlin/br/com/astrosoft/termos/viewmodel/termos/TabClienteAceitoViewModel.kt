package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.termos.model.beans.Cliente

class TabClienteAceitoViewModel(val viewModel: TermoViewModel) {
  private val subView
    get() = viewModel.view.tabClienteAceitoViewModel

  fun updateGrid() {
    val filtro = subView.filtro()
    val lista = Cliente.findClientes(filtro, true)
    subView.updateClientes(lista)
  }

  fun naoAceito(cliente: Cliente) {
    cliente.flagHorarioDias = false
    cliente.flagEntregaTroca = false
    cliente.flagPesquisaSatisfacao = false
    cliente.flagPromocoesOferta = false
    cliente.flagUsoAsistencia = false
    cliente.save()
  }

  fun aceito(cliente: Cliente) {
    cliente.save()
  }
}

interface ITabClienteAceitoViewModel : ITabView {
  fun filtro(): String
  fun updateClientes(listaCliente: List<Cliente>)
}
