
CREATE ALGORITHM = UNDEFINED VIEW  `view_table_pessoa` AS

(
	SELECT
		id_pessoa,	
		IF(nome IS NULL,"",nome) nome,
		IF(setor IS NULL,"",setor) setor,
		IF(descricao IS NULL,"",descricao) descricao,
		IF(excluido = 'N' OR excluido = 'n' , 'N' , excluido) excluido
		
	FROM
		pessoa
		
	WHERE
		excluido = 'N'
		AND id_pessoa > 0
)
	

