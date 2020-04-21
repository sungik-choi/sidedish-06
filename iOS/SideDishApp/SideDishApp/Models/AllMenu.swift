//
//  AllMenu.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

struct AllMenu {
    var statusCode: Int
    var body: [Menu]
}

struct Menu {
    var detail_hash: String
    var image: String
    var alt: String
    var delivery_type: [Delivery]
    var title: String
    var description: String
    var n_price: String
    var s_price: String
    var badge: [Discount]
}

struct Delivery {
    var type: String
}

struct Discount {
    var badge: String
}

