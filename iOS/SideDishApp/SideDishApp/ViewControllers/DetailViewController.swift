//
//  DetailViewController.swift
//  SideDishApp
//
//  Created by delma on 2020/04/26.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController, UIScrollViewDelegate {
    
    //MARK:- properties
    var menuHash = ""
    private var menuDetail: MenuDetail?
    
    private let wholeScrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        scrollView.scrollsToTop = true
        scrollView.showsVerticalScrollIndicator = true
        return scrollView
    }()
    
    private let thumbnailScrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        scrollView.showsHorizontalScrollIndicator = true
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
        label.font = UIFont.boldSystemFont(ofSize: 17.0)
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
    
    private let pageControl = UIPageControl()
    
    private let priceTitle = ContentsTitleLabel(text: "가격")
    private let savedMoneyTitle = ContentsTitleLabel(text: "적립금")
    private let deliveryMoneyTitle = ContentsTitleLabel(text: "배송비")
    private let deliveryInfoTitle = ContentsTitleLabel(text: "배송정보")
    
    private let originalPrice = DescriptionLabel()
    private let savedMoney = DescriptionLabel()
    private let deliveryMoney = DescriptionLabel()
    private let deliveryInfo = DescriptionLabel()
    
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
        button.titleLabel?.font = UIFont.boldSystemFont(ofSize: 18)
        button.titleLabel?.textAlignment = .center
        button.backgroundColor = #colorLiteral(red: 0.4551282529, green: 0.9278236041, blue: 0.8855857598, alpha: 1)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    //MARK:- functions
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController?.navigationBar.isHidden = false
        
        addSubViews()
        configureConstraints()
        thumbnailScrollView.delegate = self
        
        configureUsecase(menuHash)
        
    }
    
    func makeImageView(image: UIImage) -> UIImageView {
        let imageView = UIImageView()
        imageView.image = image
        return imageView
    }
    
    private func addSubViews() {
        self.view.addSubview(wholeScrollView)
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
        wholeScrollView.addSubview(orderButton)
    }
    
    private func configureConstraints() {
        let constraints = [
            wholeScrollView.topAnchor.constraint(equalTo: self.view.topAnchor),
            wholeScrollView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            wholeScrollView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            wholeScrollView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            
            thumbnailScrollView.topAnchor.constraint(equalTo: self.view.topAnchor),
            thumbnailScrollView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            thumbnailScrollView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            thumbnailScrollView.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 0.3),
            
            thumbnailStackView.topAnchor.constraint(equalTo: self.view.topAnchor),
            thumbnailStackView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            thumbnailStackView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            thumbnailStackView.bottomAnchor.constraint(equalTo: thumbnailScrollView.bottomAnchor),
            
            menuTitle.topAnchor.constraint(equalTo: thumbnailScrollView.bottomAnchor, constant: 8),
            menuTitle.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 8),
            
            menuDescription.topAnchor.constraint(equalTo: menuTitle.bottomAnchor, constant: 4),
            menuDescription.leadingAnchor.constraint(equalTo: menuTitle.leadingAnchor),
            
            priceTitle.topAnchor.constraint(equalTo: menuDescription.bottomAnchor, constant: 8),
            priceTitle.leadingAnchor.constraint(equalTo: menuDescription.leadingAnchor),
            
            savedMoneyTitle.topAnchor.constraint(equalTo: priceTitle.bottomAnchor, constant: 4),
            savedMoneyTitle.leadingAnchor.constraint(equalTo: menuDescription.leadingAnchor),
            
            deliveryMoneyTitle.topAnchor.constraint(equalTo: savedMoneyTitle.bottomAnchor, constant: 4),
            deliveryMoneyTitle.leadingAnchor.constraint(equalTo: menuDescription.leadingAnchor),
            
            deliveryInfoTitle.topAnchor.constraint(equalTo: deliveryMoneyTitle.bottomAnchor, constant: 4),
            deliveryInfoTitle.leadingAnchor.constraint(equalTo: menuDescription.leadingAnchor),
            
            salePrice.topAnchor.constraint(equalTo: priceTitle.topAnchor),
            salePrice.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -8),
            
            originalPrice.topAnchor.constraint(equalTo: salePrice.topAnchor),
            originalPrice.trailingAnchor.constraint(equalTo: salePrice.leadingAnchor, constant: -4),
            
            savedMoney.topAnchor.constraint(equalTo: savedMoneyTitle.topAnchor),
            savedMoney.leadingAnchor.constraint(equalTo: savedMoneyTitle.trailingAnchor, constant: 4),
            
            deliveryMoney.topAnchor.constraint(equalTo: deliveryMoneyTitle.topAnchor),
            deliveryMoney.leadingAnchor.constraint(equalTo: deliveryMoneyTitle.trailingAnchor, constant: 4),
            
            deliveryInfo.topAnchor.constraint(equalTo: deliveryInfoTitle.topAnchor),
            deliveryInfo.leadingAnchor.constraint(equalTo: deliveryInfoTitle.trailingAnchor, constant: 4),
            
            detailImageStack.topAnchor.constraint(equalTo: deliveryInfoTitle.bottomAnchor, constant: 20),
            detailImageStack.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            detailImageStack.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            detailImageStack.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            
            orderButton.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            orderButton.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            orderButton.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant: -20),
            orderButton.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 0.1)
            
        ]
        constraints.forEach { $0.isActive = true }
    }
    
    private func configureUsecase(_ menuHash: String) {
        NetworkUseCase.makeMenuDetail(with: MenuViewController.networkManager, menuHash: "") { data in
            self.menuDetail = data
            DispatchQueue.main.async {
                self.pageControl.numberOfPages = self.menuDetail!.thumb_images.count
            guard let menuDetail = self.menuDetail else { return }
            self.configureData(menuDetail)
            }
            
        }
    }
    
    private func configureData(_ menuDetail: MenuDetail) {
        menuTitle.text = menuDetail.title
        menuDescription.text = menuDetail.description
        originalPrice.text = menuDetail.originPrice
        salePrice.text = menuDetail.salePrice
        savedMoney.text = menuDetail.point
        deliveryMoney.text = menuDetail.delivery_fee
        deliveryInfo.text = menuDetail.delivery_info
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        pageControl.currentPage = Int(floor(thumbnailScrollView.contentOffset.x / UIScreen.main.bounds.width))
    }
    
    
}
