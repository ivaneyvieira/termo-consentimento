package br.com.astrosoft.termos.view.termos

import br.com.astrosoft.framework.model.IUser
import br.com.astrosoft.framework.view.TabPanelGrid
import br.com.astrosoft.framework.view.addColumnButton
import br.com.astrosoft.termos.model.beans.Cliente
import br.com.astrosoft.termos.model.beans.UserSaci
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteCodigo
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteCpf
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteDataAceite
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteDataCancelamento
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteEmail
import br.com.astrosoft.termos.view.termos.columms.ClienteColumns.clienteNome
import br.com.astrosoft.termos.viewmodel.termos.ITabClienteAceitoViewModel
import br.com.astrosoft.termos.viewmodel.termos.ITabClienteCanceladoViewModel
import br.com.astrosoft.termos.viewmodel.termos.TabClienteAceitoViewModel
import br.com.astrosoft.termos.viewmodel.termos.TabClienteCanceladoViewModel
import com.github.mvysny.karibudsl.v10.textField
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.Grid.SelectionMode.SINGLE
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import org.claspina.confirmdialog.ButtonOption
import org.claspina.confirmdialog.ConfirmDialog

class TabClientesCancelado(val viewModel: TabClienteCanceladoViewModel) : TabPanelGrid<Cliente>(Cliente::class),
        ITabClienteCanceladoViewModel {
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
    addColumnButton(VaadinIcon.EXIT, "Desfazer o canceladomento", "Desfazer") { cliente ->
      viewModel.desfazerCancelamento(cliente)
    }

    clienteDataCancelamento()
    clienteCodigo()
    clienteNome()
    clienteCpf()
    clienteEmail()
  }

  override fun query(): String {
    return edtFiltro.value ?: ""
  }

  override fun updateClientes(listaCliente: List<Cliente>) {
    updateGrid(listaCliente)
  }

  override fun showQuestao(msg: String, exec: () -> Unit) {
    ConfirmDialog.createQuestion()
      .withCaption("Confirma????o")
      .withMessage(msg)
      .withYesButton({
                       exec()
                     },
                     ButtonOption.caption("Sim"))
      .withNoButton({ }, ButtonOption.caption("N??o"))
      .open()
  }

  override fun isAuthorized(user: IUser): Boolean {
    val username = user as? UserSaci
    return username?.clienteCancelado == true
  }

  override val label: String
    get() = "Termos Cancelados"

  override fun updateComponent() {
    viewModel.updateGrid()
  }
}