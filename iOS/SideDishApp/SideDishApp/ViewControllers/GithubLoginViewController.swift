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
    @IBOutlet var indicator: UIActivityIndicatorView!
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
            guard let menuViewController = self.storyboard?.instantiateViewController(withIdentifier: "NavigationViewController") as? UINavigationController else { return }
            self.present(menuViewController, animated: true)
        }
        decisionHandler(.allow)
    }
    
    func webView(_ webView: WKWebView, didStartProvisionalNavigation navigation: WKNavigation!) {
        indicator.isHidden = false
        indicator.startAnimating()
    }
    
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        indicator.isHidden = true
        indicator.stopAnimating()
    }
    
    func webView(_ webView: WKWebView, didFailProvisionalNavigation navigation: WKNavigation!, withError error: Error) {
        indicator.isHidden = true
        indicator.stopAnimating()
        present(networkErrorAlert(), animated: true)
    }
    
    private func networkErrorAlert() -> UIAlertController {
        let alert = UIAlertController(title: "알림", message: "네트워크에 오류가 생겼습니다. 잠시후 다시 시도해주세요", preferredStyle: .alert)
        let ok = UIAlertAction(title: "OK", style: .default) { _ in
            self.dismiss(animated: true)
        }
        alert.addAction(ok)
        return alert
    }
}
