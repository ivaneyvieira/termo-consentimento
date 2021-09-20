package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.framework.viewmodel.IView
import br.com.astrosoft.framework.viewmodel.ViewModel
import br.com.astrosoft.termos.model.beans.Cliente

class TermoViewModel(view: ITermoView) : ViewModel<ITermoView>(view) {
  val tabClieteViewModel = TabClienteViewModel(this)
  val tabClieteAceitoViewModel = TabClienteAceitoViewModel(this)
  override fun listTab(): List<ITabView> = listOf(view.tabClienteViewModel, view.tabClienteAceitoViewModel)
}

interface ITermoView : IView {
  val tabClienteViewModel: ITabClienteViewModel
  val tabClienteAceitoViewModel: ITabClienteAceitoViewModel
}

