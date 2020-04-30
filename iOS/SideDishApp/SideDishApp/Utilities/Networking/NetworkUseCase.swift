//
//  NetworkUseCase.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

struct NetworkUseCase {
    
    static func makeMenu(with manager: NetworkManager, completed: @escaping (AllMenu) -> ()) {
        EndPoints.allCases.map { EndPoints.BaseURL + $0.rawValue }.forEach { url in
            manager.getResource(url: url , methodType: .get, body: nil) { result in
                switch result {
                case .success(let data):
                    do {
                        let decodedData = try JSONDecoder().decode(AllMenu.self, from: data)
                        completed(decodedData)
                    }catch {
                        print(error.localizedDescription)
                    }
                case .failure(let error):
                    //error handling 필요
                    print(error.localizedDescription)
                }
            }
        }
    }
    
    static func makeMenuDetail(with manager: NetworkManager, menuHash: String, completed: @escaping(MenuDetail) -> ()) {
        let url = EndPoints.MenuDetail + "/\(menuHash)"
        manager.getResource(url: url, methodType: .get, body: nil) { result in
            switch result {
            case .success(let data):
                guard let decodedData = try? JSONDecoder().decode(MenuDetail.self, from: data) else { return }
                completed(decodedData)
            case .failure(let error):
                //error handling 필요8
                print(error.localizedDescription)
            }
        }
    }
    
    static func makeImage(with manager: NetworkManager, urlString: String, completed: @escaping (Data) -> ()) {
        manager.getResource(url: urlString, methodType: .get) { result in
            switch result {
            case .success(let data):
                completed(data)
            case .failure(let error):
                print(error)
            }
        }
    }
    
    static func makeStub (with manager: NetworkManager, completed: @escaping(AllMenu) -> ()) {
        MockEndPoints.allCases.forEach { url in
            manager.getResource(url: url.rawValue , methodType: .get, body: nil) { result in
                switch result {
                case .success(let data):
                    do {
                        let decodedData = try JSONDecoder().decode(AllMenu.self, from: data)
                        completed(decodedData)
                    }catch {
                        print(error.localizedDescription)
                    }
                case .failure(let error):
                    //error handling 필요
                    print(error.localizedDescription)
                }
            }
        }
    }
    
}
