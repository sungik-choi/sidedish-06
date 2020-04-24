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

struct MockNetworkSuccessSideStub: NetworkManageable {
    
    func getResource(url: String, methodType: HTTPMethod, body: Data?, completion: @escaping Self.Handler) {
        let data = """
        {
            "menuIndex": 0,
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
        """.data(using: .utf8)!
        completion(.success(data))
    }
}

struct MockNetworkSuccessMainStub: NetworkManageable {
    
    func getResource(url: String, methodType: HTTPMethod, body: Data?, completion: @escaping Self.Handler) {
        let data = """
        {
            "menuIndex": 1,
            "menuType": "밥과 함께",
            "menuTypeTitle": "언제 먹어도 든든한 반찬",
            "data": [
                {
                    "hash": "HBDEF",
                    "image": "http://public.codesquad.kr/jk/storeapp/data/2d3f99a9a35601f4e98837bc4d39b2c8.jpg",
                    "alt": "[미노리키친] 규동 250g",
                    "delivery_type": [
                        "새벽배송",
                        "전국택배"
                    ],
                    "title": "[미노리키친] 규동 250g",
                    "description": "일본인의 소울푸드! 한국인도 좋아하는 소고기덮밥",
                    "originPrice": "6,500원",
                    "salePrice": "7,000원",
                    "badge": [
                        "이벤트특가"
                    ]
                },
                {
                    "hash": "HEDFB",
                    "image": "http://public.codesquad.kr/jk/storeapp/data/bc3b777115e8377a48c7bd762fe5fdc9.jpg",
                    "alt": "[빅마마의밥친구] 비빔오징어 150g",
                    "delivery_type": [
                        "새벽배송",
                        "전국택배"
                    ],
                    "title": "[빅마마의밥친구] 비빔오징어 150g",
                    "description": "달콤한 신야초발효액이 포인트!",
                    "originPrice": null,
                    "salePrice": "5,000원"
                }
            ]
        }
        """.data(using: .utf8)!
        completion(.success(data))
    }
}

struct MockNetworkSuccessSoupStub: NetworkManageable {
    
    func getResource(url: String, methodType: HTTPMethod, body: Data?, completion: @escaping Self.Handler) {
        let data = """
        {
            "menuIndex": 2,
            "menuType": "국.찌개",
            "menuTypeTitle": "김이 모락모락 국, 찌개",
            "data": [
                {
                    "hash": "H72C3",
                    "image": "http://public.codesquad.kr/jk/storeapp/data/d1fccf125f0a78113d0e06cb888f2e74.jpg",
                    "alt": "[수하동] 특곰탕 850g",
                    "delivery_type": [
                        "새벽배송",
                        "전국택배"
                    ],
                    "title": "[수하동] 특곰탕 850g",
                    "description": "100% 한우양지로 끓여낸 70년전통의 서울식곰탕",
                    "originPrice": "15,000",
                    "salePrice": "14,200원",
                    "badge": [
                        "이벤트특가"
                    ]
                },
                {
                    "hash": "HFFF9",
                    "image": "http://public.codesquad.kr/jk/storeapp/data/2416b58044d49f0d3a24256f8e76163b.jpg",
                    "alt": "[마더앤찬] 명란감자국  630ml",
                    "delivery_type": [
                        "새벽배송",
                        "전국택배"
                    ],
                    "title": "[마더앤찬] 명란감자국 630ml",
                    "description": "간간한 저염명란과 고소한 감자가 조화로운 국이에요",
                    "originPrice": "7,000",
                    "salePrice": "6,300원",
                    "badge": [
                        "론칭특가"
                    ]
                }
            ]
        }
        """.data(using: .utf8)!
        completion(.success(data))
    }
}
