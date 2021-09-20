package br.com.astrosoft.termos.view.termos

import br.com.astrosoft.framework.util.SystemUtils
import br.com.astrosoft.framework.view.SubWindowForm
import br.com.astrosoft.framework.view.style
import br.com.astrosoft.termos.model.beans.Cliente
import br.com.astrosoft.termos.viewmodel.termos.TabClienteViewModel
import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.checkbox.Checkbox
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.data.binder.Binder
import com.wontlost.zxing.ZXingVaadinWriter

@CssImport("./styles/gridTotal.css", themeFor = "vaadin-grid")
class DlgTermoConsetimento(val viewModel: TabClienteViewModel) {
  lateinit var form: SubWindowForm
  fun showDialogNota(cliente: Cliente) {
    form = SubWindowForm("") {
      val frame = createHtmlFrame(cliente)

      HorizontalLayout().apply {
        setSizeFull()
        addAndExpand(frame)
      }
    }
    form.open()
  }

  fun createHtmlFrame(cliente: Cliente): Component {
    return VerticalLayout().apply {
      val html = "/html/termo.html"
      val binder = Binder(Cliente::class.java)
      verticalLayout {
        isExpand = true
        this.html(SystemUtils.readFile(html))
      }
      horizontalLayout {
        setWidthFull()
        this.qrcode(cliente.token())
        this.dadosCliente(cliente)
      }
      verticalLayout {
        h2("OPÇÕES DE LIVRE ESCOLHA, PARA ASSINALAR:")
        p("""O CLIENTE aceita receber, por meios físicos ou digitais, através dos diferentes canais de 
          |comunicação da ENGECOPI:""".trimMargin()) {
          this.style("font-size", "20px")
        }
        checkBoxAceite("""Informações sobre entrega e/ou troca de produtos adquiridos pelo <b>CLIENTE</b>;"""){
          bind(binder).bind(Cliente::flagEntregaTroca)
        }
        checkBoxAceite("""Informações sobre o uso e/ou assistência técnica dos produtos adquiridos pelo 
          |<b>CLIENTE</b>;""".trimMargin()){
          bind(binder).bind(Cliente::flagUsoAsistencia)
        }
        checkBoxAceite("""Informações sobre horários e dias de funcionamento das lojas físicas;"""){
          bind(binder).bind(Cliente::flagHorarioDias)
        }
        checkBoxAceite("""Promoções, ofertas e quaisquer outras informações sobre os produtos comercializados pela 
          |<b>ENGECOPI</b>;""".trimMargin()){
          bind(binder).bind(Cliente::flagPromocoesOferta)
        }
        checkBoxAceite("""Pesquisas de satisfação/avaliação sobre vendas, atendimento, espaço físico das lojas, 
          |e-commerce e qualidade de quaisquer dos produtos comercializados pela <b>ENGECOPI</b>;""".trimMargin()){
          bind(binder).bind(Cliente::flagPesquisaSatisfacao)
        }
      }
      br()
      horizontalLayout {
        button("Aceito") {
          this.addThemeVariants(ButtonVariant.LUMO_LARGE,
                                ButtonVariant.LUMO_SUCCESS)
          onLeftClick {
            binder.writeBean(cliente)
            viewModel.aceito(cliente)
            form.close()
          }
        }
        button("Não Aceito") {
          this.addThemeVariants(ButtonVariant.LUMO_LARGE,
                                ButtonVariant.LUMO_ERROR)
          onLeftClick {
            viewModel.naoAceito(cliente)
            form.close()
          }
        }
      }
      binder.readBean(cliente)
    }
  }

  private fun (@VaadinDsl VerticalLayout).checkBoxAceite(text: String, block: (@VaadinDsl Checkbox).() -> Unit = {}) {
    this.checkBox {
      this.setLabelAsHtml(text)
      this.style("font-size", "20px")
      block()
    }
  }

  private fun (@VaadinDsl HorizontalLayout).dadosCliente(cliente: Cliente) {
    verticalLayout {
      this.isExpand = true
      h2("DADOS DO CLIENTE SIGNATÁRIO:")
      textField("NOME DO CLIENTE") {
        value = cliente.nome
        width = "500px"
        isReadOnly = true
      }
      textField("CPF DO CLIENTE") {
        value = cliente.cpf
        width = "500px"
        isReadOnly = true
      }
      textField("EMAIL") {
        value = cliente.email
        width = "500px"
        isReadOnly = true
      }
    }
  }

  private fun (@VaadinDsl HorizontalLayout).qrcode(value: String) {
    verticalLayout {
      val qrcode = ZXingVaadinWriter()
      qrcode.size = 300
      qrcode.value = value
      width = "${qrcode.size}px"
      add(qrcode)
    }
  }
}