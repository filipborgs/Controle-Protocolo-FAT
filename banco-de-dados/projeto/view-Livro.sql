
CREATE ALGORITHM = UNDEFINED VIEW  `view_table_livro` AS

(
	SELECT
		id_livro,	
		numero,
		IF(nome IS NULL,"",nome) nome,
		IF(detalhes IS NULL,"",detalhes) detalhes,
		IF(folhas IS NULL,"",folhas) folhas,
		IF(cor IS NULL,"",cor) cor,	
		IF(data_inicio IS NULL,"",data_inicio) data_inicio,
		IF(data_fim IS NULL,"",data_fim) data_fim,
		IF(data_inicio IS NULL,"",DATE_FORMAT(data_inicio, '%d/%m/%Y')) dt_ini,
		IF(data_fim IS NULL,"",DATE_FORMAT(data_fim, '%d/%m/%Y')) dt_fim,
		IF(excluido = 'N' OR excluido = 'n' , 'N' , excluido) excluido
		
	FROM
		livro
		
	WHERE
		excluido = 'N'
		AND id_livro > 0
)
	