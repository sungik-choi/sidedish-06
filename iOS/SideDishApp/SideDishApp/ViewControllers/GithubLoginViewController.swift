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
    let successResponse = "http://52.79.117.147/"
    
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
    
    func webView(_ webView: WKWebView, decidePolicyFor navigationResponse: WKNavigationResponse, decisionHandler: @escaping (WKNavigationResponsePolicy) -> Void) {
        if let responseURL = navigationResponse.response.url, responseURL.absoluteString == successResponse {
            self.dismiss(animated: true)
            guard let menuViewController = self.storyboard?.instantiateViewController(withIdentifier: "NavigationViewController") as? UINavigationController else { return }
            self.present(menuViewController, animated: true)
        }
        decisionHandler(.allow)
    }
    
}
