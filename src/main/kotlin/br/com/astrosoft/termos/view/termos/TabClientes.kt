package br.com.astrosoft.termos.view.termos

import br.com.astrosoft.framework.model.IUser
import br.com.astrosoft.framework.view.TabPanelGrid
import br.com.astrosoft.termos.model.beans.Cliente
import br.com.astrosoft.termos.viewmodel.termos.ITabClienteViewModel
import br.com.astrosoft.termos.viewmodel.termos.TabClienteViewModel
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout

class TabClientes(val viewModel: TabClienteViewModel) :
        TabPanelGrid<Cliente>(Cliente::class), ITabClienteViewModel {
  override fun HorizontalLayout.toolBarConfig() {
    TODO("Not yet implemented")
  }

  override fun Grid<Cliente>.gridPanel() {
    TODO("Not yet implemented")
  }

  override fun isAuthorized(user: IUser): Boolean {
    TODO("Not yet implemented")
  }

  override val label: String
    get() = TODO("Not yet implemented")

  override fun updateComponent() {
    TODO("Not yet implemented")
  }
}