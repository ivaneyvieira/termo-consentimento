package br.com.astrosoft.termos.view.termos.columms

import br.com.astrosoft.framework.view.addColumnInt
import br.com.astrosoft.framework.view.addColumnLocalDate
import br.com.astrosoft.framework.view.addColumnLocalDateTime
import br.com.astrosoft.framework.view.addColumnString
import br.com.astrosoft.termos.model.beans.Cliente
import com.vaadin.flow.component.grid.Grid

object ClienteColumns {
  fun Grid<Cliente>.clienteDataAceite() = addColumnLocalDateTime(Cliente::dataHoraAceite) {
    this.setHeader("Data Aceite")
  }

  fun Grid<Cliente>.clienteDataCancelamento() = addColumnLocalDateTime(Cliente::dataHoraCancelamento) {
    this.setHeader("Data Cancelamento")
  }

  fun Grid<Cliente>.clienteCodigo() = addColumnInt(Cliente::codigo) {
    this.setHeader("Código")
  }

  fun Grid<Cliente>.clienteNome() = addColumnString(Cliente::nome) {
    this.setHeader("Nome")
  }

  fun Grid<Cliente>.clienteCpf() = addColumnString(Cliente::cpf) {
    this.setHeader("CPF")
  }

  fun Grid<Cliente>.clienteEmail() = addColumnString(Cliente::email) {
    this.setHeader("E-mail")
  }
}