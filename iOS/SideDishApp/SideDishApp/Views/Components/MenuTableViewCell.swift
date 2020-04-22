//
//  MenuTableViewCell.swift
//  SideDishApp
//
//  Created by delma on 2020/04/20.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class MenuTableViewCell: UITableViewCell {
    
    // MARK:- initialize
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: .default, reuseIdentifier: "MenuTableViewCell")
        configure()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configure()
    }
    
    // MARK:- properties
    private let menuImage: UIImageView = {
        let image = UIImageView()
        image.contentMode = .scaleAspectFit
        return image
    }()
    
    private let menuTitle: UILabel = {
        let label = UILabel()
        label.textColor = .black
        label.font = UIFont.boldSystemFont(ofSize: 17.0)
        label.textAlignment = .left
        return label
    }()
    
    private let menuDescription: UILabel = {
        let label = UILabel()
        label.textColor = .lightGray
        label.font = UIFont.systemFont(ofSize: 15.0)
        label.textAlignment = .left
        return label
    }()
    
    private let previousPrice: UILabel = {
        let label = UILabel()
        label.textColor = .lightGray
        label.font = UIFont.systemFont(ofSize: 14.0)
        let attrString = NSAttributedString(string: "", attributes: [NSAttributedString.Key.strikethroughStyle: NSUnderlineStyle.single.rawValue])
        label.attributedText = attrString
        return label
    }()
    
    private let price: UILabel = {
        let label = UILabel()
        label.textColor = #colorLiteral(red: 0.4078031928, green: 0.8572929886, blue: 0.7874471795, alpha: 1)
        label.font = UIFont.boldSystemFont(ofSize: 17.0)
        return label
    }()
    
    private lazy var badgeStack: UIStackView = {
        let stack = UIStackView()
        stack.axis = .horizontal
        stack.alignment = .fill
        stack.distribution = .fillProportionally
        stack.spacing = 5.0
        return stack
    }()
    
    //MARK:- functions
    
    override func prepareForReuse() {
        super.prepareForReuse()
        menuTitle.text = nil
        menuTitle.text = nil
        menuDescription.text = nil
        previousPrice.text = nil
        price.text = nil
        
        for view in badgeStack.subviews {
            view.removeFromSuperview()
        }
    }
    
    func configureCellData(title: String, description: String, originPrice: String?, newPrice: String, badges: [String] ) {
        menuTitle.text = title
        menuDescription.text = description
        previousPrice.text = originPrice
        price.text = newPrice
        for badge in badges {
            let label = makeBadgeLabel(badge: badge)
            addArrangedSubview(label: label)
        }
    }
    
    // MARK:- private functions
    
    private func makeBadgeLabel(badge: String) -> UILabel {
        let label = UILabel()
        label.textColor = .white
        label.backgroundColor = #colorLiteral(red: 0.7156945001, green: 0.5062116534, blue: 0.9173937183, alpha: 1)
        label.font = UIFont.systemFont(ofSize: 14.0)
        label.text = badge
        
        return label
    }
    
    private func addArrangedSubview(label: UILabel) {
        badgeStack.addArrangedSubview(label)
    }
    
    private func configure() {
        self.contentView.addSubview(menuImage)
        self.contentView.addSubview(menuTitle)
        self.contentView.addSubview(menuDescription)
        self.contentView.addSubview(previousPrice)
        self.contentView.addSubview(price)
        self.contentView.addSubview(badgeStack)
        
        configureConstraints()
    }
    
    private func configureConstraints() {
        menuImage.translatesAutoresizingMaskIntoConstraints = false
        menuImage.topAnchor.constraint(equalTo: self.contentView.topAnchor, constant: 8).isActive = true
        menuImage.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor, constant: 8).isActive = true
        menuImage.widthAnchor.constraint(equalTo: self.contentView.widthAnchor, multiplier: 0.25).isActive = true
        menuImage.heightAnchor.constraint(equalTo: menuImage.widthAnchor).isActive = true
        menuImage.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor, constant: -8).isActive = true
        
        menuTitle.translatesAutoresizingMaskIntoConstraints = false
        menuTitle.topAnchor.constraint(equalTo: menuImage.topAnchor).isActive = true
        menuTitle.leadingAnchor.constraint(equalTo: menuImage.trailingAnchor, constant: 8).isActive = true
        menuTitle.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor, constant: -8).isActive = true
        
        menuDescription.translatesAutoresizingMaskIntoConstraints = false
        menuDescription.topAnchor.constraint(equalTo: menuTitle.bottomAnchor, constant: 4).isActive = true
        menuDescription.leadingAnchor.constraint(equalTo: menuTitle.leadingAnchor).isActive = true
        menuDescription.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor, constant: -8).isActive = true
        
        previousPrice.translatesAutoresizingMaskIntoConstraints = false
        previousPrice.topAnchor.constraint(equalTo: menuDescription.bottomAnchor, constant: 6).isActive = true
        previousPrice.leadingAnchor.constraint(equalTo: menuDescription.leadingAnchor).isActive = true
        previousPrice.bottomAnchor.constraint(equalTo: price.bottomAnchor).isActive = true
        
        price.translatesAutoresizingMaskIntoConstraints = false
        price.topAnchor.constraint(equalTo: menuDescription.bottomAnchor, constant: 6).isActive = true
        price.leadingAnchor.constraint(equalTo: previousPrice.trailingAnchor, constant: 4).isActive = true
        
        badgeStack.translatesAutoresizingMaskIntoConstraints = false
        badgeStack.topAnchor.constraint(equalTo: price.bottomAnchor, constant: 4).isActive = true
        badgeStack.leadingAnchor.constraint(equalTo: previousPrice.leadingAnchor).isActive = true
        badgeStack.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor, constant: -8).isActive = true
        
    }
    
}
