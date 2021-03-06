//
//  ErrorAlertController.swift
//  SideDishApp
//
//  Created by delma on 2020/05/03.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class ErrorAlertController: UIAlertController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "오류 알림"
    }
    
    func set(message: NetworkErrorCase) {
        self.message = message.description + "다시 시도해 주세요"
    }
    
    func makeDefaultAction(handler:@escaping () -> Void) {
        let ok = UIAlertAction(title: "확인", style: .default) { ok in
            handler()
        }
        self.addAction(ok)
    }
    
}
