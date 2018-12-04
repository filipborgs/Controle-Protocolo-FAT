
	
	if(localStorage.getItem('#$#EFR57AQw2#$!#') != '=U86?VFGT9@#$344' ){
		
		window.location.replace("index.html"); // REDIRECIONA E NÃO PERMITE APERTAR O BOTÃO VOLTAR
	
	}
	
	function logout(){
		
		localStorage.clear();
		
		window.location.replace("index.html"); // REDIRECIONA E NÃO PERMITE APERTAR O BOTÃO VOLTAR
		
		/*
		
			FAZER CHAMADA AJAX PARA MATAR A SESSÃO DO TOKIN NO SERVIDOR
		
		*/
		
	}