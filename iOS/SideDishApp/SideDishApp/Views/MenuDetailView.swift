//
//  MenuDetailView.swift
//  SideDishApp
//
//  Created by delma on 2020/05/02.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class MenuDetailView: UIView {
    
    private let priceTitle = ContentsTitleLabel(text: "가격")
    private let savedMoneyTitle = ContentsTitleLabel(text: "적립금")
    private let deliveryMoneyTitle = ContentsTitleLabel(text: "배송비")
    private let deliveryInfoTitle = ContentsTitleLabel(text: "배송정보")
    
    private let originalPrice = DescriptionLabel()
    private let savedMoney = DescriptionLabel()
    private let deliveryMoney = DescriptionLabel()
    private let deliveryInfo = DescriptionLabel()
    
    private lazy var wholeScrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        scrollView.scrollsToTop = true
        scrollView.showsVerticalScrollIndicator = true
        scrollView.contentSize = self.bounds.size
        scrollView.autoresizingMask = .flexibleHeight
        scrollView.showsVerticalScrollIndicator = true
        return scrollView
    }()
    
    private let thumbnailScrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        scrollView.showsHorizontalScrollIndicator = true
        scrollView.showsVerticalScrollIndicator = false
        scrollView.isPagingEnabled = true
        scrollView.alwaysBounceVertical = false
        return scrollView
    }()
    
    private let thumbnailStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 0
        stackView.distribution = .fillEqually
        stackView.alignment = .center
        stackView.translatesAutoresizingMaskIntoConstraints = false
        return stackView
    }()
    
    private let menuTitle: UILabel = {
        let label = UILabel()
        label.textColor = .black
        label.font = UIFont.boldSystemFont(ofSize: 19.0)
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let menuDescription: UILabel = {
        let label = UILabel()
        label.textColor = .black
        label.font = UIFont.systemFont(ofSize: 15.0)
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let salePrice: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 18)
        label.textColor = #colorLiteral(red: 0.4551282529, green: 0.9278236041, blue: 0.8855857598, alpha: 1)
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let detailImageStack: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.spacing = 0
        stackView.distribution = .fillEqually
        stackView.alignment = .center
        stackView.translatesAutoresizingMaskIntoConstraints = false
        return stackView
    }()
    
    private let orderButton: UIButton = {
        let button = UIButton()
        button.setTitle("주문하기", for: .normal)
        button.setTitleColor(.white, for: .normal)
        button.titleLabel?.font = UIFont.boldSystemFont(ofSize: 20)
        button.titleLabel?.textAlignment = .center
        button.backgroundColor = #colorLiteral(red: 0.4551282529, green: 0.9278236041, blue: 0.8855857598, alpha: 1)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.layer.cornerRadius = 20.0
        return button
    }()
    
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubViews()
        configureScrollViewConstraints()
        configureStackViewConstraints()
        configureElementsConstraints()
        
        orderButton.addTarget(self, action: #selector(postPressedNoti(button:)), for: .touchUpInside)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    func configureData(_ menuDetail: MenuDetail) {
        menuTitle.text = menuDetail.title
        menuDescription.text = menuDetail.description
        originalPrice.text = menuDetail.originPrice
        salePrice.text = menuDetail.salePrice
        savedMoney.text = menuDetail.point
        deliveryMoney.text = menuDetail.delivery_fee
        deliveryInfo.text = menuDetail.delivery_info
        loadImage(urlStrings: menuDetail.thumb_images, to: self.thumbnailStackView, isThumbnail: true)
        loadImage(urlStrings: menuDetail.detail_section, to: self.detailImageStack, isThumbnail: false)
    }
    
    private func addSubViews() {
        self.addSubview(wholeScrollView)
        
        wholeScrollView.addSubview(thumbnailScrollView)
        thumbnailScrollView.addSubview(thumbnailStackView)
        
        wholeScrollView.addSubview(menuTitle)
        wholeScrollView.addSubview(menuDescription)
        
        wholeScrollView.addSubview(priceTitle)
        wholeScrollView.addSubview(savedMoneyTitle)
        wholeScrollView.addSubview(deliveryMoneyTitle)
        wholeScrollView.addSubview(deliveryInfoTitle)
        
        wholeScrollView.addSubview(originalPrice)
        wholeScrollView.addSubview(savedMoney)
        wholeScrollView.addSubview(deliveryMoney)
        wholeScrollView.addSubview(deliveryInfo)
        wholeScrollView.addSubview(salePrice)
        
        wholeScrollView.addSubview(detailImageStack)
        self.addSubview(orderButton)
    }
    
    private func configureScrollViewConstraints() {
        let constraints = [
            wholeScrollView.topAnchor.constraint(equalTo: self.topAnchor),
            wholeScrollView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            wholeScrollView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            
            wholeScrollView.contentLayoutGuide.topAnchor.constraint(equalTo: self.topAnchor),
            wholeScrollView.contentLayoutGuide.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            wholeScrollView.contentLayoutGuide.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            
            thumbnailScrollView.topAnchor.constraint(equalTo: wholeScrollView.topAnchor),
            thumbnailScrollView.leadingAnchor.constraint(equalTo: wholeScrollView.leadingAnchor),
            thumbnailScrollView.trailingAnchor.constraint(equalTo: wholeScrollView.trailingAnchor),
            thumbnailScrollView.heightAnchor.constraint(equalTo: wholeScrollView.heightAnchor, multiplier: 0.3),
        ]
        constraints.forEach { $0.isActive = true }
    }
    
    private func configureStackViewConstraints() {
        let constraints = [
            thumbnailStackView.topAnchor.constraint(equalTo: thumbnailScrollView.topAnchor),
            thumbnailStackView.leadingAnchor.constraint(equalTo: thumbnailScrollView.leadingAnchor),
            thumbnailStackView.trailingAnchor.constraint(equalTo: thumbnailScrollView.trailingAnchor),
            thumbnailStackView.bottomAnchor.constraint(equalTo: thumbnailScrollView.bottomAnchor),
            thumbnailStackView.heightAnchor.constraint(equalTo: thumbnailScrollView.heightAnchor),
            
            detailImageStack.topAnchor.constraint(equalTo: deliveryInfoTitle.bottomAnchor, constant: 10),
            detailImageStack.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            detailImageStack.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            detailImageStack.bottomAnchor.constraint(equalTo: wholeScrollView.bottomAnchor),
        ]
        constraints.forEach { $0.isActive = true }
    }
    
    private func configureElementsConstraints() {
        let constraints = [
            menuTitle.topAnchor.constraint(equalTo: thumbnailScrollView.bottomAnchor, constant: 30),
            menuTitle.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 20),
            
            menuDescription.topAnchor.constraint(equalTo: menuTitle.bottomAnchor, constant: 4),
            menuDescription.leadingAnchor.constraint(equalTo: menuTitle.leadingAnchor),
            
            priceTitle.topAnchor.constraint(equalTo: menuDescription.bottomAnchor, constant: 15),
            priceTitle.leadingAnchor.constraint(equalTo: menuDescription.leadingAnchor),
            
            savedMoneyTitle.topAnchor.constraint(equalTo: priceTitle.bottomAnchor, constant: 4),
            savedMoneyTitle.leadingAnchor.constraint(equalTo: menuDescription.leadingAnchor),
            
            deliveryMoneyTitle.topAnchor.constraint(equalTo: savedMoneyTitle.bottomAnchor, constant: 4),
            deliveryMoneyTitle.leadingAnchor.constraint(equalTo: menuDescription.leadingAnchor),
            
            deliveryInfoTitle.topAnchor.constraint(equalTo: deliveryMoneyTitle.bottomAnchor, constant: 4),
            deliveryInfoTitle.leadingAnchor.constraint(equalTo: menuDescription.leadingAnchor),
            
            salePrice.topAnchor.constraint(equalTo: priceTitle.topAnchor),
            salePrice.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -20),
            
            originalPrice.bottomAnchor.constraint(equalTo: salePrice.bottomAnchor),
            originalPrice.trailingAnchor.constraint(equalTo: salePrice.leadingAnchor, constant: -4),
            
            savedMoney.topAnchor.constraint(equalTo: savedMoneyTitle.topAnchor),
            savedMoney.leadingAnchor.constraint(equalTo: deliveryInfo.leadingAnchor, constant: 4),
            
            deliveryMoney.topAnchor.constraint(equalTo: deliveryMoneyTitle.topAnchor),
            deliveryMoney.leadingAnchor.constraint(equalTo: deliveryInfo.leadingAnchor, constant: 4),
            
            deliveryInfo.topAnchor.constraint(equalTo: deliveryInfoTitle.topAnchor),
            deliveryInfo.leadingAnchor.constraint(equalTo: deliveryInfoTitle.trailingAnchor, constant: 10),
            deliveryInfo.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -20),
            
            orderButton.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            orderButton.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -20),
            orderButton.heightAnchor.constraint(equalTo: self.heightAnchor, multiplier: 0.08),
            orderButton.widthAnchor.constraint(equalTo: self.widthAnchor, multiplier: 0.9)
        ]
        constraints.forEach { $0.isActive = true }
    }
    
    private func loadImage(urlStrings: [String], to stackView: UIStackView, isThumbnail: Bool) {
        urlStrings.forEach { url in
            ImageLoader.shared.load(urlString: url) { result in
                switch result {
                case .success(let data):
                    DispatchQueue.main.async {
                        guard let image = UIImage(data: data) else { return }
                        let imageView = self.makeImageView(image: image)
                        self.addArrangedSubview(to: stackView, subView: imageView, isThumbnail: isThumbnail)
                    }
                case .failure(let error):
                    print(error)
                }
            }
        }
    }
    
    private func makeImageView(image: UIImage) -> UIImageView {
        let imageView = UIImageView()
        imageView.image = image
        return imageView
    }
    
    private func addArrangedSubview(to stackView: UIStackView, subView: UIImageView, isThumbnail: Bool) {
        var height = self.frame.height / 3
        if !isThumbnail { height = height + 250.0 }
        subView.heightAnchor.constraint(equalToConstant: height).isActive = true
        subView.widthAnchor.constraint(equalToConstant: self.frame.width).isActive = true
        stackView.addArrangedSubview(subView)
    }
    
    @objc func postPressedNoti(button: UIButton) {
        NotificationCenter.default.post(name: .pressOrderButton, object: button)
    }
}
