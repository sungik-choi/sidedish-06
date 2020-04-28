//
//  DescriptionLabel.swift
//  SideDishApp
//
//  Created by delma on 2020/04/26.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class DescriptionLabel: UILabel {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        configure()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configure()
    }

    func configureText(string: String) {
        self.text = string
    }
    
    private func configure() {
        self.textColor = .lightGray
        self.font = UIFont.systemFont(ofSize: 14.0)
        self.translatesAutoresizingMaskIntoConstraints = false
    }
    
}
