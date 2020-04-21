//
//  NetworkUseCase.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

struct NetworkUseCase {
    
    static func makeAllMenu(with manager: NetworkManager, completed: @escaping (AllMenu) -> ()) {
        manager.getResource(url: EndPoints.AllMenu!, methodType: .get, body: nil) { result in
            switch result {
            case .success(let data):
                guard let decodedData = try? JSONDecoder().decode(AllMenu.self, from: data) else { return }
                completed(decodedData)
            case .failure(let error):
                //error handling 필요
                print(error.localizedDescription)
            }
        }
        
    }
    
}
