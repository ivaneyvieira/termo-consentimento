package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.framework.viewmodel.IView
import br.com.astrosoft.framework.viewmodel.ViewModel

class TermoViewModel(view: ITermoView) : ViewModel<ITermoView>(view) {
  val tabClieteViewModel = TabClienteViewModel(this)
  override fun listTab(): List<ITabView> = listOf(view.tabClienteViewModel)
}

interface ITermoView : IView {
  val tabClienteViewModel: ITabClienteViewModel
}

