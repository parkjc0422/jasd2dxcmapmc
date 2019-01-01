//
//  ViewController.swift
//  KingdomOfCurling
//
//  Created by jucher park on 11/11/2018.
//  Copyright © 2018 jucher park. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    var timer :Timer?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        startTimer()
        
        let btn = UIButton.init(frame: CGRect.init(x: 0,
                                                      y: 0,
                                                      width: 100,
                                                      height: 100))
        
        
        
        
        btn.backgroundColor = UIColor.red
        btn.addAction(for: .touchUpInside) {
//            let st = UIStoryboard(name: "Main", bundle: Bundle.main)
//            let vc = st.instantiateViewController(withIdentifier: "MainViewController") as! MainViewController
//            self.navigationController?.pushViewController(vc, animated: false)
//            self.present(vc, animated: true, completion: nil)
            
            let vc = self.getViewController(storyboradName: "Main", identifier: "MainViewController") as! MainViewController
            self.present(vc, animated: true, completion: nil)
        }
        
        self.view .addSubview(btn)
        
    }

    func startTimer () {
        self.timer = Timer.scheduledTimer(withTimeInterval: 5000,
                                          repeats: false,
                                          block: { (Timer) in
            self.performSegue(withIdentifier: "MainLoad", sender: self)
        })
    }
}

