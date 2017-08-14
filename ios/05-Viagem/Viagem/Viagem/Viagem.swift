//
//  Viagem.swift
//  Viagem
//
//  Created by admin on 14/08/17.
//  Copyright © 2017 mauriciodelira. All rights reserved.
//

import Foundation

class Viagem: NSObject {
    var destino: String!  // nunca vai ser null
    var convidados: Int!  // nunca vai ser null
    var orcamento: Float!  // nunca vai ser null (!)
    var altaTemporada: Bool!
    
    override var description: String{
        return self.destino
    }
    
    init(destino : String!, convidados : Int!, orcamento : Float!, altaTemporada : Bool!) {
        self.altaTemporada = altaTemporada
        self.convidados = convidados
        self.orcamento = orcamento
        self.destino = destino
    }
    
    func porPessoa() -> Float {
        return self.orcamento / Float(self.convidados + 1)
    }
    
}
