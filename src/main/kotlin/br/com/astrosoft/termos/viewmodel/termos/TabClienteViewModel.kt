package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView

class TabClienteViewModel(val viewModel: TermoViewModel) {
  val subView
    get() = viewModel.view.tabClienteViewModel
}

interface ITabClienteViewModel : ITabView
