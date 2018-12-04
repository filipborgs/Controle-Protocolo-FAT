

			$.ajax
			({
				url : uri+metodo,
				type : 'POST',
				data : 
				{
					user: 'admin', 
					senha: 'admin' 
				},
				beforeSend : function(){
					//alert("ENTRANDO...");
				}
			})
			.done(function(msg){
					
			})
			.fail(function(msg){
				alert("Falha ao tentar realizar o login");
				console.log(msg);
			}); 
	
	
	
	
			$.post(uri+metodo, {
					user: 'admin', 
					senha: 'admin' 
				}, function(msg){
					alert(msg);
					alert("sucesso");
				}
			);