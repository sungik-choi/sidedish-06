//
//  GithubLoginViewController.swift
//  SideDishApp
//
//  Created by delma on 2020/04/28.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit
import WebKit

class GithubLoginViewController: UIViewController, WKUIDelegate, WKNavigationDelegate {

    @IBOutlet var webView: WKWebView!
    
    override func loadView() {
        super.loadView()
        webView = WKWebView(frame: self.view.frame)
        webView.uiDelegate = self
        webView.navigationDelegate = self
        self.view = self.webView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let url = URL(string: "https://github.com/login/oauth/authorize/?client_id=4946b46078dcaa5adfa6&scope=user%20public_repo")
        let request = URLRequest(url: url!)
        webView.load(request)
    }
   

}
