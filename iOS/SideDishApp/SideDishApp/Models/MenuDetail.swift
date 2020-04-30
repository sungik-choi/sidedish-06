//
//  Menu.swift
//  SideDishApp
//
//  Created by delma on 2020/04/21.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

struct MenuDetail: Decodable {
    var hash: String
    var title: String
    var top_image: String
    var thumb_images: [String]
    var description: String
    var point: String
    var delivery_info: String
    var delivery_fee: String
    var originPrice: String?
    var salePrice: String
    var detail_section: [String]
}

