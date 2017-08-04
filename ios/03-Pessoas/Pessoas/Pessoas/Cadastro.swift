//
//  Cadastro.swift
//  Pessoas
//
//  Created by admin on 04/08/17.
//  Copyright © 2017 mauriciodelira. All rights reserved.
//

import Foundation

class Cadastro {
    // Setar uma lista de objetos do tipo Pessoa
    var listaPessoas: Array<Pessoa>!
    
    init() {
        // Inicializar a lista
        self.listaPessoas = Array<Pessoa>();
    }
    
    func count() -> Int {
        return self.listaPessoas.count
    }
    
    // Se não retornar nada, nem bota o tipo de retorno na func
    func addPessoa(pessoa:Pessoa){
        self.listaPessoas.append(pessoa)
    }
    
}
