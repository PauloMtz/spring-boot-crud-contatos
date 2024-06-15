//valida numero inteiro com mascara
function campoNumerico(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	
	if ((charCode >= 48 && charCode <= 57) || charCode <= 31) {
		return true;
	}
	
	return false;
}

//adiciona mascara de cnpj
function mascaraCNPJ(cnpj) { 
    if (campoNumerico(cnpj) == false) {
        event.returnValue = false;
    }     
    return formataCampo(cnpj, '00.000.000/0000-00', event);
}

//adiciona mascara de cep
function mascaraCep(cep) {
    if (campoNumerico(cep) == false) {
        event.returnValue = false;
    }       
    return formataCampo(cep, '00000-000', event);
}

//adiciona mascara de data
function mascaraData(data) {
    if (campoNumerico(data) == false) {
        event.returnValue = false;
    }    
    return formataCampo(data, '00/00/0000', event);
}

//adiciona mascara ao telefone
function mascaraTelefone(tel) { 
    if (campoNumerico(tel) == false) {
        event.returnValue = false;
    }      
    return formataCampo(tel, '(00) 0000-0000', event);
}

//adiciona mascara ao CPF
function mascaraCPF(cpf) {
    if (campoNumerico(cpf) == false) {
        event.returnValue = false;
    }
    validaCPF(cpf);   
    return formataCampo(cpf, '000.000.000-00', event);
}

//adiciona mascara ao RG
function mascaraRG(rg) {
    if (campoNumerico(rg) == false) {
        event.returnValue = false;
    }  
    return formataCampo(rg, '00.000.000-0', event);
}

//valida telefone
function validaTelefone(tel) {
    exp = /\(\d{2}\)\ \d{4}\-\d{4}/
    if (!exp.test(tel.value))
        alert('Numero de Telefone Invalido!');
}

//valida CEP
function validaCep(cep) {
    exp = /\d{2}\.\d{3}\-\d{3}/
    if (!exp.test(cep.value))
        alert('Numero de Cep Invalido!');               
}

//valida data
function validaData(data) {
    exp = /\d{2}\/\d{2}\/\d{4}/
    if (!exp.test(data.value))
        alert('Data Invalida!');                        
}

//valida o CPF digitado
function validaCPF(Objcpf) {
    var cpf = Objcpf.value;
    exp = /\.|\-/g
    cpf = cpf.toString().replace( exp, "" ); 
    var digitoDigitado = eval(cpf.charAt(9)+cpf.charAt(10));
    var soma1=0, soma2=0;
    var vlr =11;

    for (i=0;i<9;i++){
            soma1+=eval(cpf.charAt(i)*(vlr-1));
            soma2+=eval(cpf.charAt(i)*vlr);
            vlr--;
    }       
    soma1 = (((soma1 * 10) % 11) == 10 ? 0 : ((soma1 * 10) % 11));
    soma2 = (((soma2 + (2 * soma1)) * 10) % 11);

    var digitoGerado = (soma1 * 10) + soma2;
    if (digitoGerado != digitoDigitado) {        
        alert('CPF Invalido!');
        // limpa o campo CPF
        $("#cpf").val("");
    }
}

//valida o CNPJ digitado
function validarCNPJ(ObjCnpj) {
    var cnpj = ObjCnpj.value;
    var valida = new Array(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
    var dig1 = new Number;
    var dig2 = new Number;

    exp = /\.|\-|\//g
    cnpj = cnpj.toString().replace( exp, "" ); 
    var digito = new Number(eval(cnpj.charAt(12) + cnpj.charAt(13)));

    for (i = 0; i < valida.length; i++){
            dig1 += (i > 0 ? (cnpj.charAt(i - 1) * valida[i]) : 0);  
            dig2 += cnpj.charAt(i) * valida[i];       
    }
    dig1 = (((dig1 % 11) < 2) ? 0 : (11 - (dig1 % 11)));
    dig2 = (((dig2 % 11) < 2) ? 0 : (11 - (dig2 % 11)));

    if(((dig1 * 10) + dig2) != digito)  
            alert('CNPJ Invalido!');

}

//formata de forma generica os campos
function formataCampo(campo, mascara, evento) { 
    var boleanoMascara; 

    var Digitato = evento.keyCode;
    exp = /\-|\.|\/|\(|\)| /g
    campoSoNumeros = campo.value.toString().replace( exp, "" ); 

    var posicaoCampo = 0;    
    var NovoValorCampo="";
    var TamanhoMascara = campoSoNumeros.length;; 

    if (Digitato != 8) { // backspace 
        for(i=0; i <= TamanhoMascara; i++) { 
            boleanoMascara  = ((mascara.charAt(i) == "-") || (mascara.charAt(i) == ".") || (mascara.charAt(i) == "/"));
            boleanoMascara  = boleanoMascara || ((mascara.charAt(i) == "(") || (mascara.charAt(i) == ")") || (mascara.charAt(i) == " "));
            if (boleanoMascara) { 
                NovoValorCampo += mascara.charAt(i); 
                TamanhoMascara++;
            }else { 
                NovoValorCampo += campoSoNumeros.charAt(posicaoCampo); 
                posicaoCampo++; 
            }              
        }      
        campo.value = NovoValorCampo;
        return true; 
    }else { 
        return true; 
    }
}
