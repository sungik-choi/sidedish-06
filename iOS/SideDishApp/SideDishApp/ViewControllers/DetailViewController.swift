//
//  DetailViewController.swift
//  SideDishApp
//
//  Created by delma on 2020/04/26.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {
    
    //MARK:- properties
    var menuHash = ""
    private var menuDetail: MenuDetail?
    private var width: CGFloat = 0
    private var height: CGFloat = 0
    
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
        scrollView.contentSize = self.view.bounds.size
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
        return button
    }()
    
    //MARK:- functions
    override func viewDidLoad() {
        super.viewDidLoad()

        addSubViews()
        configureScrollViewConstraints()
        configureStackViewConstraints()
        configureElementsConstraints()
        configureUsecase(menuHash)
        width = self.view.frame.width
        height = self.view.frame.height

        self.navigationController?.setNavigationBarHidden(false, animated: false)
        orderButton.addTarget(self, action: #selector(pressOrderButton(button:)), for: .touchUpInside)
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
        self.view.addSubview(orderButton)
    }
    
    private func configureScrollViewConstraints() {
        let constraints = [
            wholeScrollView.topAnchor.constraint(equalTo: self.view.topAnchor),
            wholeScrollView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            wholeScrollView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            wholeScrollView.bottomAnchor.constraint(equalTo: orderButton.topAnchor),
            
            wholeScrollView.contentLayoutGuide.topAnchor.constraint(equalTo: self.view.topAnchor),
            wholeScrollView.contentLayoutGuide.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            wholeScrollView.contentLayoutGuide.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            
            thumbnailScrollView.topAnchor.constraint(equalTo: wholeScrollView.topAnchor),
            thumbnailScrollView.leadingAnchor.constraint(equalTo: wholeScrollView.leadingAnchor),
            thumbnailScrollView.trailingAnchor.constraint(equalTo: wholeScrollView.trailingAnchor),
            thumbnailScrollView.heightAnchor.constraint(equalToConstant: self.view.frame.size.height/3),
            
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
            detailImageStack.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            detailImageStack.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            detailImageStack.bottomAnchor.constraint(equalTo: wholeScrollView.bottomAnchor),
        ]
        constraints.forEach { $0.isActive = true }
    }
    
    private func configureElementsConstraints() {
        let constraints = [
            menuTitle.topAnchor.constraint(equalTo: thumbnailScrollView.bottomAnchor, constant: 30),
            menuTitle.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 20),
            
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
            salePrice.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -20),
            
            originalPrice.bottomAnchor.constraint(equalTo: salePrice.bottomAnchor),
            originalPrice.trailingAnchor.constraint(equalTo: salePrice.leadingAnchor, constant: -4),
            
            savedMoney.topAnchor.constraint(equalTo: savedMoneyTitle.topAnchor),
            savedMoney.leadingAnchor.constraint(equalTo: deliveryInfo.leadingAnchor, constant: 4),
            
            deliveryMoney.topAnchor.constraint(equalTo: deliveryMoneyTitle.topAnchor),
            deliveryMoney.leadingAnchor.constraint(equalTo: deliveryInfo.leadingAnchor, constant: 4),
            
            deliveryInfo.topAnchor.constraint(equalTo: deliveryInfoTitle.topAnchor),
            deliveryInfo.leadingAnchor.constraint(equalTo: deliveryInfoTitle.trailingAnchor, constant: 10),
            deliveryInfo.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -20),
            
            orderButton.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            orderButton.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            orderButton.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant: -20),
            orderButton.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 0.08)
        ]
        constraints.forEach { $0.isActive = true }
    }
    
    private func configureUsecase(_ menuHash: String) {
        NetworkUseCase.makeMenuDetail(with: MenuViewController.networkManager, menuHash: menuHash) { data in
            self.menuDetail = data
            DispatchQueue.main.async {
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
        loadImage(urlStrings: menuDetail.thumb_images, to: self.thumbnailStackView, isThumbnail: true)
        loadImage(urlStrings: menuDetail.detail_section, to: self.detailImageStack, isThumbnail: false)
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
        if isThumbnail {
            subView.heightAnchor.constraint(equalToConstant: height/3).isActive = true
        }else {
            subView.heightAnchor.constraint(equalToConstant: height/3 + 300.0).isActive = true
        }
        subView.widthAnchor.constraint(equalToConstant: width).isActive = true
        stackView.addArrangedSubview(subView)
    }
    
    @objc func pressOrderButton(button: UIButton) {
        let noStockAlert = makeAlert(title: "재고없음", message: "재고가 없어 주문이 취소되었습니다", insideAlert: nil) {
            self.navigationController?.popViewController(animated: true)
        }
        let defaultAlert = makeAlert(title: "주문", message: "주문하시겠습니까?", insideAlert: noStockAlert) { }
        present(defaultAlert, animated: true)
    }
    
    private func makeAlert(title: String, message: String, insideAlert: UIAlertController?, handler: @escaping () -> ()) -> UIAlertController {
        let defaultAlert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let ok = UIAlertAction(title: "네", style: .default) { ok in
            if let alert = insideAlert { self.present(alert, animated: true) }
            handler()
        }
        let cancel = UIAlertAction(title: "아니오", style: .cancel)
        
        defaultAlert.addAction(ok)
        defaultAlert.addAction(cancel)
        return defaultAlert
    }
}
