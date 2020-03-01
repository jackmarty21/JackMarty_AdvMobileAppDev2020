//
//  CollectionViewController.swift
//  Lab3
//
//  Created by Jack Marty on 3/1/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import UIKit

private let reuseIdentifier = "Cell"

class CollectionViewController: UICollectionViewController, UICollectionViewDelegateFlowLayout {

    var landscapeImages = [String]()
    
    let spacing : CGFloat = 8
    let numberItemsPerRow : CGFloat = 3
    let spacingBetweenCells : CGFloat = 12
    
    override func viewDidLoad() {
        super.viewDidLoad()

        for i in 1...10 {
            landscapeImages.append("landscape" + String(i))
        }
        
        let layout = UICollectionViewFlowLayout()
        //configure layout
        layout.sectionInset = UIEdgeInsets(top: spacing, left: spacing, bottom: spacing, right: spacing)
        layout.minimumLineSpacing = spacing
        layout.minimumInteritemSpacing = spacing
        //apply to collection view
        collectionView.collectionViewLayout = layout
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        let totalSpacing = (2 * spacing) + ((numberItemsPerRow - 1) * spacingBetweenCells)
        
        let width = (collectionView.bounds.width - totalSpacing) / numberItemsPerRow
        
        return CGSize(width: width, height: width)
        
    }

     //MARK: - Navigation

     //In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
         if segue.identifier == "showDetail" {
             //get ref to destination controller
             let detailVC = segue.destination as! DetailViewController
             //get the cell
             let indexPath = collectionView.indexPath(for: sender as! CollectionViewCell)
             
             //set properties in destination
             detailVC.title = "Landscape #\(indexPath!.row + 1)"
             detailVC.imageName = landscapeImages[indexPath!.row]
         }
    }


    // MARK: UICollectionViewDataSource

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of items
        return landscapeImages.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! CollectionViewCell
    
        // constructs UIImage and set source of imageView
        let image = UIImage(named: landscapeImages[indexPath.row])
        cell.imageView.image = image
    
        return cell
    }
    
    //set size for header
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForHeaderInSection section: Int) -> CGSize {
        return CGSize.init(width: 50, height: 40)
    }
    //set size for footer
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForFooterInSection section: Int) -> CGSize {
        return CGSize.init(width: 50, height: 40)
    }
    
    override func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {

        switch kind {

            case UICollectionView.elementKindSectionHeader:

                let headerView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "Header", for: indexPath) as! HeaderView
                
                headerView.headerLabel.text = "Landscape Photos"
                
                return headerView

            case UICollectionView.elementKindSectionFooter:
                let footerView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "Footer", for: indexPath) as! FooterView
                footerView.footerLabel.text = "\(landscapeImages.count) photos"
                return footerView

            default:

                assert(false, "Unexpected element kind")
            }
    }
   

    // MARK: UICollectionViewDelegate

    /*
    // Uncomment this method to specify if the specified item should be highlighted during tracking
    override func collectionView(_ collectionView: UICollectionView, shouldHighlightItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment this method to specify if the specified item should be selected
    override func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment these methods to specify if an action menu should be displayed for the specified item, and react to actions performed on the item
    override func collectionView(_ collectionView: UICollectionView, shouldShowMenuForItemAt indexPath: IndexPath) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, canPerformAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, performAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) {
    
    }
    */

}
