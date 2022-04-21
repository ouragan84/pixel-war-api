package com.ouragan.pixelwarapi;

import com.ouragan.pixelwarapi.Grid.GirdData;
import com.ouragan.pixelwarapi.Messages.FillAreaMessage;
import com.ouragan.pixelwarapi.Messages.ResizeMessage;
import com.ouragan.pixelwarapi.Messages.SetGridMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/grid")
public class GridStaticConroller {

    public GridController gridController;

    @Autowired
    public GridStaticConroller(GridController gridController) {
        this.gridController = gridController;
    }

    @GetMapping("/get")
    public GirdData getGrid() throws Exception {
        System.out.println(" -- Called getGrid with empty Message -- ");

        return this.gridController.currentGrid;
    }

    @PostMapping("/set")
    public GirdData setGrid(@RequestBody SetGridMessage message) throws Exception {
        System.out.println(" -- Called setGrid -- ");
        if(message.getGrid().length > 0)
            return this.gridController.setGrid(message);
        throw new Exception("length was too small, requires length > 0");
    }

    @GetMapping("/random")
    public SetGridMessage randomGrid() throws Exception {
        System.out.println(" -- Called randomGrid with empty Message -- ");
        SetGridMessage m = new SetGridMessage();
        m.setGrid(new int[][] {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}});

        return m;
    }

    @PostMapping("/resize")
    public GirdData resizeGrid(@RequestBody ResizeMessage message) throws Exception {
        System.out.println(" -- Called resizeGrid with " + message.getWidth() + "x" + message.getHeight() + " -- ");
        if(message.getWidth() > 0)
            return this.gridController.resizeGrid(message);
        throw new Exception("width was too small, requires width > 0");
    }

    @PostMapping("/fill_area")
    public int fillAreaGrid(@RequestBody FillAreaMessage message) throws Exception {
        System.out.println(" -- Called fillAreaGrid with -- ");
        return this.gridController.fillArea(message);
    }
}
