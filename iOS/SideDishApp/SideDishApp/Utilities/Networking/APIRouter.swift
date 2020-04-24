//
//  APIRouter.swift
//  SideDishApp
//
//  Created by delma on 2020/04/24.
//  Copyright © 2020 delma. All rights reserved.
//

import Foundation

enum APIRouter: String, CaseIterable {
    case main = "main"
    case soup = "soup"
    case side = "side"
    
    init(_ index: Int) {
        self = APIRouter.allCases[index]
    }
    
}
