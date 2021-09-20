UPDATE sqldados.custp AS C
SET C.s6 = POW(2, 0) * :flagEntregaTroca + POW(2, 1) * :flagUsoAsistencia +
	   POW(2, 2) * :flagHorarioDias + POW(2, 3) * :flagPromocoesOferta +
	   POW(2, 4) * :flagPesquisaSatisfacao
WHERE no = :custno