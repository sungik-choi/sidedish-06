//
//  AllMenu.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

struct AllMenu: Codable {
    var menuType: String
    var menuTypeTitle: String
    var data: [Menu]
}

struct Menu: Codable {
    var hash: String
    var image: String
    var alt: String
    var delivery_type: [String]
    var title: String
    var description: String
    var originPrice: String?
    var salePrice: String
    var badge: [String]?
}


