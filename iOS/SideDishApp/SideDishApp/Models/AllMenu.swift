//
//  AllMenu.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

struct AllMenu: Codable, Hashable {
    var menuIndex: Int
    var menuType: String
    var menuTypeTitle: String
    var data: [Menu]
    
    func hash(into hasher: inout Hasher) {
        hasher.combine(menuType)
        hasher.combine(menuTypeTitle)
        hasher.combine(data)
    }
    
    static func == (lhs: AllMenu, rhs: AllMenu) -> Bool {
        return lhs.menuType == rhs.menuType
            && lhs.menuTypeTitle == rhs.menuTypeTitle
            && lhs.data == rhs.data
    }
    
}

struct Menu: Codable, Hashable {
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


