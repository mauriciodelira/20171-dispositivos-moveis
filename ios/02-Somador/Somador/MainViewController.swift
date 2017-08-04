//
//  ViewController.swift
//  Somador
//
//  Created by admin on 24/07/17.
//  Copyright © 2017 mauriciodelira. All rights reserved.
//

import UIKit

class MainViewController: UIViewController {

    @IBOutlet weak var tfNumero1: UITextField!
    @IBOutlet weak var tfNumero2: UITextField!
    @IBOutlet weak var lbResultado: UILabel!
    
    @IBAction func salvar(_ sender: Any) {
        var n1: Int!
        var n2: Int!
        
        print("funfou")
        
        n1 = Int(self.tfNumero1.text!)
        n2 = Int(self.tfNumero2.text!)
        
        self.lbResultado.text = String(n1 + n2)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

