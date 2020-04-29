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
    var badge: [Badge]?
    
    func hash(into hasher: inout Hasher) {
        hasher.combine(hash)
        hasher.combine(image)
        hasher.combine(alt)
        hasher.combine(delivery_type)
        hasher.combine(title)
        hasher.combine(description)
        hasher.combine(originPrice)
        hasher.combine(salePrice)
        hasher.combine(badge)
    }
    
    static func == (lhs: Menu, rhs: Menu) -> Bool {
        return lhs.hash == rhs.hash
            && lhs.image == rhs.image
            && lhs.alt == rhs.alt
            && lhs.delivery_type == rhs.delivery_type
            && lhs.title == rhs.title
            && lhs.description == rhs.description
            && lhs.originPrice == rhs.originPrice
            && lhs.salePrice == rhs.salePrice
            && lhs.badge == rhs.badge
    }
}

struct Badge: Codable, Hashable {
    var badgeName: String
    var badgeHexa: String
}

