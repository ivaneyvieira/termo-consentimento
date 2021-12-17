package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.termos.model.beans.Cliente

class TabClienteTermoViewModel(val viewModel: TermoViewModel) {
  private val subView
    get() = viewModel.view.tabClienteTermoViewModel

  fun updateGrid() {
    val filtro = subView.filtro()
    val lista = Cliente.findClientes(filtro, false)
    subView.updateClientes(lista)
  }

  fun aceito(cliente: Cliente) {
    cliente.flagCadastro = true
    cliente.save()
    updateGrid()
  }
}

interface ITabClienteTermoViewModel : ITabView {
  fun filtro(): String
  fun updateClientes(listaCliente: List<Cliente>)
}
