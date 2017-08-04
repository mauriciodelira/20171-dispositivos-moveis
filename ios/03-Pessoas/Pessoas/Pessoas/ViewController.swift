//
//  ViewController.swift
//  Pessoas
//
//  Created by admin on 04/08/17.
//  Copyright © 2017 mauriciodelira. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    // Declara as variaveis referenciando objetos da tela.
    // ctrl + clique e arrasta da View para cá.
    
    // Declaração de objetos: se terminar com ?, ele pode ser nulo.
    // Se terminar com !, ele não pode ser nulo.
    @IBOutlet weak var tfNome: UITextField!
    @IBOutlet weak var tfIdade: UITextField!
    @IBOutlet weak var lbContador: UILabel!
    
    var cadastro = Cadastro()
    
    // Ação do botão
    // Quando arrasta, define que o tipo dele é IBAction.
    @IBAction func btCadastrar(_ sender: Any) {
        
        // LET = constante
        // VAR = variável altamente mutável (pode ser também usado como constante)
        let constNome = self.tfNome.text;
        
        // Parse de uma String (.text) para Int: feito apenas colocando a classe antes.
        let constIdade = Int(self.tfIdade.text!)
        
        // Cria uma pessoa temporaria e adiciona ao cadast
        let temp = Pessoa(nome: constNome!, idade: constIdade!)
        self.cadastro.addPessoa(pessoa: temp)
        
        self.lbContador.text = String(self.cadastro.count())
        
        // print = Log.i = printa no console.
        // para formatar: \( variavel )
        // print("Nome: \(constNome); Idade: \(constIdade)")
        print(self.cadastro.listaPessoas)
        
        
    }
    
    
}

