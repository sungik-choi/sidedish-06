//
//  ContentsTitleLabel.swift
//  SideDishApp
//
//  Created by delma on 2020/04/26.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class ContentsTitleLabel: UILabel {
    
    convenience init(text: String) {
        self.init()
        configure(text: text)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    private func configure(text: String) {
        self.textColor = .black
        self.font = UIFont.boldSystemFont(ofSize: 16)
        self.text = text
        self.translatesAutoresizingMaskIntoConstraints = false
    }
    
}
