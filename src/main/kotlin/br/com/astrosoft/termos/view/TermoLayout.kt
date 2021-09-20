package br.com.astrosoft.termos.view

import br.com.astrosoft.framework.view.MainLayout
import br.com.astrosoft.termos.view.termos.TermoView
import com.vaadin.flow.component.dependency.JsModule
import com.vaadin.flow.component.icon.VaadinIcon.*
import com.vaadin.flow.component.page.Push
import com.vaadin.flow.component.tabs.Tabs
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo

@Theme(value = Lumo::class, variant = Lumo.DARK)
@Push
@JsModule("./styles/shared-styles.js")
class TermoLayout : MainLayout() {
  var  result : Result<Int>? = null
  override fun Tabs.menuConfig() {
    menuRoute(FORM, "Termos", TermoView::class)
  }
}