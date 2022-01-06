package br.com.astrosoft.termos.view

import br.com.astrosoft.framework.view.MainLayout
import br.com.astrosoft.termos.view.termos.TermoView
import com.vaadin.flow.component.icon.VaadinIcon.*
import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.component.page.BodySize
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.component.tabs.Tabs
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo

class TermoLayout : MainLayout() {
  var  result : Result<Int>? = null
  override fun Tabs.menuConfig() {
    menuRoute(FORM, "Termos", TermoView::class)
  }
}

@BodySize(width = "100vw", height = "100vh")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes")
@Theme(themeClass = Lumo::class, variant = Lumo.DARK)
class app : AppShellConfigurator