//
//  NetworkManager.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

protocol NetworkManageable {
    typealias Handler = (Data?, Error?) -> Void
    
    func getResource(url: String, methodType: HTTPMethod, body: Data?, completion: @escaping Handler) throws
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

struct MockNetworkSuccessSideStub: NetworkManageable {
    func getResource(url: String, methodType: HTTPMethod, body: Data?, completion: @escaping Self.Handler) throws {
        let data = """
        {
            "menuType": "밑반찬",
            "menuTypeTitle": "언제 먹어도 든든한 밑반찬",
            "data": [
                {
                    "hash": "HBBCC",
                    "image": "http://public.codesquad.kr/jk/storeapp/data/fdb0d5fcfb86e332505785225a6d9ade.jpg",
                    "alt": "[마샐미디쉬] 유자소스 연근무침 250g",
                    "delivery_type": [
                        "새벽배송",
                        "전국택배"
                    ],
                    "title": "[마샐미디쉬] 유자소스 연근무침 250g",
                    "description": "향긋한 유자향과 아삭한 연근",
                    "originPrice": null,
                    "salePrice": "6,500원",
                    "badge": []
                },
                {
                    "hash": "HB9C1",
                    "image": "http://public.codesquad.kr/jk/storeapp/data/043cf496f07899e7515f761e29d1ffa9.jpg",
                    "alt": "[너의반찬] 미소된장 고추무침 200g",
                    "delivery_type": [
                        "새벽배송",
                        "전국택배"
                    ],
                    "title": "[너의반찬] 미소된장 고추무침 200g",
                    "description": "고소한 양념 때문에 손이 자꾸 가요",
                    "originPrice": null,
                    "salePrice": "4,500원",
                    "badge": []
                }
            ]
        }
        """.data(using: .utf8)
        completion(data, nil)
    }
    
    
}
