
//////////////////////////////////

function criarTabela(){
	
	
	
}

//////////////////////////////////

function cadastrarPessoa(){
	
	let pessoa = {
		"nome": document.getElementById('nome').value,
		"telefone": document.getElementById('tel').value,
		"email": document.getElementById('email').value,
		"senha": document.getElementById('senha').value
	};
	
		
	fetch("/pessoa", {
		
		method: "POST",
		headers: {
			"content-type": "application/JSON"
		},
		
		body: JSON.stringify(pessoa)
		
	}).then(function(response){
		
		document.location = 'twitter.html';
		
	}).catch(function(error){
		console.log(error);
	});
	
}