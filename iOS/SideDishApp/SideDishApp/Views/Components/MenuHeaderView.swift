//
//  MenuHeaderView.swift
//  SideDishApp
//
//  Created by delma on 2020/04/22.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class MenuHeaderView: UITableViewHeaderFooterView {
    
    // MARK:- initializers
    override init(reuseIdentifier: String?) {
        super.init(reuseIdentifier: reuseIdentifier)
        configure()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configure()
    }
    
    // MARK:- properites
    private let headerBadge: UIButton = {
        let button = UIButton()
        button.layer.borderWidth = 1
        button.layer.borderColor = UIColor.lightGray.cgColor
        button.isEnabled = false
        button.titleEdgeInsets = UIEdgeInsets(top: 0, left: 4, bottom: 0, right: 4)
        button.setTitleColor(.lightGray, for: .normal)
        button.titleLabel?.font = UIFont(name: "Helvetica Neue", size: 8)
        return button
    }()
    
    private let headerTitle: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 18)
        return label
    }()
    
    //MARK:- functions
    func configureHeaderData(badge: String, title: String) {
        headerBadge.setTitle(badge, for: .normal)
        headerTitle.text = title
    }
    
    private func configure() {
        self.contentView.backgroundColor = .white
        self.contentView.addSubview(headerBadge)
        self.contentView.addSubview(headerTitle)
        
        headerBadge.translatesAutoresizingMaskIntoConstraints = false
        headerBadge.topAnchor.constraint(equalTo: self.contentView.topAnchor, constant: 10).isActive = true
        headerBadge.centerXAnchor.constraint(equalTo: self.contentView.centerXAnchor, constant: 0).isActive = true
        headerBadge.widthAnchor.constraint(equalTo: self.contentView.widthAnchor, multiplier: 0.1).isActive = true
        
        headerTitle.translatesAutoresizingMaskIntoConstraints = false
        headerTitle.topAnchor.constraint(equalTo: headerBadge.bottomAnchor, constant: 5).isActive = true
        headerTitle.centerXAnchor.constraint(equalTo: self.contentView.centerXAnchor, constant: 0).isActive = true
        
    }
}
