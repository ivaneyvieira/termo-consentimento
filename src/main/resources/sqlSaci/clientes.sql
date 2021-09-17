DO @NOME := TRIM(:filtro);
DO @CODIGO := @FILTRO * 1;

SELECT C.no                                                         AS codigo,
       C.name                                                       AS nome,
       C.cpf_cgc                                                    AS cpf,
       IF(LENGTH(C3.auxString6) > 5, IFNULL(C3.auxString6, ''), '') AS email
FROM sqldados.custp          AS C
  LEFT JOIN sqldados.ctmore3 AS C3
	      ON C.no = C3.custno
WHERE C.fjflag = 1
  AND (name LIKE CONCAT('%', @NOME, '%') OR @NOME = '' OR C.no = @CODIGO)
ORDER BY C.no
LIMIT 5000
