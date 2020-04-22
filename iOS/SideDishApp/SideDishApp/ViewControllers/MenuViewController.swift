//
//  MenuViewController.swift
//  SideDishApp
//
//  Created by delma on 2020/04/20.
//  Copyright © 2020 delma. All rights reserved.
//

import UIKit

class MenuViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    static let networkManager = NetworkManager()
    private let sections:[String] = ["국","찌개","반찬"]
    @IBOutlet var tableView: MenuTableView!
    
    private var allMenu: AllMenu?
    
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
        cell.configureCellData(title: allMenu?.body[indexPath.row].title ?? "", description: allMenu?.body[indexPath.row].description ?? "", originPrice: allMenu?.body[indexPath.row].n_price ?? "", newPrice: allMenu?.body[indexPath.row].s_price ?? "", badges: allMenu?.body[indexPath.row].badge ?? [])
        return cell
    }

    private func configureTableView() {
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(MenuTableViewCell.self, forCellReuseIdentifier: "MenuTableViewCell")
        tableView.register(MenuHeaderView.self, forHeaderFooterViewReuseIdentifier: "MenuHeaderView")
    }
    
    private func configureUsecase() {
        NetworkUseCase.makeAllMenu(with: MenuViewController.networkManager) { data in
            self.allMenu = data
            DispatchQueue.main.async {
                self.tableView.reloadData()
            }
        }
    }
}

