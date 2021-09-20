package br.com.astrosoft.termos.view.termos

import br.com.astrosoft.framework.model.IUser
import br.com.astrosoft.framework.view.TabPanelGrid
import br.com.astrosoft.framework.view.addColumnButton
import br.com.astrosoft.termos.model.beans.Cliente
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteCodigo
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteCpf
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteEmail
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteNome
import br.com.astrosoft.termos.viewmodel.termos.ITabClienteAceitoViewModel
import br.com.astrosoft.termos.viewmodel.termos.ITabClienteViewModel
import br.com.astrosoft.termos.viewmodel.termos.TabClienteAceitoViewModel
import br.com.astrosoft.termos.viewmodel.termos.TabClienteViewModel
import com.github.mvysny.karibudsl.v10.textField
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.Grid.SelectionMode.SINGLE
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode

class TabClientesAceito(val viewModel: TabClienteAceitoViewModel) :
        TabPanelGrid<Cliente>(Cliente::class), ITabClienteAceitoViewModel {
  private lateinit var edtFiltro: TextField
  override fun HorizontalLayout.toolBarConfig() {
    edtFiltro = textField("Filtro"){
      this.width = "600px"
      this.valueChangeMode = ValueChangeMode.TIMEOUT

      addValueChangeListener {
        updateComponent()
      }
    }
  }

  override fun Grid<Cliente>.gridPanel() {
    setSelectionMode(SINGLE)
    addColumnButton(VaadinIcon.DIPLOMA, "Termo de consentimento", "Termo") { fornecedor ->
      DlgTermoConsetimentoAceito(viewModel).showDialogNota(fornecedor)
    }

    clienteCodigo()
    clienteNome()
    clienteCpf()
    clienteEmail()
  }

  override fun filtro(): String {
    return edtFiltro.value ?: ""
  }

  override fun updateClientes(listaCliente: List<Cliente>) {
    updateGrid(listaCliente)
  }

  override fun isAuthorized(user: IUser): Boolean {
    return true
  }

  override val label: String
    get() = "Termos aceitos"

  override fun updateComponent() {
    viewModel.updateGrid()
  }
}