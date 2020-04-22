//
//  LoginViewController.swift
//  SideDishApp
//
//  Created by delma on 2020/04/20.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    @IBOutlet var titleLabel: UILabel!
    @IBOutlet var signInButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureConstraints()
    }
    
    @IBAction func signInGithub(_ sender: UIButton) {
        guard let menuViewController = self.storyboard?.instantiateViewController(withIdentifier: "MenuViewController") else { return }
        self.present(menuViewController, animated: true)
    }
    
    private func configureConstraints() {
        let height = self.view.bounds.height
        
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.topAnchor.constraint(equalTo: self.view.topAnchor, constant: height/5).isActive = true
        titleLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor, constant: 0).isActive = true

        signInButton.translatesAutoresizingMaskIntoConstraints = false
        signInButton.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant: -(height/5)).isActive = true
        signInButton.centerXAnchor.constraint(equalTo: self.view.centerXAnchor, constant: 0).isActive = true
    }

}
