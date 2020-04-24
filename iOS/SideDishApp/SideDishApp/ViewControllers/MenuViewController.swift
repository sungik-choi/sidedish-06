//
//  MenuViewController.swift
//  SideDishApp
//
//  Created by delma on 2020/04/20.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit
import Toaster

class MenuViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    // MARK: - properties
    static let networkManager = NetworkManager()
    static let imageCacheManager = ImageCacheManager()
    private let sections:[String] = ["국","찌개","반찬"]
    private var allMenu: AllMenu?
    @IBOutlet var tableView: MenuTableView!
    
    // MARK: - functions
    override func viewDidLoad() {
        super.viewDidLoad()
        configureTableView()
        configureUsecase()
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        //server에서 받아온 데이터로 변경하기
        return sections.count
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: "MenuHeaderView") as! MenuHeaderView
        header.configureHeaderData(badge: "국.찌개", title: "김이 모락모락 국.찌개")
        return header
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 80
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return allMenu?.body.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MenuTableViewCell", for: indexPath) as! MenuTableViewCell
        cell.configureCellData(menu: allMenu?.body[indexPath.row])
        return cell
    }
    
    func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
        let gesture = SectionHeaderTapGestureRecognizer(target: self, action: #selector(touchedSectionHeader(recognizer:)))
        gesture.index = section
        
        view.addGestureRecognizer(gesture)
    }
    
    @objc func touchedSectionHeader(recognizer: SectionHeaderTapGestureRecognizer) {
        guard let index = recognizer.index else { return }
        let toaster = Toast(text: "\(sections[index]) \(tableView.numberOfRows(inSection: index))개 재고 보유")
        toaster.show()
    }
    
    // MARK: - private functions
    private func configureTableView() {
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(MenuTableViewCell.self, forCellReuseIdentifier: "MenuTableViewCell")
        tableView.register(MenuHeaderView.self, forHeaderFooterViewReuseIdentifier: "MenuHeaderView")
    }
    
    private func configureUsecase() {
        NetworkUseCase.makeMenu(with: MenuViewController.networkManager, url: EndPoints.MainMenu) { data in
            self.allMenu = data
            DispatchQueue.main.async {
                self.tableView.reloadData()
            }
        }
    }
}

