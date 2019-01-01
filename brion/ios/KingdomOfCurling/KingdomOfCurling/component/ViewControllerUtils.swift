//
//  ViewControllerUtils.swift
//  KingdomOfCurling
//
//  Created by jucher park on 01/01/2019.
//  Copyright © 2019 jucher park. All rights reserved.
//

import Foundation
import UIKit

extension UIViewController {
    func getViewController(storyboradName:String, bundle:Bundle, identifier:String) -> UIViewController {
        let st = UIStoryboard(name: storyboradName, bundle: bundle)
        let vc = st.instantiateViewController(withIdentifier: identifier)
        return vc
    }
}
