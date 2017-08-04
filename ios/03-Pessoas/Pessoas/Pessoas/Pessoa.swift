//
//  Pessoa.swift
//  Pessoas
//
//  Created by admin on 04/08/17.
//  Copyright © 2017 mauriciodelira. All rights reserved.
//

import Foundation

// o : depois do nome da classe significa extends ou implements (interface ou classe mãe)
class Pessoa: NSObject{
    var nome: String!
    var idade: Int!
    
    // Construtor: simplesmente "init"
    init(nome:String, idade:Int) {
        self.idade = idade
        self.nome = nome
    }
    
    // Define uma funcao referente à Pessoa
    func maiorIdade(/*parameters*/) -> Bool {
        return self.idade >= 18
    }
    
    // toString no mundo swift = description
    override var description: String{
        return "nome: \(self.nome) - idade: \(self.idade)"
    }
}
