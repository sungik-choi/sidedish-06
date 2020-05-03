//
//  ImageCache.swift
//  SideDishApp
//
//  Created by delma on 2020/04/24.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

struct ImageCache {
    private let cacheDirectoryURL: URL = {
        return FileManager.default.urls(for: .cachesDirectory, in: .userDomainMask)[0]
    }()

    static let shared = ImageCache()
}

extension ImageCache {
    func find(_ urlString: String) -> Data? {
        let url = cacheDirectoryURL.appendingPathComponent(urlString)
        return FileManager.default.contents(atPath: url.path)
    }
    
    func save(_ urlString: String, content data: Data) {
        let url = cacheDirectoryURL.appendingPathComponent(urlString)
        FileManager.default.createFile(atPath: url.path, contents: data, attributes: nil)
    }
}

