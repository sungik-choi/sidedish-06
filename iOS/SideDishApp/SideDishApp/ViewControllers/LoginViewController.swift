//
//  LoginViewController.swift
//  SideDishApp
//
//  Created by delma on 2020/04/20.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    @IBOutlet var backgroundView: UIView!
    @IBOutlet var logo: UIImageView!
    @IBOutlet var signInButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureConstraints()
    }
    
    @IBAction func signInGithub(_ sender: UIButton) {
        guard let menuViewController = self.storyboard?.instantiateViewController(withIdentifier: "NavigationViewController") as? UINavigationController else { return }
        self.present(menuViewController, animated: true)
    }
    
    private func configureConstraints() {
        let height = self.view.bounds.height
        
        backgroundView.translatesAutoresizingMaskIntoConstraints = false
        backgroundView.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        backgroundView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor).isActive = true
        backgroundView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor).isActive = true
        backgroundView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
        
        logo.translatesAutoresizingMaskIntoConstraints = false
        logo.centerXAnchor.constraint(equalTo: self.view.centerXAnchor, constant: 0).isActive = true
        logo.widthAnchor.constraint(equalTo: self.view.widthAnchor, multiplier: 0.5).isActive = true
        logo.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 0.5).isActive = true
        logo.bottomAnchor.constraint(equalTo: signInButton.topAnchor, constant: -(height/9)).isActive = true

        signInButton.translatesAutoresizingMaskIntoConstraints = false
        signInButton.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant: -(height/5)).isActive = true
        signInButton.centerXAnchor.constraint(equalTo: self.view.centerXAnchor, constant: 0).isActive = true
    }

}
