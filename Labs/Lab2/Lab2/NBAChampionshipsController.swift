//
//  DetailViewController.swift
//  countries
//
//  Created by Isaac Sheets on 2/2/20.
//  Copyright © 2020 Isaac Sheets. All rights reserved.
//

import UIKit

class NBAChampionshipsController: UITableViewController {
    
    var nbaData = NBAChampsController()
    var selectedName = 0
    var championshipsList = [String]()
    
    override func viewWillAppear(_ animated: Bool) {
        championshipsList = nbaData.getChampionships(idx: selectedName)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        //self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return championshipsList.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ChampionshipCell", for: indexPath)
        
        // Configure the cell...
        cell.textLabel?.text = championshipsList[indexPath.row]

        return cell
    }

    
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    

    
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            //Notify data model
            championshipsList.remove(at: indexPath.row)
            //Update instance
            nbaData.deleteChamp(dataIdx: selectedName, champIdx: indexPath.row)
            //Update table
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }
    }
    

    
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        //get idx of rows
        let fromRow = fromIndexPath.row
        let toRow = to.row
        
        //get name of country being moved
        let moveChampionship = championshipsList[fromRow]
        
        //swap in array
        championshipsList.swapAt(fromRow, toRow)
        
        //swap in data controller
        nbaData.deleteChamp(dataIdx: selectedName, champIdx: fromRow)
        nbaData.addChampionship(dataIdx: selectedName, newChamp: moveChampionship, champIdx: toRow)
    }
    

    
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    
    
    //called automatically
    @IBAction func unwindSegue(_ segue: UIStoryboardSegue) {
        //check the id of segue
        if segue.identifier == "save" {
            //downcast to access members
            let source = segue.source as! AddChampionshipController

            //double check to make sure new country name is not empty
            if source.addedChampionship.isEmpty == false {
                //add new country to data model (notify of changes)
                nbaData.addChampionship(dataIdx: selectedName, newChamp: source.addedChampionship, champIdx: championshipsList.count)
                //add to working copy
                championshipsList.append(source.addedChampionship)
                //update table view based on data changes
                tableView.reloadData()
            }
        }
    }
}
