//
//  ErrorAlert.swift
//  SideDishApp
//
//  Created by delma on 2020/05/03.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class ErrorAlert: UIAlertController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    func set(title: String) {
        self.title = title
    }
    
    func set(message: NetworkErrorCase) {
        self.message = message.description
    }
    
    
}
