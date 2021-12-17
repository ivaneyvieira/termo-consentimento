package br.com.astrosoft.termos.view.termos

import br.com.astrosoft.framework.model.IUser
import br.com.astrosoft.framework.view.TabPanelGrid
import br.com.astrosoft.termos.model.beans.Cliente
import br.com.astrosoft.termos.model.beans.UserSaci
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteCodigo
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteCpf
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteEmail
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteNome
import br.com.astrosoft.termos.viewmodel.termos.ITabClienteBaseViewModel
import br.com.astrosoft.termos.viewmodel.termos.TabClienteBaseViewModel
import com.github.mvysny.karibudsl.v10.textField
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.Grid.SelectionMode.SINGLE
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode

@CssImport("./styles/gridTotal.css")
class TabClientesBase(val viewModel: TabClienteBaseViewModel) : TabPanelGrid<Cliente>(Cliente::class),
        ITabClienteBaseViewModel {
  private lateinit var edtFiltro: TextField
  override fun HorizontalLayout.toolBarConfig() {
    edtFiltro = textField("Filtro") {
      this.width = "600px"
      this.valueChangeMode = ValueChangeMode.TIMEOUT

      addValueChangeListener {
        updateComponent()
      }
    }
  }

  override fun Grid<Cliente>.gridPanel() {
    setSelectionMode(SINGLE)

    clienteCodigo().markAceito()
    clienteNome().markAceito()
    clienteCpf().markAceito()
    clienteEmail().markAceito()
  }

  private fun Grid.Column<Cliente>.markAceito(): Grid.Column<Cliente> {
    this.setClassNameGenerator {
      if (it.flagAceito()) "marcaDiferenca" else null
    }
    return this
  }

  override fun filtro(): String {
    return edtFiltro.value ?: ""
  }

  override fun updateClientes(listaCliente: List<Cliente>) {
    updateGrid(listaCliente)
  }

  override fun isAuthorized(user: IUser): Boolean {
    val username = user as? UserSaci
    return username?.clienteBase == true

  }

  override val label: String
    get() = "Base"

  override fun updateComponent() {
    viewModel.updateGrid()
  }
}