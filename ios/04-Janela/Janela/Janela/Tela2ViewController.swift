//
//  Tela2ViewController.swift
//  Janela
//
//  Created by admin on 07/08/17.
//  Copyright © 2017 mauriciodelira. All rights reserved.
//

import UIKit

class Tela2ViewController: UIViewController {

    // label com o nome das funções
    @IBOutlet weak var lbMethodCall: UILabel!
    
    @IBAction func btVoltarTelaUm(_ sender: Any) {

        self.presentedViewController.
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        print("Tela 2: func viewDidLoad()")
        self.lbMethodCall.text = "viewDidLoad"
        
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        // quando for apresentar a tela, antes disso, vai fazer algo
        
        print("Tela 2: viewWillAppear")
        self.lbMethodCall.text = "viewWillAppear"
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(true)
        // depois de apresentar ao usuário, vai fazer algo
        
        print("Tela 2: viewDidAppear")
        self.lbMethodCall.text = "viewDidAppear"
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        
        print("Tela 2: func didReceiveMemoryWarning()")
    
        self.lbMethodCall.text = "viewDidAppear"
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
