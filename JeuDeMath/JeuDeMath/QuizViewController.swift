//
//  QuizViewController.swift
//  JeuDeMath
//
//  Created by etudiant on 4/28/21.
//


import UIKit

class QuizViewController: UIViewController {
    private var ind=0
    private var COUNTERSTART=5
    private var counter=0
    
    private var NBQUEST=5
    private var allQuest:Array<Question>?
    private var currentQuest:Question?
    
    var allRepUser:Array<String>=[]
    private var rep=""
    private var indRepUser=0
    @IBOutlet var lblTemps: UILabel!
    @IBOutlet var lblQuestion: UILabel!
    @IBOutlet var lblCalcul: UILabel!
    @IBOutlet var btnSuivant: UIButton!
    @IBOutlet var segmentReponse: UISegmentedControl!
    @IBOutlet var btnTerminer: UIButton!
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        if segue.identifier == "goResult" {
            let vc=segue.destination as! ResultatViewController
            vc.allRepUser = self.allRepUser
        }
    }
    required init?(coder aDecoder: NSCoder)
    {
        super.init(coder: aDecoder);
       allQuest=fillAllQuest()
        counter=COUNTERSTART
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        btnTerminer.isEnabled=false
        nextQuest(i: ind)
        
        let timer = Timer.scheduledTimer(timeInterval: 1.0, target: self, selector: #selector(fire), userInfo: nil, repeats: true)

       // btnSuivant.isEnabled=false
    }
    
    
    
    @objc func fire()
         {
            if(0<counter){counter-=1}
            lblTemps.text="\(counter) sec"
            if(counter==0){
                //if ((ind+1)<NBQUEST) {btnSuivant.isEnabled=true}
                counter=COUNTERSTART
                
                indRepUser=segmentReponse.selectedSegmentIndex
                rep=segmentReponse.titleForSegment(at: ind-1) ?? ""
                allRepUser[ind]=rep
            }
         }
    

    @IBAction func fillnextQuestion(_ sender: Any) {
        if ((ind+1)==NBQUEST) { btnTerminer.isEnabled=true }
        //else {btnSuivant.isEnabled=true}
        
        
        nextQuest(i: ind)
        //btnSuivant.isEnabled=false
    }
    @IBAction func showResult(_ sender: Any) {
        let vc = ResultatViewController()
        vc.allRepUser = allRepUser
        self.navigationController?.pushViewController(vc, animated: true)
        //navigationController?.pushViewController(vc, animated: true)
    }
    

    func nextQuest(i:Int)  {
        currentQuest=allQuest?[i]
        
        lblQuestion.text="Question \(i+1)"
        lblCalcul.text=(currentQuest?.text ?? "calcul")+" = ?"
        
        for (i,calc) in (currentQuest?.listRep.enumerated())! {
            segmentReponse.setTitle(String(calc), forSegmentAt: i )
        }
        
         if((ind+1)<NBQUEST) { ind+=1 }
    }
    
    func fillAllQuest() -> Array<Question> {
        var lq:Array<Question>=[]
        var q:Question
        for _ in 0...NBQUEST-1{
            q=Question()
            lq.append(q)
        }
        return lq
    }
    
}

class Question {
    var answer:Int
    private var nb1:Int
    private var nb2:Int
    var text:String
    var listRep:Array<Int>!
    
    init() {
        nb1=Int.random(in: 0...10)
        nb2=Int.random(in: 0...10)
        answer=nb1*nb2
        text="\(nb1) X \(nb2)"
        listRep=fillListRep()
    }
    
    func isAnswer(n:Int) -> Bool {
        n==self.answer
    }
    func fillListRep()-> Array<Int>{
        var l:[Int]=[]
        
        let max=(answer != 0) ? (answer+answer)/2 : 10
        
        var piege:Int
        for _ in 1...2 {
            piege=Int.random(in: 0...max)
            l.append(piege)
        }
        
    
        piege=(answer != 0) ? 2*answer : Int.random(in: 0...10)
        
        l.insert(piege, at: Int.random(in: 0...l.count-1))
        l.insert(answer, at: Int.random(in: 0...l.count-1))
        return l
    }
}
