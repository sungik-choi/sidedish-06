//
//  NetworkManager.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

protocol NetworkManageable {
    func getResource<T:Decodable>(url: URL, methodType: HTTPMethod, dataType: T.Type, body: Data?, handler: @escaping (Result<Data, NetworkErrorCase>) -> Void) throws
}

struct NetworkManager {
    let decodeManager = DecodeManager()
    
    func getResource<T:Decodable>(url: URL, methodType: HTTPMethod, dataType:T.Type, body: Data? = nil, completion: @escaping(Result<Any, NetworkErrorCase>) -> Void) {
        var request = URLRequest(url: url)
        request.httpMethod = methodType.rawValue
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = body
        
        URLSession.shared.dataTask(with: request) { (data, response, error) in
            guard let data = data, error == nil else {
                if let error = error as NSError?, error.domain == NSURLErrorDomain { completion(.failure(.InvalidURL)) }
                return
            }
            self.decodeManager.decode(data: data, dataType: dataType) { completion($0) }
        }.resume()
    }
}

struct DecodeManager {
    func decode<T:Decodable>(data: Data, dataType:T.Type, completion: @escaping (Result<Any, NetworkErrorCase>) -> Void) {
        let decoder = JSONDecoder()
        guard let anyData = try? decoder.decode(dataType, from: data) else { completion(.failure(.DecodeError)); return }
        completion(.success(anyData))
    }
}
