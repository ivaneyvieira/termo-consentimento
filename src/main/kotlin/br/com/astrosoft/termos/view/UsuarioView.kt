package br.com.astrosoft.termos.view

import br.com.astrosoft.framework.view.UserLayout
import br.com.astrosoft.framework.viewmodel.IUsuarioView
import br.com.astrosoft.termos.model.beans.UserSaci
import br.com.astrosoft.termos.viewmodel.UsuarioViewModel
import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.CrudOperation.*
import org.vaadin.crudui.crud.impl.GridCrud

@Route(layout = TermoLayout::class)
@PageTitle("Usuário")
class UsuarioView : UserLayout<UserSaci, UsuarioViewModel>(), IUsuarioView {
  override val viewModel = UsuarioViewModel(this)

  override fun columns(): List<String> {
    return listOf(UserSaci::no.name, UserSaci::login.name, UserSaci::name.name, UserSaci::impressora.name)
  }

  override fun createGrid() = GridCrud(UserSaci::class.java)

  override fun formCrud(operation: CrudOperation?,
                        domainObject: UserSaci?,
                        readOnly: Boolean,
                        binder: Binder<UserSaci>): Component {
    return FormLayout().apply {
      if (operation in listOf(READ, DELETE, UPDATE)) integerField("Número") {
        isReadOnly = readOnly
        binder.bind(this, UserSaci::no.name)
      }
      if (operation in listOf(ADD, READ, DELETE, UPDATE)) textField("Login") {
        isReadOnly = readOnly
        binder.bind(this, UserSaci::login.name)
      }
      if (operation in listOf(READ, DELETE, UPDATE)) textField("Nome") {
        isReadOnly = true
        binder.bind(this, UserSaci::name.name)
      }
      if (operation in listOf(ADD, READ, DELETE, UPDATE)) {
        comboBox<Int>("Número Loja") {
          isReadOnly = readOnly
          isAllowCustomValue = false
          val lojas = viewModel.allLojas()
          val values = lojas.map { it.no } + listOf(0)
          setItems(values.distinct().sorted())
          this.setItemLabelGenerator { storeno ->
            when (storeno) {
              0    -> "Todas as lojas"
              else -> lojas.firstOrNull { loja ->
                loja.no == storeno
              }?.descricao ?: ""
            }
          }
          formLayout {
            h4("Termo de Consetimento") {
              colspan = 2
            }
            checkBox("Base") {
              isReadOnly = readOnly
              binder.bind(this, UserSaci::clienteBase.name)
            }
            checkBox("Termo") {
              isReadOnly = readOnly
              binder.bind(this, UserSaci::clienteTermo.name)
            }
            checkBox("Aceito") {
              isReadOnly = readOnly
              binder.bind(this, UserSaci::clienteAceito.name)
            }
            checkBox("Cancelado") {
              isReadOnly = readOnly
              binder.bind(this, UserSaci::clienteCancelado.name)
            }
          }

          isAllowCustomValue = false
          binder.bind(this, UserSaci::storeno.name)
        }
      }
    }
  }
}

