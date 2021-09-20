package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.termos.model.beans.Cliente

class TabClienteViewModel(val viewModel: TermoViewModel) {
  private val subView
    get() = viewModel.view.tabClienteViewModel

  fun updateGrid() {
    val filtro = subView.filtro()
    val lista = Cliente.findClientes(filtro, false)
    subView.updateClientes(lista)
  }

  fun naoAceito(cliente: Cliente) {
    cliente.flagHorarioDias = false
    cliente.flagEntregaTroca = false
    cliente.flagPesquisaSatisfacao = false
    cliente.flagPromocoesOferta = false
    cliente.flagUsoAsistencia = false
    cliente.flagCadastro = false
    cliente.save()
    updateGrid()
  }

  fun aceito(cliente: Cliente) {
    cliente.flagCadastro = true
    cliente.save()
    updateGrid()
  }
}

interface ITabClienteViewModel : ITabView {
  fun filtro(): String
  fun updateClientes(listaCliente: List<Cliente>)
}
