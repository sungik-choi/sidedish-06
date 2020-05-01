//
//  ImageLoader.swift
//  SideDishApp
//
//  Created by delma on 2020/04/25.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

protocol ImageLoadable: class {
    typealias Handler = (Result<Data, Error>) -> Void
    func load(urlString: String, completion: @escaping Handler)
}

class ImageLoader {
    static let shared: ImageLoadable = ImageLoader()
}

extension ImageLoader: ImageLoadable {
    func load(urlString: String, completion: @escaping Handler) {
        if let cachedData = ImageCache.shared.find(urlString) {
                completion(.success(cachedData))
        } else {
            requestImage(urlString: urlString) { result in
                switch result {
                case .success(let data):
                    ImageCache.shared.save(urlString, content: data)
                    completion(.success(data))
                case .failure(let error):
                    completion(.failure(error))
                }
            }
        }
    }
}

extension ImageLoader {
    func requestImage(urlString: String, completion: @escaping Handler) {
        NetworkUseCase.makeImage(with: MenuViewController.networkManager, urlString: urlString) { completion(.success($0)) }
    }
}
