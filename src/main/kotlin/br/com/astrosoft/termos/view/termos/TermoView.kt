package br.com.astrosoft.termos.view.termos

import br.com.astrosoft.framework.model.IUser
import br.com.astrosoft.framework.view.ViewLayout
import br.com.astrosoft.termos.model.beans.UserSaci
import br.com.astrosoft.termos.view.TermoLayout
import br.com.astrosoft.termos.viewmodel.termos.ITermoView
import br.com.astrosoft.termos.viewmodel.termos.TermoViewModel
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route(layout = TermoLayout::class, value = "")
@PageTitle("Termo de Consentimento")
@CssImport("./styles/gridTotal.css", themeFor = "vaadin-grid")
class TermoView : ViewLayout<TermoViewModel>(), ITermoView {
  override val viewModel = TermoViewModel(this)
  override fun isAccept(user: IUser): Boolean {
    val userSaci = user as? UserSaci ?: return false
    return userSaci.termos
  }

  override val tabClienteViewModel = TabClientes(viewModel.tabClieteViewModel)
  override val tabClienteAceitoViewModel = TabClientesAceito(viewModel.tabClieteAceitoViewModel)

  init {
    addTabSheat(viewModel)
  }
}