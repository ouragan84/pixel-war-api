package com.ouragan.pixelwarapi;

import com.ouragan.pixelwarapi.Grid.GirdData;
import com.ouragan.pixelwarapi.Grid.GridConfig;
import com.ouragan.pixelwarapi.Grid.GridRepository;
import com.ouragan.pixelwarapi.Grid.GridService;
import com.ouragan.pixelwarapi.Messages.FillAreaMessage;
import com.ouragan.pixelwarapi.Messages.PixelChangeMessage;
import com.ouragan.pixelwarapi.Messages.ResizeMessage;
import com.ouragan.pixelwarapi.Messages.SetGridMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;

@Controller
@RestController
@EnableScheduling
public class GridController {

    @Autowired
    private SimpMessagingTemplate template;

    private final GridService gridService;
    private Long gridID;
    public GirdData currentGrid;

    @Autowired
    public GridController(GridService gridService) {
        this.gridService = gridService;

        this.gridID = gridService.getSmallestGridID();

        if(this.gridID == null){
            this.currentGrid = new GirdData();
            this.gridService.addNewGrid(currentGrid);
            this.gridID = gridService.getSmallestGridID();
        }else{
            this.gridService.deleteDuplicates( this.gridID );
            currentGrid = this.gridService.getGrid( this.gridID );
        }

        System.out.println("Hey trying to access password: " + System.getenv("PROTECTED_METHOD_PASSWORD"));
    }

    @MessageMapping("/grid/place")
    @SendTo("/topic/pixel_update")
    public PixelChangeMessage send(final PixelChangeMessage message) throws Exception {

        System.out.println("Called send with Message coord:("+message.getX()+", "+message.getY()+") new color:" + message.getColor());
        this.updateGridPixel(this.currentGrid, message.getX(), message.getY(), message.getColor());

        return message;
    }

    // saves the grid every 5 min
    @Scheduled(fixedRate = 1000*60*5) // in ms
    public void scheduleGridSave() {
        System.out.println("saving grid from scheduleFixedDelayTask");

        this.gridService.setGrid(this.gridID, currentGrid);
    }

    // saves the grid before regular shutdown
    @PreDestroy
    public void preDestroyGridSave() {
        System.out.println("saving grid from preDestroy");
        this.gridService.setGrid(this.gridID, currentGrid);
    }

    // sends every user the updated grid in case messages were missed every 5 min
    @Scheduled(fixedRate = 1000*60*5) // in ms
    public void scheduleFullUpdate(){
        UpdateAllUsers();
    }

    public GirdData setGrid(SetGridMessage message){
        this.currentGrid = new GirdData(message.getGrid(), message.getGrid()[0].length, message.getGrid().length);
        UpdateAllUsers();
        return this.currentGrid;
    }

    public GirdData resizeGrid(ResizeMessage message){
        this.currentGrid.resize(message.getWidth(), message.getHeight(), GridConfig.initialColor);
        UpdateAllUsers();
        return this.currentGrid;
    }

    public int fillArea(FillAreaMessage message){
        int count = this.currentGrid.fillOverArea(message.getX1(), message.getY1(), message.getX2(), message.getY2(), message.getFillColor());
        UpdateAllUsers();
        return count;
    }

    public void updateGridPixel(GirdData g, int x, int y, int color){

        if(x < g.getWidth() && x >= 0 &&  y < g.getHeight() && y >= 0){
            g.setPixel(x, y, color);
            System.out.println("pixel successfully updated");
        }else{
            System.out.println("error: x or y coords not valid");
        }
    }

    public void UpdateAllUsers(){
        System.out.println("Sending Full Grid to all users");
        this.template.convertAndSend("/topic/full_update", this.currentGrid);
    }
}