//
//  ResultatViewController.swift
//  JeuDeMath
//
//  Created by etudiant on 4/28/21.
//

import UIKit

class ResultatViewController: UIViewController,UITableViewDelegate,UITableViewDataSource {
    
 
    @IBOutlet var tab2: UITableView!
    var allRepUser:Array<String>=["Repondu"]
    var allRep:Array<String>=["Reponse"]
    var allCalc:Array<String>=["Question"]
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        allRepUser.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tab2.dequeueReusableCell(withIdentifier: "myIdCell") as! MaCell
      
        cell.lblQuest.text=allCalc[indexPath.row]
        cell.lblRepUser.text=allRepUser[indexPath.row]
        cell.lblRep.text=allRep[indexPath.row]
        
        if(0<indexPath.row){
            if(!cell.lblRepUser.text!.elementsEqual(cell.lblRep.text!)) {cell.lblRepUser.backgroundColor=UIColor.red}
            else {cell.lblRepUser.backgroundColor=UIColor.green}
        }
        
        return cell
    }
    
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        //config delegate
        tab2.dataSource=self
        tab2.delegate=self
    }


    
}
