package br.com.astrosoft.termos.model

import br.com.astrosoft.termos.model.beans.Loja
import br.com.astrosoft.termos.model.beans.UserSaci
import br.com.astrosoft.framework.model.Config.appName
import br.com.astrosoft.framework.model.DB
import br.com.astrosoft.framework.model.QueryDB
import br.com.astrosoft.termos.model.beans.Cliente

class QuerySaci : QueryDB(driver, url, username, password) {
  fun findUser(login: String?): UserSaci? {
    login ?: return null
    val sql = "/sqlSaci/userSenha.sql"
    return query(sql, UserSaci::class) {
      addParameter("login", login)
      addParameter("appName", appName)
    }.firstOrNull()
  }

  fun findAllUser(): List<UserSaci> {
    val sql = "/sqlSaci/userSenha.sql"
    return query(sql, UserSaci::class) {
      addParameter("login", "TODOS")
      addParameter("appName", appName)
    }
  }

  fun allLojas(): List<Loja> {
    val sql = "/sqlSaci/listLojas.sql"
    return query(sql, Loja::class)
  }

  fun findLojaByCnpj(cnpj: String): Loja? {
    val sql = "/sqlSaci/findLojaByCnpj.sql"
    return query(sql, Loja::class) {
      addOptionalParameter("cnpj", cnpj)
    }.firstOrNull()
  }

  fun updateUser(user: UserSaci) {
    val sql = "/sqlSaci/updateUser.sql"
    script(sql) {
      addOptionalParameter("login", user.login)
      addOptionalParameter("bitAcesso", user.bitAcesso)
      addOptionalParameter("loja", user.storeno)
      addOptionalParameter("appName", appName)
    }
  }

  // Clientes
  fun findClientes(filtro: String): List<Cliente> {
    val sql = "/sqlSaci/clientes.sql"
    return query(sql, Cliente::class) {
      addParameter("filtro", filtro)
    }
  }

  companion object {
    private val db = DB("saci")
    internal val driver = db.driver
    internal val url = db.url
    internal val username = db.username
    internal val password = db.password
    val ipServer: String? = url.split("/").getOrNull(2)
  }
}

val saci = QuerySaci()
