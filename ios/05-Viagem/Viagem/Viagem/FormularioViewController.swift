//
//  ViewController.swift
//  Viagem
//
//  Created by admin on 14/08/17.
//  Copyright © 2017 mauriciodelira. All rights reserved.
// editar, adicionar hora, excluir
//

import UIKit

class FormularioViewController: UIViewController {
    @IBOutlet weak var tfDestino: UITextField!
    @IBOutlet weak var tfOrcamento: UITextField!
    @IBOutlet weak var lbConvidados: UILabel!
    @IBOutlet weak var swAltaTemporada: UISwitch!
    @IBOutlet weak var stConvidados: UIStepper!
   
    // vai vir do ListarTableViewController, método override func prepare(for segue: UIStoryboardSegue, sender: Any?)    
    var cadastro : Cadastro!
    
    @IBAction func definirQtdConvidados(_ sender: Any) {
    self.lbConvidados.text = String(Int(self.stConvidados.value))
    }
    
    @IBAction func btSalvar(_ sender: Any) {
        // let -> um tipo de variável que nunca vai perder a sua referência, mas seu conteudo pode ser alterado
        let dest = self.tfDestino.text
        let people = Int(self.stConvidados.value)
        let budget = Float(self.tfOrcamento.text!)
        let isAltaTemp = self.swAltaTemporada.isOn
        
        let viagem = Viagem(destino: dest, convidados: people, orcamento: budget, altaTemporada: isAltaTemp)
        
        print("\(viagem)")
        self.cadastro.add(nova: viagem)
        
        self.navigationController?.popViewController(animated: true)
        
    }
    
    @IBAction func btActionCancelar(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    
}

