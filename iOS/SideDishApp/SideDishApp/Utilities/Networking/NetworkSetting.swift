//
//  APISetting.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

enum HTTPMethod: String {
    case get = "GET"
    case post = "POST"
    case put = "PUT"
    case delete = "DELETE"
}

enum EndPoints: String, CaseIterable {
    case main = "main"
    case soup = "soup"
    case side = "side"
    
    static let BaseURL = "http://52.79.117.147/api/menu/"
    static let MenuDetail = "http://52.79.117.147/api/detail/"
}

enum MockEndPoints: String, CaseIterable {
    case side = "http://www.mocky.io/v2/5ea401924f00003729d9fa25"
    case main = "http://www.mocky.io/v2/5ea4028a4f0000bd54d9fa27"
    case soup = "http://www.mocky.io/v2/5ea402a24f0000cf6bd9fa28"
}
