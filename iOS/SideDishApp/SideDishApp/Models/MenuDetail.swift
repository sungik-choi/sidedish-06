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
    var data: MenuDetailData
}

struct MenuDetailData: Decodable {
    var top_image: String
    var thumb_images: [ThumbImage]
    var product_description: String
    var point: String
    var delivery_info: String
    var delivery_fee: String
    var prices: [Price]
    var detail_section: [DetailImage]
}

struct ThumbImage: Decodable {
    var image: String
}

struct Price: Decodable {
    var price: String
}

struct DetailImage: Decodable {
    var detailImage: String
}
