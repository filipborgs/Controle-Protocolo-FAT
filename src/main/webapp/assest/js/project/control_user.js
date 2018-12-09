
	
	if(localStorage.getItem('#$#EFR57AQw2#$!#') !== '=U86?VFGT9@#$344' ){
		
		window.location.replace("index.html"); // REDIRECIONA E NÃO PERMITE APERTAR O BOTÃO VOLTAR
	
	}
	
	function logout(){
		
		localStorage.clear();
		
		window.location.replace("index.html"); // REDIRECIONA E NÃO PERMITE APERTAR O BOTÃO VOLTAR
		
		/*
		
			FAZER CHAMADA AJAX PARA MATAR A SESSÃO DO TOKIN NO SERVIDOR
		
		*/
		
	}
        
        function getToken(){
            
            var tokin = localStorage.getItem("Dw33&121#$%8\z|^");
            
            return tokin;
        }
        
        function verifiquePermissaoExcluir(){
            
            return true;
        }
        
         function verifiquePermissaoEditar(){
            
            return true;
        }
        
        function verifiquePermissaoInserir(){
            
            return true;
        }
        
        
