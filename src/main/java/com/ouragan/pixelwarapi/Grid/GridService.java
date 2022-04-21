package com.ouragan.pixelwarapi.Grid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GridService {
    private final GridRepository gridRepository;

    @Autowired
    public GridService(GridRepository studentRepository) {
        this.gridRepository = studentRepository;
    }

    public List<GirdData> getGrids(){
        return gridRepository.findAll();
    }

    public GirdData getGrid(Long gridId){

        GirdData g = getElementID(gridId);

        if(g == null){
            System.out.println("IN GET GRID, Throwing error grid with id " + gridId + " does not exist");
            throw new IllegalStateException("grid with id " + gridId + " does not exist");
        }

        System.out.println("IN GET GRID, element found.");
        System.out.println("END OF GET GRID, grid=");
//        g.printCurrentGrid();

        return g;
    }

    public GirdData getElementID(Long id){
        List<GirdData> grids = getGrids();
        System.out.println("size = " + grids.size());
        for (int i = 0; i < grids.size(); ++i) {
            if(grids.get(i).getId() == id) return grids.get(i);
        }
        return null;
    }

    public void deleteDuplicates(Long exceptionID){
        List<GirdData> grids = getGrids();
        System.out.println("Deleting Duplicates except id#" + exceptionID + " in size " + grids.size() + " list");

        for (int i = 0; i < grids.size(); ++i) {
            if(! grids.get(i).getId().equals(exceptionID)){
                gridRepository.deleteById(grids.get(i).getId());
                System.out.println("Deleting id#" + grids.get(i).getId());
            }
        }
    }

    public Long getSmallestGridID(){
        List<GirdData> grids = getGrids();

        if(grids.size() <= 0) return null;

        Long min = grids.get(0).getId();

        for (int i = 1; i < grids.size(); ++i) {
            if( (grids.get(i).getId()).compareTo(min) < 0){
                min = grids.get(i).getId();
            }
        }

        System.out.println("Smallest ID = " + min);

        return min;
    }

    @Transactional
    public void setGrid(Long gridId, GirdData newGrid){

        GirdData g = getElementID(gridId);

        if(g == null){
            System.out.println("IN SET GRID, grid with id " + gridId + " does not exist");
            throw new IllegalStateException("grid with id " + gridId + " does not exist");
        }else{
            System.out.println("IN SET GRID, element found.");
            g.setGrid(newGrid.getWidth(),newGrid.getHeight(),newGrid.getGrid());
        }

        System.out.println("END OF SET GRID, grid=");
//        getElementID(gridId).printCurrentGrid();
    }

    public void addNewGrid(GirdData grid) {
        gridRepository.save(grid);
        System.out.println("FROM ADD NEW GRID, Added new grid");
    }

}
