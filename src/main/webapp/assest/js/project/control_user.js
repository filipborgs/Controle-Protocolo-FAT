

if (localStorage.getItem('#$#EFR57AQw2#$!#') !== '=U86?VFGT9@#$344') {

    window.location.replace("index.html"); // REDIRECIONA E NÃO PERMITE APERTAR O BOTÃO VOLTAR

}

function logout() {

    localStorage.clear();

    window.location.replace("index.html"); // REDIRECIONA E NÃO PERMITE APERTAR O BOTÃO VOLTAR

    /*
     
     FAZER CHAMADA AJAX PARA MATAR A SESSÃO DO TOKIN NO SERVIDOR
     
     */

}

function getToken() {

    var tokin = localStorage.getItem("Dw33&121#$%8\z|^");

    return tokin;
}

function verifiquePermissaoExcluir() {

    if (localStorage.getItem('sdw3354SD&@!5231') === 'S') {

        return true;

    }else{
        return false;
    }
}

function verifiquePermissaoEditar() {

    if (localStorage.getItem('zXGT*&&!3YHRU+|D') === 'S') {

        return true;

    }else{
        return false;
    }
    
}

function verifiquePermissaoInserir() {

    if (localStorage.getItem('QWER8765@#*)--Z^') === 'S') {

        return true;

    }else{
        return false;
    }
    
}


