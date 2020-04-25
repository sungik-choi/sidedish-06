//
//  NetworkManager.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

protocol NetworkManageable {
    typealias Handler = (Result<Data, NetworkErrorCase>) -> Void
    
    func getResource(url: String, methodType: HTTPMethod, body: Data?, completion: @escaping Handler)
}

struct NetworkManager {
    
    func getResource(url: String, methodType: HTTPMethod, body: Data? = nil, completion: @escaping(Result<Data, NetworkErrorCase>) -> Void) {
        var request = URLRequest(url: URL(string: url)!)
        request.httpMethod = methodType.rawValue
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = body
        
        URLSession.shared.dataTask(with: request) { (data, response, error) in
            guard let data = data, error == nil else {
                if let error = error as NSError?, error.domain == NSURLErrorDomain { completion(.failure(.InvalidURL)) }
                return
            }
            completion(.success(data))
        }.resume()
    }
}

