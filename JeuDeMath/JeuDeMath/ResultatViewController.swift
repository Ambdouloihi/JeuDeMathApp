//
//  ResultatViewController.swift
//  JeuDeMath
//
//  Created by etudiant on 4/28/21.
//

import UIKit

class ResultatViewController: UIViewController,UITableViewDelegate,UITableViewDataSource {
    
    @IBOutlet var tableauResultat: UITableView!
    @IBOutlet var tab2: UITableView!
    
    var allRepUser:Array<String>=["]
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        allRepUser.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tab2.dequeueReusableCell(withIdentifier: "myIdCell") as! MaCell
      
        cell.lblQuest.text=allRepUser[0]
        cell.lblRepUser.text=allRepUser[indexPath.row]
        //cell.lblRep.text="r"
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
