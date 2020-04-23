//
//  ImageCacheManager.swift
//  SideDishApp
//
//  Created by delma on 2020/04/23.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation
import UIKit

final class ImageCacheManager {
    static let imageCache = NSCache<NSString, UIImage>()
    
    private func setObject(key string: String, image: UIImage) {
        let cacheKey = string as NSString
        ImageCacheManager.imageCache.setObject(image, forKey: cacheKey)
    }
    
    func existCachedImage(key: String) -> UIImage? {
        if let cachedImage = ImageCacheManager.imageCache.object(forKey: key as NSString) {
            return cachedImage
        }else {
            guard let image = makeUIImage(string: key) else { return nil }
            setObject(key: key, image: image)
            return image
        }
    }
    
    private func makeUIImage(string: String) -> UIImage? {
        let url = URL(string: string)
        var data: Data?
        do {
            data = try Data(contentsOf: url!)
        } catch { //error handling
            print(error)
        }
        guard let image = data else { return nil }
        return UIImage(data: image)
    }
    
}
