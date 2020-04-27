//
//  ContentsTitleLabel.swift
//  SideDishApp
//
//  Created by delma on 2020/04/26.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class ContentsTitleLabel: UILabel {

    override init(frame: CGRect) {
        super.init(frame: frame)
        configure()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configure()
    }

    func configureData(string: String) {
        self.text = string
    }
    
    private func configure() {
        self.textColor = .black
        self.font = UIFont.boldSystemFont(ofSize: 16)
        self.translatesAutoresizingMaskIntoConstraints = false
    }

}
