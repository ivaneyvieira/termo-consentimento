DO @NOME := TRIM(:filtro);
DO @CODIGO := @NOME * 1;

SELECT C.no                                                         AS codigo,
       C.name                                                       AS nome,
       C.cpf_cgc                                                    AS cpf,
       IF(LENGTH(C3.auxString6) > 5, IFNULL(C3.auxString6, ''), '') AS email,
       (C.s6 & POW(2, 0)) != 0                                      AS flagEntregaTroca,
       (C.s6 & POW(2, 1)) != 0                                      AS flagUsoAsistencia,
       (C.s6 & POW(2, 2)) != 0                                      AS flagHorarioDias,
       (C.s6 & POW(2, 3)) != 0                                      AS flagPromocoesOferta,
       (C.s6 & POW(2, 4)) != 0                                      AS flagPesquisaSatisfacao,
       (C.s6 & POW(2, 5)) != 0                                      AS flagCadastro
FROM sqldados.custp          AS C
  LEFT JOIN sqldados.ctmore3 AS C3
	      ON C.no = C3.custno
WHERE C.fjflag = 1
  AND (name LIKE CONCAT('%', @NOME, '%') OR @NOME = '' OR C.no = @CODIGO)
ORDER BY C.no
LIMIT 5000
